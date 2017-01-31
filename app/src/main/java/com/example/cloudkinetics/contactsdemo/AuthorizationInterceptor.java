package com.example.cloudkinetics.contactsdemo;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cloud kinetics on 1/29/2017.
 */
public class AuthorizationInterceptor implements Interceptor {
    private String credentials;

    public AuthorizationInterceptor(String auth_sid, String auth_token) {
        credentials = Credentials.basic(auth_sid, auth_token);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authRequest = request.newBuilder().header("Authorization", credentials).build();
        return chain.proceed(authRequest);
    }
}
