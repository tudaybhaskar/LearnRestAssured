package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DemoTest {

    private String access_token = "BQCYGYUVzdVTlRcAoxZPOY1WukKhjhFLObgkUMc0VQRBfdWpQQUS62wpd0hO7b8887nqMDInjX_gNK55CCgcPAuQVuIzvTwI_SZaUmNznOaanwQzHqBhRHRP9nD5CaLQbHcssIurZqwZbaYOME7pAluoOLOLDAikbv71l2DtXnuwgnfDeLCQuveuY9QIs4egowSS79_k1aYLxSDmPPGlN5jaqLqVjzZGiw7c4iJkmGkKp9l9luYIi4Veb54hsnwFORxV3RVWrb7F";

    @Test
    public void getCurrentUserId(){
        String actualUserId = given().baseUri("https://api.spotify.com/v1/me")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("id");
        System.out.println("Actual User ID : " + actualUserId);

        assertThat(actualUserId, equalTo(ConfigLoader.getInstance().getUserId()));
    }

    @Test
    public void getCurrentUserProfile(){
        String actualUserId = given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("/me")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .jsonPath()
                .getString("id");

        System.out.println("Actual User ID : " + actualUserId);

        assertThat(actualUserId, equalTo(ConfigLoader.getInstance().getUserId()));
    }
}
