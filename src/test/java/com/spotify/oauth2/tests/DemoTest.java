package com.spotify.oauth2.tests;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DemoTest {
    private String access_token = "BQB9V1YGDBdBOwAAVJpbAPt1l3y2uFI_1vv9-bABV7AjiFgV2ePCG8zgTftUc-VKriNNLntem" +
            "DNPYPYf8Ft3IzoKhC4_chqM3m4PAIp-S_mXuaOmr3jMD9bwXdJmgSeeVgVBoOUsntTd4DsOicpWeuC0aUqeX2dnp-_ny4zaI2fefJbkaz5SKdAXXfk_YVdiRCjBCwfMkjGHzRY6ijSvebuss3tn0UJaUCBQngJbNc9pVaWmRQVlhJNC3EEQqvOWO_W5Na8hKa3F";

    @Test
    public void getCurrentUserProfile(){
        String actualUserID = RestAssured.given().baseUri("https://api.spotify.com/v1/me")
                .header("Authorization", "Bearer " + access_token )
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().jsonPath().getString("id");

        System.out.println("Actual User Id: " + actualUserID);
        assertThat(actualUserID, equalTo(ConfigLoader.getInstance().getUserId()));

    }
}
