package com.haris.proscanner.util;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * When we have to use OkHttpClient multiple time at multiple files
 * so we used this class to get the common instance of OkHttpClient
 * from this file, we can control the configuration of OkHttpClient
 * rather have to open each file and do configuration separately
 */
public class OkHttpClientConfig {
    private OkHttpClient.Builder builder;
    private final int CONNECT_TIME_OUT = 30;
    private final  int READ_TIME_OUT = 30;

    public OkHttpClientConfig() {

        // init. OkHttpClient builder
        // set timeout duration for connection and read out
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIME_OUT, java.util.concurrent.TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, java.util.concurrent.TimeUnit.SECONDS);

        // init. configuration for ssl certificate in case of https
        // un-comment below both line when you have to used https
        // and https are from cpanel free vendor rather than any authentic provider
        // whenever your app give error regarding security exception then un-comment below lines

        /*SslCertificateConfig config = new SslCertificateConfig();
        builder.sslSocketFactory(config.getSslContext().getSocketFactory(), (X509TrustManager) config.getTrustManagers()[0]);*/

    }

    public OkHttpClient getHttpClient() {
        return builder.build();
    }

}
