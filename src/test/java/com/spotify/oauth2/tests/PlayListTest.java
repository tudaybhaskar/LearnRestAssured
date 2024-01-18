package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;

public class PlayListTest {
    private static String access_token = "BQCucUx2ziuNulDqDvUpPQhtosoc-YmGZekxT1rSeIkBRDBxooZIBC1hWktBmkEm67GCzdwZwKKkxTf3m5SzlmJBeTZr_pb5UODx8np05yiM17hThimw8TgDlV-4p8BxUGOceGXBgvDeX7MvWJ2cg0gm4jvNBKeoEP1_NNGuKhKItgS1hPIYmd7SxgrgByxtOCktMPiaSHinhpPWvH4jm1hK_P7ewhjJqNUaVWd5CD9HYZQ5YZGRseIqiX-woVMK9yTlGIW_Vs9t";

    @Test
    public void createPlayList1(){
        //https://api.spotify.com/v1/users/{user_id}/playlists

        given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + access_token)
                .header("Content-Type", "application/json")
                .body(new Playlist("Playlist_1", "Description_Playlist", false))
                .when()
                .post(Routes.USERS+"/"+ ConfigLoader.getInstance().getUserId()+Routes.PLAYLISTS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }
}
