package com.fjern.common.service;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.services.IService;
import com.fjern.test.common.util.IdUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public abstract class AbstractServiceUnitTest<T extends NameableEntity> {

    public void before() {
        Mockito.when(getRepo().findAll()).thenReturn(Lists.<T> newArrayList());
    }

    @Test
    public void ifServiceInitialized_thenNoException() {

    }

    //create

    @Test(expected = NullPointerException.class)
    public void whenCreateIsTriggeredForNullEntity_ThenException() {
        getApi().create(null);
    }

    @Test
    public void whenCreateIsTriggered_ThenNoException() {
        getApi().create(stubRepoSave(createNewEntity()));
    }

    @Test
    public void whenCreatingNewEntityThenEntityIsSaved() {
        T entity = stubRepoSave(createNewEntity());
        getApi().create(entity);
        Mockito.verify(getRepo()).save(entity);
    }

    //update

    @Test
    public void whenUpdateIsTriggered_thenNoException() {
        T entity = createSimulatedExistingEntity();
        getApi().update(entity);
        Mockito.verify(getRepo()).save(entity);
    }

    protected abstract T createNewEntity();

    protected T createSimulatedExistingEntity() {
        T entity = createNewEntity();
        entity.setId(IdUtil.randomPositiveLong());
        Mockito.when(getRepo().findById(entity.getId())).thenReturn(Optional.of(entity));
        return entity;
    }

    private T stubRepoSave(T entity) {
        Mockito.when(getRepo().save(entity)).thenReturn(entity);
        return entity;
    }

    protected T givenEntityExists(T entity) {
        Mockito.when(getRepo().findById(entity.getId())).thenReturn(Optional.of(entity));
        return entity;
    }

    protected abstract IService<T> getApi();

    protected abstract JpaRepository<T, Long> getRepo();

}
