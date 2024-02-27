package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.Entity;

public class Repository<T extends Entity> implements IRepository<T> {
    private Map<Integer, T> repositoryMap = new HashMap<>();

    @Override
    public T get(int id) {
        return repositoryMap.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repositoryMap.values());
    }

    @Override
    public void add(T object) {
        int id = object.getId();

        if (repositoryMap.containsKey(id)) {
            throw new IllegalArgumentException("Object with the same ID already exists");
        }

        repositoryMap.put(id, object);
    }

    @Override
    public void modify(T newObject) {
        int id = newObject.getId();
        
        if (!repositoryMap.containsKey(id)) {
            throw new IllegalArgumentException("No object with the provided ID");    
        }

        repositoryMap.put(id, newObject);
    }

    @Override
    public void remove(int id) {
        repositoryMap.remove(id);
    }
}
