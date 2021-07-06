package com.example.spotify.controller;

import com.spotify.sdk.android.auth.AuthorizationResponse;

public interface IAuthController {
    void startAuthenticating();

    void getResponse(AuthorizationResponse response);
}
