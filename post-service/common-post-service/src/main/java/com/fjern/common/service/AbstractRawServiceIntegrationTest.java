package com.fjern.common.service;


import com.fjern.common.client.IDtoOps;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.services.RawService;
import com.fjern.test.common.util.IdUtil;
import org.junit.Assert;
import org.junit.Test;


public abstract class AbstractRawServiceIntegrationTest<T extends NameableEntity> {

    @Test
    public void givenResourceDoesNotExists_whenFindNonExistingResource_thenReturnNull() {
        T resource = getApi().findById(IdUtil.randomPositiveLong());
        Assert.assertNull(resource);
    }

    public abstract RawService<T> getApi();

    public abstract IDtoOps<T> getDtoOps();
    public  T persistNewEntity() {
       return getApi().create(getDtoOps().createNewResource());
    }
}
