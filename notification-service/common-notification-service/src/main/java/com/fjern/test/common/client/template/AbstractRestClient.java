package com.fjern.test.common.client.template;

import com.fjern.common.interfaces.IEntity;
import com.fjern.common.web.util.RestPreconditions;
import com.fjern.test.common.client.security.ITestAuthenticator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractRestClient<T extends IEntity> implements IRestClient<T> {

    @Autowired
    protected ITestAuthenticator auth;

    protected final Class<T> clazz;

    protected AbstractRestClient(Class<T> clazz) {
        RestPreconditions.checkNotNull(clazz);
        this.clazz = clazz;
    }

    //find - one

    @Override
    public T findById(Long id) {
        String uri = getUri() + "/" + id;
        return findOneByUri(uri);
    }

    @Override
    public T findOneByUri(String uri) {
        return findOneByUriAsResponse(uri).as(clazz);
    }

    public Response findOneByUriAsResponse(String uri) {
        return findOneByUriAsResponse(uri, null);
    }

    @Override
    public Response findOneByUriAsResponse(String uri, RequestSpecification request) {
        if (request == null) {
            return readRequest().get(uri);
        }
        return readRequest(request).get(uri);
    }

    @Override
    public Response findOneAsResponse(Long id) {
        return findOneAsResponse(id, null);
    }

    public Response findOneAsResponse(Long id, RequestSpecification requestSpecification) {
        return findAllByUriAsResponse(getUri() + "/" + id, null);
    }

    //find - all

    @Override
    public List<T> findAll() {
        return findAllByUri(getUri());
    }

    @Override
    public List<T> findAllByUri(String uri) {
        return findAllByUriAsResponse(uri).as(List.class);
    }

    public Response findAllByUriAsResponse(String uri) {
        return findAllByUriAsResponse(uri, null);
    }

    public Response findAllByUriAsResponse(String uri, RequestSpecification request) {
        if (request == null) {
            return readRequest().get(uri);
        }
        return readRequest(request).get(uri);
    }

    @Override
    public Response findAllAsResponse(RequestSpecification request) {
        return findAllByUriAsResponse(getUri(), request);
    }

    //create

    @Override
    public T create(T object) {
       return createAsResponse(object).as(clazz);
    }

    @Override
    public Response createAsResponse(T resource) {
        return createAsResponse(resource, null);
    }

    @Override
    public Response createAsResponse(T resource, Pair<String, String> credentials) {
        RestPreconditions.checkNotNull(resource);
        RequestSpecification givenAuth = null;
        if (credentials != null) {
            givenAuth = auth.givenAuthenticated(credentials.getLeft(), credentials.getRight());
        } else {
            givenAuth = givenAuthenticated();
        }

        return givenAuth.contentType(ContentType.JSON).body(resource).post(getUri());
    }

    @Override
    public String createAsUri(T resource) {
        return createAsUri(resource, null);
    }

    public String createAsUri(T resource, Pair<String, String> credentials) {
        Response response = createAsResponse(resource, credentials);
        RestPreconditions.checkRequestState(response.getStatusCode()==201, "create operation: " + response.getStatusCode());

        String location = response.getHeader(HttpHeaders.LOCATION);
        RestPreconditions.checkNotNull(location);
        return location;
    }

    //update

    @Override
    public T update(T object) {
        return updateAsResponse(object).as(clazz);
    }

    @Override
    public Response updateAsResponse(T resource) {
        return givenAuthenticated().contentType(ContentType.JSON).body(resource).put(getUri() + "/" + resource.getId());
    }

    //delete

    @Override
    public void delete(Long id) {
        Response response = deleteAsResponse(id);
        RestPreconditions.checkRequestState(response.getStatusCode()==204);
    }


    @Override
    public Response deleteAsResponse(Long id) {
        return givenAuthenticated().contentType(ContentType.JSON).delete(getUri() + "/" + id);
    }

    public RequestSpecification readRequest() {
        return givenAuthenticated().contentType(ContentType.JSON);
    }

    public RequestSpecification readRequest(RequestSpecification request) {
        return request.contentType(ContentType.JSON);
    }

    @Override
    public RequestSpecification givenAuthenticated() {
        Pair<String, String> credentials = getCredentials();
        return auth.givenAuthenticated(credentials.getLeft(), credentials.getRight());
    }

    @Override
    public Response read(String uri) {
        return readRequest().get(uri);
    }

    protected abstract Pair<String, String> getCredentials();

    @Override
    public String getUri() {
        return null;
    }
}
