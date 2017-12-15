package xyz.sangsik.blog.service;

import java.util.List;

/**
 * Created by sangsik on 2017-12-14.
 */
public interface GenericService<T> {
    T get(Long id);

    T add(T entity);

    T update(T entity);

    List<T> getAll();

    void delete(Long id);



}
