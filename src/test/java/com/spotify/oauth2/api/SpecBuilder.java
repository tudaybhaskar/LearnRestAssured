package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        String BASE_URI = System.getProperty("BASE_URI");
        System.out.println("BASE_URI is now: " + BASE_URI);
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/v1")
                .build();


    }
}
