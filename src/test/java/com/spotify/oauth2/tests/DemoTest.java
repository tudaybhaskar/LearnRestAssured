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

    private String access_token = "BQCti9SbRk6dDw__DGzaacACymK5ji11wVEMSJYESb0m1ebvu5D-VaVjZQl8XRUI5n6Qwvm0AWJtDdverv5olg-Qv1koR2oygYaU7cCDnLS2YdzSMThjsQ0Vakoi6AncgtYnb-hgO1A-arBg_0rZVhXdzl9b1Um3dayqFupomU7xdmiDA5uPD9CFGRi1rJrKd5VmLk1drRyKPrxTy4POsb9RMADy2o2sb_UmSiUFpg2eUj6oF_CzPHs6voEoVeC5NeVW68yswwAj";

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
