package com.example.spotify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotify.controller.AuthController;
import com.example.spotify.databinding.ActivityLandingBinding;
import com.example.spotify.interfaces.AuthListener;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import static com.example.spotify.util.Constants.ACCESS_TOKEN;
import static com.example.spotify.util.Constants.EXPIRES_IN;

public class LandingActivity extends AppCompatActivity implements AuthListener {
    private SharedPreferences sharedPreferences;
    private ActivityLandingBinding binding;
    private AuthController authController;
    private Long expiresIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        authController = new AuthController(this, this);

        //to save token
        sharedPreferences = getSharedPreferences("spotify", MODE_PRIVATE);
        expiresIn = sharedPreferences.getLong(EXPIRES_IN, 0);
        if(expiresIn != 0){
            if(System.currentTimeMillis() > expiresIn){
                authController.startAuthenticating();
            }
            else{
                Intent intent = new Intent(this, ArtistSearchActivity.class);
                startActivity(intent);
                finish();
            }
        }
        configViews();
    }

    private void configViews(){
        binding.loginBtn.setOnClickListener(view -> authController.startAuthenticating());
    }

    @Override
    public void onAuthProgress(AuthorizationResponse authorizationResponse) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, authorizationResponse.getAccessToken());
        expiresIn = System.currentTimeMillis() + (authorizationResponse.getExpiresIn() * 1000);
        editor.putLong(EXPIRES_IN, expiresIn);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);

        authController.getResponse(response);
    }

    @Override
    public void onAuthComplete(boolean success) {
        if(success){
            //redirect to the "Artist Search" page
            Intent intent = new Intent(this, ArtistSearchActivity.class);
            startActivity(intent);
            finish();
        }
    }
}