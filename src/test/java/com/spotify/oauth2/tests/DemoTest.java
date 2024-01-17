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

    private String access_token = "BQCzHoQEvxQjBJhWbHM1zwOwOD6FAgrJ8id8hMauo57rwgJet6TOXdbRvBCPY8qzTkUaAD50cqQYLZan_hBbdEDK08GOWU3snYG5OgcsPnlAercrfEE0AVW1XES1wvW6Ut8TmjzBC3Q170UCY315cvn5QjEKyQ-QcQo9HJBTHxmCFc2wLGNtqtTl8xBrcm-I9Yufz6kZYK4y12QlLmzrMVrPkncwGQBjmb11rAek8d8VfWO8UBzaHnyKKJceTrHNpcOfuTQaTMVN";

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
