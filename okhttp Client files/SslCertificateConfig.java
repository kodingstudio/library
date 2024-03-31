package com.haris.proscanner.util;

import com.haris.proscanner.MyApplication;
import com.haris.proscanner.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SslCertificateConfig {
    private TrustManager[] trustManagers;
    private SSLContext sslContext;


    public SslCertificateConfig() {

        try {
            init();
        } catch (CertificateException | IOException | InvalidKeySpecException |
                 NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

    }

    private void init() throws CertificateException, IOException, InvalidKeySpecException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        // Load the SSL certificate
        InputStream certificateInputStream = MyApplication.getInstance().getResources().openRawResource(R.raw.backend_server); // Replace R.raw.your_certificate with the location of your certificate file
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(certificateInputStream);

        // Create a KeyStore and load the certificate and private key into it
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("backend_server", certificate);

        // Create a TrustManager that trusts the server's certificate
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        try {
            trustManagerFactory.init(keyStore);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
        trustManagers = trustManagerFactory.getTrustManagers();

        // Create an SSLContext with the custom TrustManager
        sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, null);

    }

    public TrustManager[] getTrustManagers() {
        return trustManagers;
    }

    public SSLContext getSslContext() {
        return sslContext;
    }

}
