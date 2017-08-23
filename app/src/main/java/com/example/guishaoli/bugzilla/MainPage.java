package com.example.guishaoli.bugzilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guishaoli.myapplication.R;

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

public class MainPage extends AppCompatActivity {

    private TextView responseText;
    private String username;
    private String password;
    private Button fileBugs;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        responseText = (TextView) findViewById(R.id.response_text);
        fileBugs = (Button) findViewById(R.id.FileBugs);

        assert responseText != null;
        assert fileBugs != null;

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

//        Log.d("username",username);
//        Log.d("password",password);


        sendRequestWithHttpsURLConnection();

        fileBugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this,FileBugs.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

    }

    private void sendRequestWithHttpsURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    //https://rdmobilebugzilla.tp-link.com.cn:8008/
                    URL url = new URL("https://rdmobilebugzilla.tp-link.com.cn:8008/");
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null,new TrustManager[]{new MyTrustManager()},new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestProperty("Connection","keep-alive");
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                    String params = "Bugzilla_login="+ URLEncoder.encode(username, "UTF-8")
                            +"&Bugzilla_password="+URLEncoder.encode(password, "UTF-8")
                            +"&GoAheadAndLogIn="+URLEncoder.encode("Log+in", "UTF-8");

                    connection.setRequestMethod("POST");
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
                            //Log.d("mcookie",cookie.split(";")[0]);
                        }
                        Log.d("cookie",sumcookie);
                        cookie = sumcookie;
                    }

                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                    //Log.d("hehe",response.toString());
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
            }
        }).start();
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //responseText.setText(response);
                loginResult(response);
            }
        });
    }

    private void loginResult(String response){
        String title = HtmlParseUtils.getTitle(response).trim();
        ArrayList<String> bugzillabody = HtmlParseUtils.getBugItems(response);
        for(int i = 0;i<bugzillabody.size();i++){
            Log.d(String.valueOf(i),bugzillabody.get(i));
        }
        if(title.equals("Invalid Username Or Password")){
            responseText.setText(title);
        }else{
            fileBugs.setVisibility(View.VISIBLE);
            //responseText.setText(response);
        }
    }

    private class MyHostnameVerifier implements HostnameVerifier{
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private class MyTrustManager implements X509TrustManager{
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
