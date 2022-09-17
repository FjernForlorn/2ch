package com.fjern.roles;

import com.fjern.app.persistence.entities.Role;
import com.fjern.app.web.util.UtilNames;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)

public class RolesBasicLiveTest {

    private final static String URI = "http://localhost:8080/roles";

    @Test
    public void whenAllRolesAreRetrievedThen200Ok () {
        final RequestSpecification auth = RestAssured.given()
                .auth()
                .preemptive()
                .basic(UtilNames.ADMIN_USERNAME, UtilNames.ADMIN_PASS);

        final Response response = auth.accept(ContentType.JSON).get(URI);
        assertThat(response.getStatusCode(), Matchers.equalTo(200));
    }

    @Test
    public void whenAllRolesAreRetrievedThenRolesNotEmpty () {
        final RequestSpecification auth = RestAssured.given()
                .auth()
                .preemptive()
                .basic(UtilNames.ADMIN_USERNAME, UtilNames.ADMIN_PASS);

        final Response response = auth.accept(ContentType.JSON).get(URI);
        List<Role> roleList = response.as(List.class);

        assertThat(roleList, not(Matchers.<Role> empty()));
    }
}
