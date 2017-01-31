package com.example.cloudkinetics.contactsdemo;


import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by cloud kinetics on 1/29/2017.
 */
public class MessageApi {
    

    public static void call(Callback c, String msgBody) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationInterceptor(
                        BuildConfig.AUTH_SID, BuildConfig.AUTH_TOKEN)).build();
        RequestBody requestBody = new FormBody.Builder()
                .add("From", "+19254021291")
                .add("To", "+917998053670")
                .add("Body", msgBody)
                .build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Constants.URL_TWILIO_SMS)
                .build();
        okHttpClient.newCall(request).enqueue(c);
    }
}
