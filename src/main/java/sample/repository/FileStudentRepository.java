package sample.repository;

import sample.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileStudentRepository implements Repository<Student> {

    private final File file;

    public FileStudentRepository() {

        file = new File("data/students.txt");

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating students file", e);
        }
    }

    @Override
    public void add(Student student) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, true))) {

            writer.write(student.getStudentId() + ","
                    + student.getStudentName() + ","
                    + student.getStudentEmail() + ","
                    + student.getStudentPhone() + ","
                    + student.getStudentDepartment() + ","
                    + student.getAcademicYear());

            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error adding student", e);
        }
    }


    @Override
    public void update(Student item, Student newItem) {

        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length > 0 &&
                        parts[0].equals(item.getStudentId())) {

                    line = newItem.getStudentId() + ","
                            + newItem.getStudentName() + ","
                            + newItem.getStudentEmail() + ","
                            + newItem.getStudentPhone() + ","
                            + newItem.getStudentDepartment() + ","
                            + newItem.getAcademicYear();

                    found = true;
                }

                lines.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading students file", e);
        }

        if (!found) {
            throw new IllegalArgumentException(
                    "Student with ID " + item.getStudentId() + " not found."
            );
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, false))) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error updating student", e);
        }
    }

    @Override
    public void delete(Student student) {

        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length > 0 &&
                        parts[0].equals(student.getStudentId())) {

                    found = true;
                    continue;
                }

                lines.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading students file", e);
        }

        if (!found) {
            throw new IllegalArgumentException(
                    "Student with ID " + student.getStudentId() + " not found."
            );
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file, false))) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }

    @Override
    public ObservableList<Student> findAll() {

        ObservableList<Student> students = FXCollections.observableArrayList();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length != 6) {
                    System.out.println("Invalid student data: " + line);
                    continue;
                }

                try {
                    Student student = new Student(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            Integer.parseInt(parts[5])
                    );

                    students.add(student);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid academic year: " + line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading students file", e);
        }

        return students;
    }
}