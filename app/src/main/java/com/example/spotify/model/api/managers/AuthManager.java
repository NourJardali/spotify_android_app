package com.example.spotify.model.api.managers;

import android.net.Uri;

import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import static com.example.spotify.util.Constants.CLIENT_ID;
import static com.example.spotify.util.Constants.REDIRECT_URI;

public class AuthManager {
    public AuthorizationRequest getAuthenticationReq(){
        return new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN,Uri.parse(REDIRECT_URI).toString())
                .setShowDialog(false)
                .setScopes(new String[]{"user-read-private", "user-read-email"})
                .build();
    }
}
