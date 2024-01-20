package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTest {
    private static String access_token = ConfigLoader.getInstance().getAccessToken();//"BQCucUx2ziuNulDqDvUpPQhtosoc-YmGZekxT1rSeIkBRDBxooZIBC1hWktBmkEm67GCzdwZwKKkxTf3m5SzlmJBeTZr_pb5UODx8np05yiM17hThimw8TgDlV-4p8BxUGOceGXBgvDeX7MvWJ2cg0gm4jvNBKeoEP1_NNGuKhKItgS1hPIYmd7SxgrgByxtOCktMPiaSHinhpPWvH4jm1hK_P7ewhjJqNUaVWd5CD9HYZQ5YZGRseIqiX-woVMK9yTlGIW_Vs9t";

    @Test
    public void createPlayList1(){
        //https://api.spotify.com/v1/users/{user_id}/playlists
        String playlist_description = generateDescription();

        Playlist requestPlayList = playlistBuilder(generateName(), playlist_description , false);

        Response response = given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + getToken())
                .header("Content-Type", "application/json")
                .body(requestPlayList)
                .when()
                .post(Routes.USERS+"/"+ ConfigLoader.getInstance().getUserId()+Routes.PLAYLISTS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .response();

        assertThat(response.jsonPath().getString("description"),equalTo(playlist_description));
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public ){
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }
}
