package com.spotify.oauth2.api;

import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String token, Object requestbody){
        return given()
                .spec(getRequestSpec())
                .auth().oauth2(token)
                .body(requestbody)
        .when()
                .post(path)
        .then()
                .spec(getResponseSpec()).extract().response();
    }


}
