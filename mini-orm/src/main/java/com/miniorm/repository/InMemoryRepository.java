package com.miniorm.repository;

import java.util.*;
import java.lang.reflect.*;
import com.miniorm.annotations.*;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> store = new HashMap<>();
    private long idCounter = 1;

    @Override
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        try {
            Field idField = getIdField(entity.getClass());
            idField.setAccessible(true);
            Object idValue = idField.get(entity);

            if (idValue == null) {
                if (idField.isAnnotationPresent(GeneratedValue.class)) {
                    idField.set(entity, idCounter++);
                }
            }

            store.put((ID) idField.get(entity), entity);
            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Field getIdField(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new RuntimeException("No field with @Id found in " + clazz.getName());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
    }
}
