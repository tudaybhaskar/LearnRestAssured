package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token ; //ConfigLoader.getInstance().getAccessToken();
    private static Instant expiry_time ;
    //We can use the same refresh token for generating the access_token many no of times

    public synchronized static String getToken(){
        try{
            if( access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing the token ........");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300 );
            }else {
                System.out.println("-----Token is good to use-----");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT !!! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken(){
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        Response response = given().spec(SpecBuilder.getAccountRequestSpec())
                .formParams(formParams)
                .post();

        if( response.getStatusCode() != 200 ){
            throw new RuntimeException("ABORT !!! Renew token failed.......");
        }

        return response;
    }
}
