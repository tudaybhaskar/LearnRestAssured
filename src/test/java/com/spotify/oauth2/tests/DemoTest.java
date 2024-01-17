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

    private String access_token = "BQBlSq1-nsKQKZbuPJLGyES6pEPXD7iWo22hTsYS6Dq7pe1xyjFiHjPlF3ozDKmvyVIlg3HC2sNomczANaTakMcklqtF-7unk-TzjpdJLTEL1ijn29rFfLMiyubNfXMjOnlbVu74NPBNBje5n80hDg87YbNB4kf926i5Ppnrc5Y1YjzwBJ6rMuYS4xhwZBIRHbou6JtkxW1LA_NbCbb58kDZd1vEXXLRz75GdGybSq5oHQkhwluHBne_ed3Rh2UzVEgXZ7ko3-cR";

    @Test
    public void getCurrentUserId(){
        String actualUserId = given().spec(getRequestSpec())
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
