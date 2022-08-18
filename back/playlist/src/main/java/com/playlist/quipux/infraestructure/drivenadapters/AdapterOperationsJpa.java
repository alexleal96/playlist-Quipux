package com.playlist.quipux.infraestructure.drivenadapters;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class AdapterOperationsJpa<E, D, I, R extends CrudRepository<D, I>> {
    protected R repository;
    private Class<D> dataClass;
    protected org.reactivecommons.utils.ObjectMapper mapper;
    private Function<D, E> toEntityFn;

    public AdapterOperationsJpa(R repository, ObjectMapper mapper, Function<D, E> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.dataClass = (Class)genericSuperclass.getActualTypeArguments()[1];
        this.toEntityFn = toEntityFn;
    }

    protected D toData(E entity) {
        return this.mapper.map(entity, this.dataClass);
    }

    protected E toEntity(D data) {
        return data != null ? this.toEntityFn.apply(data) : null;
    }

    public E save(E entity) {
        D data = this.toData(entity);
        return this.toEntity(this.saveData(data));
    }

    protected List<E> saveAllEntities(List<E> entities) {
        List<D> list = (List)entities.stream().map(this::toData).collect(Collectors.toList());
        return this.toList(this.saveData(list));
    }

    public List<E> toList(Iterable<D> iterable) {
        return (List)StreamSupport.stream(iterable.spliterator(), false).map(this::toEntity).collect(Collectors.toList());
    }

    protected D saveData(D data) {
        return this.repository.save(data);
    }

    protected Iterable<D> saveData(List<D> data) {
        return this.repository.saveAll(data);
    }

    public E findById(I id) {
        return this.toEntity(this.repository.findById(id).orElse(null));
    }

    public List<E> findAll() {
        return this.toList(this.repository.findAll());
    }

    public void delete(E entity) {
        D data = this.toData(entity);
        this.deleteData(data);
    }

    protected void deleteData(D data) {
        this.repository.delete(data);
    }

    public void deleteById(I id) {
        this.repository.deleteById(id);
    }
}
