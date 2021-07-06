package com.example.spotify.controller;

import android.app.Activity;

import com.example.spotify.interfaces.AuthListener;
import com.example.spotify.model.api.managers.AuthManager;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import static com.example.spotify.util.Constants.AUTH_TOKEN_REQUEST_CODE;

public class AuthController implements IAuthController {
    private final AuthManager authManager = new AuthManager();
    private final AuthListener authListener;
    private final Activity activity;

    public AuthController(AuthListener listener, Activity activity){
        authListener = listener;
        this.activity = activity;
    }

    @Override
    public void startAuthenticating() {
        final AuthorizationRequest request = authManager.getAuthenticationReq();
        AuthorizationClient.openLoginActivity(activity, AUTH_TOKEN_REQUEST_CODE, request);
    }

    @Override
    public void getResponse(AuthorizationResponse response) {
        authListener.onAuthProgress(response);
        authListener.onAuthComplete(response.getError() == null);
    }
}