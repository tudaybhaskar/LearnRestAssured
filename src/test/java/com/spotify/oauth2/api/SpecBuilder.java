package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static synchronized RequestSpecification getRequestSpec(){
        String BASE_URI = System.getProperty("BASE_URI");
        System.out.println("BASE_URI is now: " + BASE_URI);
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath("/v1")
                .build();

    }

    public static synchronized  RequestSpecification getAccountRequestSpec(){
         return new RequestSpecBuilder().setBaseUri(Routes.account_api)
                 .setContentType(ContentType.URLENC)
                 .addFilter(new AllureRestAssured())
                 .log(LogDetail.ALL)
                 .build();
    }
}
