package sample.repository;

import java.util.List; 

public interface Repository<T> {

    List<T> findAll();

    void add(T item);

    void update(T oldItem, T newItem);

    void delete(T item);
}