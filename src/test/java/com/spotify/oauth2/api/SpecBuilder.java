package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Routes.BASE_PATH;

public class SpecBuilder {

    public static synchronized RequestSpecification getRequestSpec(){
        String BASE_URI = "https://api.spotify.com"; // System.getProperty("BASE_URI");
        System.out.println("BASE_URI is now: " + BASE_URI);
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath( BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

    }

    public static synchronized  RequestSpecification getAccountRequestSpec(){
         return new RequestSpecBuilder().setBaseUri(Routes.account_api)
                 .setContentType(ContentType.URLENC)
                 .addFilter(new AllureRestAssured())
                 .log(LogDetail.ALL)
                 .build();
    }

    public static synchronized ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }
}
