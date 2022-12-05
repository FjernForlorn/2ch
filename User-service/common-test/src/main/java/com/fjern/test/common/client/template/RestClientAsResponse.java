package com.fjern.test.common.client.template;

import com.fjern.common.interfaces.IEntity;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.Pair;

public interface RestClientAsResponse<T extends IEntity> {

    public Response read(String uri);

    //find - one

    public Response findOneAsResponse (Long id);

    public Response findOneByUriAsResponse (String uri, RequestSpecification request);

    //find - all

    public Response findAllAsResponse (RequestSpecification request);

    //create

    public Response createAsResponse(T resource);

    public Response createAsResponse(T resource, Pair<String, String> credentials);

    //update

    public Response updateAsResponse(T resource);

    //delete

    public Response deleteAsResponse(Long id);

}
