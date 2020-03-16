package com.technicaltest.technicaltest.app.services.retrofit;

import android.os.Build;

import java.io.IOException;

import es.laliga.innovationapp.BuildConfig;
import es.laliga.innovationapp.Utilities.Globals;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain)
            throws IOException {

        String accessToken = "";
        Request request = chain.request();

        try {
            if(Globals.authInfoRegister != null)
                accessToken = Globals.authInfoRegister.getTokenType() + " " + Globals.authInfoRegister.getAccessToken();
            else if(Globals.authInfoApplication != null)
                accessToken = Globals.authInfoApplication.getTokenType() + " " + Globals.authInfoApplication.getAccessToken();
            else
                throw new Exception("AccessToken Null, no se puede continuar con la ejecucion de la APP");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String versionRelease = Build.VERSION.RELEASE;
        String platformVersion = "Android " + versionRelease;

        request = request.newBuilder()
                .addHeader("Authorization", accessToken)
                .addHeader("Deviceplatform", "Android")
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "AppInnovacion/" + platformVersion + " AppVersion: " + BuildConfig.VERSION_NAME)
                .build();

        Response response = chain.proceed(request);
        return response;
    }
}
