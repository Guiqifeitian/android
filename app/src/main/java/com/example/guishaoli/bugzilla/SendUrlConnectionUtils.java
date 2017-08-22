package com.example.guishaoli.bugzilla;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by guishaoli on 2017/8/20.
 */
public class SendUrlConnectionUtils {
    public ArrayList<String> sendRequestWithHttpsURLConnection(final String uri, final String username , final String password){
        ArrayList<String> res = new ArrayList<String>();
        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        try{
            //https://rdmobilebugzilla.tp-link.com.cn:8008/
            URL url = new URL(uri);
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null,new TrustManager[]{new MyTrustManager()},new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Connection","keep-alive");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            String params = "Bugzilla_login="+ URLEncoder.encode(username, "UTF-8")+"&Bugzilla_password="+URLEncoder.encode(password, "UTF-8")
                    +"&GoAheadAndLogIn="+URLEncoder.encode("Log+in", "UTF-8");

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes("utf-8"));
            out.flush();
            out.close();

            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

            String sumcookie = "";
            if(!(cookies == null || cookies.isEmpty())){
                for(String cookie:cookies){
                    if(sumcookie.isEmpty()){
                        sumcookie = cookie.split(";")[0];
                    }else{
                        sumcookie += ";"+cookie.split(";")[0];
                    }
                    Log.d("mcookie",cookie.split(";")[0]);
                }
                Log.d("cookie",sumcookie);
                res.add(sumcookie);
            }

            InputStream in = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            //showResponse(response.toString());
            res.add(response.toString());
            return res;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return res;
    }


    private class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
