package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Routes.PLAYLISTS;
import static com.spotify.oauth2.api.Routes.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(Playlist playlist){
        String requestPath = USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS;
        System.out.println( "Request Path : " + requestPath);
        return RestResource
                .post( USERS + "/" + ConfigLoader.getInstance().getUserId()
                                + PLAYLISTS, getToken(), playlist);

    }


}
