package com.example.spotify.interfaces;

import com.spotify.sdk.android.auth.AuthorizationResponse;

public interface AuthListener {
    void onAuthProgress(AuthorizationResponse authorizationResponse);

    void onAuthComplete(boolean success);
}
