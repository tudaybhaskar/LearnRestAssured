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

    private String access_token = "BQCucUx2ziuNulDqDvUpPQhtosoc-YmGZekxT1rSeIkBRDBxooZIBC1hWktBmkEm67GCzdwZwKKkxTf3m5SzlmJBeTZr_pb5UODx8np05yiM17hThimw8TgDlV-4p8BxUGOceGXBgvDeX7MvWJ2cg0gm4jvNBKeoEP1_NNGuKhKItgS1hPIYmd7SxgrgByxtOCktMPiaSHinhpPWvH4jm1hK_P7ewhjJqNUaVWd5CD9HYZQ5YZGRseIqiX-woVMK9yTlGIW_Vs9t";

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
