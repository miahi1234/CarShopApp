package repository;

import java.util.List;

public interface IRepository<T> {
    T get(int id);
    List<T> getAll();
    void add(T object);
    void modify(T newObject);
    void remove(int id);
}
