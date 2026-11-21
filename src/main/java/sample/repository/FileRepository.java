package sample.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class FileRepository<T> implements Repository<T> {

    private final Path path;

    protected FileRepository(String filePath) {
        this.path = Paths.get(filePath);
    }

    protected abstract String toLine(T item);

    protected abstract T fromLine(String line);

    protected abstract boolean sameKey(T a, T b);

    @Override
    public List<T> findAll() {
        List<T> items = new ArrayList<>();
        if (!Files.exists(path)) {
            return items;
        }
        try {
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    items.add(fromLine(line));
                } catch (RuntimeException e) {
                    System.err.println("Skipped bad line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void add(T item) {
        List<T> items = findAll();
        items.add(item);
        saveAll(items);
    }

    @Override
    public void update(T oldItem, T newItem) {
        List<T> items = findAll();
        for (int i = 0; i < items.size(); i++) {
            if (sameKey(items.get(i), oldItem)) {
                items.set(i, newItem);
                break;
            }
        }
        saveAll(items);
    }

    @Override
    public void delete(T item) {
        List<T> items = findAll();
        items.removeIf(existing -> sameKey(existing, item));
        saveAll(items);
    }

    private void saveAll(List<T> items) {
        List<String> lines = new ArrayList<>();
        for (T item : items) {
            lines.add(toLine(item));
        }
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

