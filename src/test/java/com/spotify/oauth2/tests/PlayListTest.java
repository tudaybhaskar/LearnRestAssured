package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
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
    private final String playlist_description = generateDescription();
    private final String playlist_name = generateName();

    /*@Test
    public void createPlayList1(){
        //https://api.spotify.com/v1/users/{user_id}/playlists

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

     */

    @Test
    public void verifyCreation_Of_Playlist(){
        Playlist requestPlaylist = playlistBuilder( playlist_name, playlist_description, false );

        Response response = PlaylistApi.post( requestPlaylist );
        assertStatusCode( response.statusCode(), StatusCode.CODE_201);
        assertPlaylistName( response.jsonPath().getString("name"), playlist_name);
        assertPlaylistDescription( response.jsonPath().getString("description"), playlist_description);
    }


    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public ){
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    @Step
    public void assertStatusCode( int actualStatusCode, StatusCode statusCode){
        assertThat( actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertPlaylistName( String actualPlaylistName, String expectedPlayListName ){
        assertThat( actualPlaylistName, equalTo( playlist_name));
    }

    @Step
    public void assertPlaylistDescription( String actualPlaylistDescription, String expectedPlaylistDescription ){
        assertThat( actualPlaylistDescription, equalTo(expectedPlaylistDescription));
    }
}
