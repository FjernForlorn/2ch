package com.fjern.app.client.template;

import com.fjern.app.web.util.UtilNames;
import com.fjern.app.web.util.UtilPaths;
import com.fjern.common.interfaces.IEntity;
import com.fjern.common.web.util.RestPreconditions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class GenericSimpleApiClient<T extends IEntity> {

    public GenericSimpleApiClient(Class<T> clazz) {
        this.clazz=clazz;
    }

    private final Class<T> clazz;

    @Autowired
    protected UtilPaths utilPaths;

    public T findOne (Long id) {
        Response response= findOneAsResponse(id);
        RestPreconditions.checkRequestState(response.getStatusCode()==200, "Find one didn't result in 200 OK");
        return findOneAsResponse(id).as(clazz);
    }

    public List<T> findAll() {
        Response response = findAllAsResponse(getUri());
        return response.as(List.class);
    }

    public T create(T resource) {
        T createdResource = createAsResponse(resource).as(clazz);
        return createdResource;
    }

    public T update(T resource) {
        Response response = updateAsResponse(resource);
        return response.as(clazz);
    }

    public Response deleteAsResponse(Long id) {
        Response response = givenAuthenticated().contentType(ContentType.JSON).delete(getUri()+"/"+id);
        return response;
    }

    public Response updateAsResponse(T resource) {
        Response response = givenAuthenticated().contentType(ContentType.JSON).body(resource).put(getUri()+"/"+resource.getId());
        return response;
    }

    public Response createAsResponse(T resource) {
        Response response = givenAuthenticated().contentType(ContentType.JSON).body(resource).post(getUri());
        return response;
    }

    public Response findOneAsResponse (Long id) {
        Response response= read(getUri()+"/"+id);
        return response;
    }

    public Response findAllAsResponse (String uri) {
        Response response = read(uri);
        return response;
    }

    public Response read(String uri) {
        Response response = givenAuthenticated().accept(ContentType.JSON).get(uri);
        return response;
    }

    public RequestSpecification givenAuthenticated() {
        return RestAssured.given().auth().basic(defaultCredentials().getLeft(),defaultCredentials().getRight());
    }

    private final Pair<String, String> defaultCredentials() {

        return new ImmutablePair<String, String>(UtilNames.ADMIN_USERNAME, UtilNames.ADMIN_PASS);
    }

    public abstract String getUri();
}
