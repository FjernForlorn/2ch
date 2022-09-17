package com.fjern.roles;

import com.fjern.app.spring.ClientConfig;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.spring.AppLiveTestConfig;
import com.fjern.test.common.client.template.IRestClient;
import com.fjern.test.common.util.IdUtil;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppLiveTestConfig.class, ClientConfig.class}, loader = AnnotationConfigContextLoader.class)
public abstract class GenericSimpleLiveTest<T extends NameableEntity> {

    @Test
    public void whenNonExistingResourceRetrieved_Then404IsReceived() {
        Response response = getApi().findOneAsResponse(IdUtil.randomPositiveLong());

        assertThat(response.getStatusCode(), Matchers.equalTo(404));
    }

    @Test
    public void whenResourceIsRetrievedByNyNonNumericId_Then400IsReceived() {
        Response response = getApi().read(getUri() + "/" + randomAlphabetic(6));

        assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }

    @Test
    public void givenResourceOfThatIdExist_WhenThisResourceIsRetrieved_Then200IsReceived() {
        String uriForResourceCreation = getApi().createAsResponse(createNewResource()).getHeader(HttpHeaders.LOCATION);
        Response response = getApi().read(uriForResourceCreation);

        assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void whenResourceIsCreated_thenResourceIsRetrievedCorrectly() {
        T resource = createNewResource();
        T retrievedResource = getApi().create(resource);

        assertEquals(resource, retrievedResource);
    }

    //create

    @Test
    public void whenResourceIsCreated_then201IsReceived() {
        Response response = getApi().createAsResponse(createNewResource());

        assertThat(response.getStatusCode(), Matchers.equalTo(201));
    }

    @Test
    public void givenResourceHasNameWithSpace_whenResourceIsCreated_then201IsReceived() {
        T createdResource = createNewResource();
        createdResource.setName(randomAlphabetic(4)+" "+randomAlphabetic(4));

        Response response = getApi().createAsResponse(createdResource);

        assertThat(response.getStatusCode(), Matchers.equalTo(201));
    }

    @Test
    public void whenResourceHasNotMaintainableMediaType_then415isReceived() {
        Response response = getApi().givenAuthenticated().contentType("unknown").post(getUri());

        assertThat(response.getStatusCode(), Matchers.equalTo(415));
    }

    @Test
    public void whenResourceHasNotNullId_then400IsReceived() {
        T createdResource = createNewResource();
        createdResource.setId(IdUtil.randomPositiveLong());

        Response response = getApi().createAsResponse(createdResource);

        assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }

    @Test
    public void givenResourceExists_whenSameResourceCreated_then409IsReceived() {
        T createdResource = createNewResource();
        getApi().create(createdResource);
        Response response = getApi().createAsResponse(createdResource);

        assertThat(response.getStatusCode(), Matchers.equalTo(409));
    }

    protected abstract IRestClient<T> getApi();

    private String getUri() {
       return getApi().getUri();
    }

    protected abstract T createNewResource();
}
