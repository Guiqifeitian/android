package com.example.guishaoli.bugzilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guishaoli.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FileBugs extends AppCompatActivity implements View.OnClickListener {

    private String cookie;

    private Button testpecker;
    private Button test_proc;
    private Button fota;
    private Button qc;
    private Button byd_charger;
    private Button quickcharge;
    private Button B3charger;
    private Button C3charger;
    private Button D3charger;
    private Button B31;
    private Button C31;
    private Button D31;
    private Button G31;
    private Button fackit;
    private Button mtkrun;
    private Button mtkwifi;
    private Button mtkdown;
    private Button mtknation;
    private Button mtkwrite;
    private Button mtkline;
    private Button mtktool;
    private Button mtkdatabase;
    private Button mtkelectric;
    private Button mtkboard;
    private Button mtkcalibration;
    private Button qc1;
    private Button qc2;
    private Button qc3;
    private Button qc4;
    private Button qc5;
    private Button qc6;
    private Button qc7;
    private Button qc8;
    private Button qc9;
    private Button qc10;
    private Button qc11;
    private Button qc12;
    private Button qc13;
    private Button qc14;
    private Button qc15;
    private Button mtk1;
    private Button mtk2;
    private Button mtk3;
    private Button mtk4;
    private Button mtk5;
    private Button mtk6;
    private Button mtk7;
    private Button mtk8;
    private Button mtk9;
    private Button mtk10;
    private Button mtk11;
    private Button qcp1;
    private Button qcp2;
    private Button qcp3;
    private Button qcp4;
    private Button qcp5;
    private Button qcp6;
    private Button feedback;
    private Button manage;
    private Button ue;

//    Button Calculator20 = (Button) findViewById(R.id.Calculator20);
//    Button Calendar20 = (Button) findViewById(R.id.Calendar20);
//    Button Clock20 = (Button) findViewById(R.id.Clock20);
//    Button Compass20 = (Button) findViewById(R.id.Compass20);
//    Button Feedback20 = (Button) findViewById(R.id.Feedback20);
//    Button Filemanager20 = (Button) findViewById(R.id.Filemanager20);
//    Button Gallery20 = (Button) findViewById(R.id.Gallery20);
//    Button InternalFeedback = (Button) findViewById(R.id.InternalFeedback);
//    Button Launcher20 = (Button) findViewById(R.id.Launcher20);
//    Button Messages20 = (Button) findViewById(R.id.Messages20);
//    Button Music20 = (Button) findViewById(R.id.Music20);
//    Button Notes20 = (Button) findViewById(R.id.Notes20);
//    Button Soundrecorder20 = (Button) findViewById(R.id.Soundrecorder20);
//    Button Themes20 = (Button) findViewById(R.id.Themes20);
//    Button Video20 = (Button) findViewById(R.id.Video20);
//    Button Weather20 = (Button) findViewById(R.id.Weather20);
//
//
//    Button Calculator21 = (Button) findViewById(R.id.Calculator21);
//    Button Camera21 = (Button) findViewById(R.id.Camera21);
//    Button Clock21 = (Button) findViewById(R.id.Clock21);
//    Button Compass21 = (Button) findViewById(R.id.Compass21);
//    Button Dialtacts21 = (Button) findViewById(R.id.Dialtacts21);
//    Button Feedback21 = (Button) findViewById(R.id.Feedback21);
//    Button Filemanager21 = (Button) findViewById(R.id.Filemanager21);
//    Button FM21 = (Button) findViewById(R.id.FM21);
//    Button InternalFeedback21 = (Button) findViewById(R.id.InternalFeedback21);
//    Button Launcher21 = (Button) findViewById(R.id.Launcher21);
//    Button Lock21 = (Button) findViewById(R.id.Lock21);
//    Button Messages21 = (Button) findViewById(R.id.Messages21);
//    Button Notes21 = (Button) findViewById(R.id.Notes21);
//    Button Settings21 = (Button) findViewById(R.id.Settings21);
//    Button Soundrecorder21 = (Button) findViewById(R.id.Soundrecorder21);
//    Button SystemUI21 = (Button) findViewById(R.id.SystemUI21);
//    Button SystemUpdate21 = (Button) findViewById(R.id.SystemUpdate21);
//    Button Themes21 = (Button) findViewById(R.id.Themes21);
//    Button Video21 = (Button) findViewById(R.id.Video21);
//    Button Weather21 = (Button) findViewById(R.id.Weather21);


    private HashMap<Integer,String> buttonMaps = new HashMap<Integer, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_bugs);

        buttonMaps.put(R.id.test_c,"https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?sortation=test_c");
        buttonMaps.put(R.id.byd,"https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?classification=%E3%80%90BF1550%E3%80%91");


        buttonMaps.put(R.id.Calculator20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Calculator2.0");
        buttonMaps.put(R.id.Calendar20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Calendar2.0");
        buttonMaps.put(R.id.Clock20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Clock2.0");
        buttonMaps.put(R.id.Compass20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Compass2.0");
        buttonMaps.put(R.id.Feedback20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Feedback2.0");
        buttonMaps.put(R.id.Filemanager20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Filemanager2.0");
        buttonMaps.put(R.id.Gallery20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Gallery2.0");
        buttonMaps.put(R.id.InternalFeedback, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Internal%20Feedback");
        buttonMaps.put(R.id.Launcher20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Launcher2.0");
        buttonMaps.put(R.id.Messages20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Messages2.0");
        buttonMaps.put(R.id.Music20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Music2.0");
        buttonMaps.put(R.id.Notes20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Notes2.0");
        buttonMaps.put(R.id.Soundrecorder20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Soundrecorder2.0");
        buttonMaps.put(R.id.Themes20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Themes2.0");
        buttonMaps.put(R.id.Video20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Video2.0");
        buttonMaps.put(R.id.Weather20, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Weather2.0");

        buttonMaps.put(R.id.Calculator21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Calculator2.1");
        buttonMaps.put(R.id.Camera21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Camera2.1");
        buttonMaps.put(R.id.Clock21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Clock2.1");
        buttonMaps.put(R.id.Compass21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Compass2.1");
        buttonMaps.put(R.id.Dialtacts21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Dialtacts2.1");
        buttonMaps.put(R.id.Feedback21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Feedback2.1");
        buttonMaps.put(R.id.Filemanager21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=FileManager2.1");
        buttonMaps.put(R.id.FM21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=FM2.1");
        buttonMaps.put(R.id.InternalFeedback21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Internal%20Feedback2.1");
        buttonMaps.put(R.id.Launcher21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Launcher2.1");
        buttonMaps.put(R.id.Lock21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=LockScreen%26Security2.1");
        buttonMaps.put(R.id.Messages21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Messages2.1");
        buttonMaps.put(R.id.Notes21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Note2.1");
        buttonMaps.put(R.id.Settings21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Settings2.1");
        buttonMaps.put(R.id.Soundrecorder21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Soundrecorder2.1");
        buttonMaps.put(R.id.SystemUI21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=SystemUI2.1");
        buttonMaps.put(R.id.SystemUpdate21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=SystemUpdate2.1");
        buttonMaps.put(R.id.Themes21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Themes2.1");
        buttonMaps.put(R.id.Video21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Video2.1");
        buttonMaps.put(R.id.Weather21, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=Weather2.1");

        buttonMaps.put(R.id.filedist, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=FileDist");
        buttonMaps.put(R.id.fota, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=FOTA");
        buttonMaps.put(R.id.manage, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=ManagementSystem");
        buttonMaps.put(R.id.ue, "https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=UEProject");

        buttonMaps.put(R.id.testpecker,"https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=TestPecker");
        buttonMaps.put(R.id.QC,"https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=QualityCenter");

        Intent intent = getIntent();
        cookie = intent.getStringExtra("cookie");



        testpecker = (Button) findViewById(R.id.testpecker);
        test_proc = (Button) findViewById(R.id.test_c);
        fota = (Button) findViewById(R.id.fota);
        qc = (Button) findViewById(R.id.QC);
        byd_charger = (Button) findViewById(R.id.byd);
        quickcharge = (Button) findViewById(R.id.quickcharge);
        B3charger = (Button) findViewById(R.id.B3charger);
        C3charger = (Button) findViewById(R.id.C3charger);
        D3charger = (Button) findViewById(R.id.D3charger);
        B31 = (Button) findViewById(R.id.B31);
        C31 = (Button) findViewById(R.id.C31);
        D31 = (Button) findViewById(R.id.D31);
        G31 = (Button) findViewById(R.id.G31);
        fackit = (Button) findViewById(R.id.fackit);
        mtkrun = (Button) findViewById(R.id.mtkrun);
        mtkwifi = (Button) findViewById(R.id.mtkwifi);
        mtkdown = (Button) findViewById(R.id.mtkdown);
        mtknation = (Button) findViewById(R.id.mtknation);
        mtkwrite = (Button) findViewById(R.id.mtkwrite);
        mtkline = (Button) findViewById(R.id.mtkline);
        mtktool = (Button) findViewById(R.id.mtktool);
        mtkdatabase = (Button) findViewById(R.id.mtkdatabase);
        mtkelectric = (Button) findViewById(R.id.mtkelectric);
        mtkboard = (Button) findViewById(R.id.mtkboard);
        mtkcalibration = (Button) findViewById(R.id.mtkcalibration);
        qc1 = (Button) findViewById(R.id.qc1);
        qc2 = (Button) findViewById(R.id.qc2);
        qc3 = (Button) findViewById(R.id.qc3);
        qc4 = (Button) findViewById(R.id.qc4);
        qc5 = (Button) findViewById(R.id.qc5);
        qc6 = (Button) findViewById(R.id.qc6);
        qc7 = (Button) findViewById(R.id.qc7);
        qc8 = (Button) findViewById(R.id.qc8);
        qc9 = (Button) findViewById(R.id.qc9);
        qc10 = (Button) findViewById(R.id.qc10);
        qc11 = (Button) findViewById(R.id.qc11);
        qc12 = (Button) findViewById(R.id.qc12);
        qc13 = (Button) findViewById(R.id.qc13);
        qc14 = (Button) findViewById(R.id.qc14);
        qc15 = (Button) findViewById(R.id.qc15);
        mtk1 = (Button) findViewById(R.id.mtk1);
        mtk2 = (Button) findViewById(R.id.mtk2);
        mtk3 = (Button) findViewById(R.id.mtk3);
        mtk4 = (Button) findViewById(R.id.mtk4);
        mtk5 = (Button) findViewById(R.id.mtk5);
        mtk6 = (Button) findViewById(R.id.mtk6);
        mtk7 = (Button) findViewById(R.id.mtk7);
        mtk8 = (Button) findViewById(R.id.mtk8);
        mtk9 = (Button) findViewById(R.id.mtk9);
        mtk10 = (Button) findViewById(R.id.mtk10);
        mtk11 = (Button) findViewById(R.id.mtk11);
        qcp1 = (Button) findViewById(R.id.qcp1);
        qcp2 = (Button) findViewById(R.id.qcp2);
        qcp3 = (Button) findViewById(R.id.qcp3);
        qcp4 = (Button) findViewById(R.id.qcp4);
        qcp5 = (Button) findViewById(R.id.qcp5);
        qcp6= (Button) findViewById(R.id.qcp6);
        feedback= (Button) findViewById(R.id.feedback);
        manage = (Button) findViewById(R.id.manage);
        ue = (Button) findViewById(R.id.ue);

        Button Calculator20 = (Button) findViewById(R.id.Calculator20);
        Button Calendar20 = (Button) findViewById(R.id.Calendar20);
        Button Clock20 = (Button) findViewById(R.id.Clock20);
        Button Compass20 = (Button) findViewById(R.id.Compass20);
        Button Feedback20 = (Button) findViewById(R.id.Feedback20);
        Button Filemanager20 = (Button) findViewById(R.id.Filemanager20);
        Button Gallery20 = (Button) findViewById(R.id.Gallery20);
        Button InternalFeedback = (Button) findViewById(R.id.InternalFeedback);
        Button Launcher20 = (Button) findViewById(R.id.Launcher20);
        Button Messages20 = (Button) findViewById(R.id.Messages20);
        Button Music20 = (Button) findViewById(R.id.Music20);
        Button Notes20 = (Button) findViewById(R.id.Notes20);
        Button Soundrecorder20 = (Button) findViewById(R.id.Soundrecorder20);
        Button Themes20 = (Button) findViewById(R.id.Themes20);
        Button Video20 = (Button) findViewById(R.id.Video20);
        Button Weather20 = (Button) findViewById(R.id.Weather20);


        Button Calculator21 = (Button) findViewById(R.id.Calculator21);
        Button Camera21 = (Button) findViewById(R.id.Camera21);
        Button Clock21 = (Button) findViewById(R.id.Clock21);
        Button Compass21 = (Button) findViewById(R.id.Compass21);
        Button Dialtacts21 = (Button) findViewById(R.id.Dialtacts21);
        Button Feedback21 = (Button) findViewById(R.id.Feedback21);
        Button Filemanager21 = (Button) findViewById(R.id.Filemanager21);
        Button FM21 = (Button) findViewById(R.id.FM21);
        Button InternalFeedback21 = (Button) findViewById(R.id.InternalFeedback21);
        Button Launcher21 = (Button) findViewById(R.id.Launcher21);
        Button Lock21 = (Button) findViewById(R.id.Lock21);
        Button Messages21 = (Button) findViewById(R.id.Messages21);
        Button Notes21 = (Button) findViewById(R.id.Notes21);
        Button Settings21 = (Button) findViewById(R.id.Settings21);
        Button Soundrecorder21 = (Button) findViewById(R.id.Soundrecorder21);
        Button SystemUI21 = (Button) findViewById(R.id.SystemUI21);
        Button SystemUpdate21 = (Button) findViewById(R.id.SystemUpdate21);
        Button Themes21 = (Button) findViewById(R.id.Themes21);
        Button Video21 = (Button) findViewById(R.id.Video21);
        Button Weather21 = (Button) findViewById(R.id.Weather21);

        Calculator21.setOnClickListener(this);
        Camera21.setOnClickListener(this);
        Clock21.setOnClickListener(this);
        Compass21.setOnClickListener(this);
        Dialtacts21.setOnClickListener(this);
        Feedback21.setOnClickListener(this);
        Filemanager21.setOnClickListener(this);
        FM21.setOnClickListener(this);
        InternalFeedback21.setOnClickListener(this);
        Launcher21.setOnClickListener(this);
        Lock21.setOnClickListener(this);
        Messages21.setOnClickListener(this);
        Notes21.setOnClickListener(this);
        Settings21.setOnClickListener(this);
        Soundrecorder21.setOnClickListener(this);
        SystemUI21.setOnClickListener(this);
        SystemUpdate21.setOnClickListener(this);
        Themes21.setOnClickListener(this);
        Video21.setOnClickListener(this);
        Weather21.setOnClickListener(this);

        assert test_proc != null;
        assert  testpecker != null;

        testpecker.setOnClickListener(this);
        test_proc.setOnClickListener(this);
        fota.setOnClickListener(this);
        qc.setOnClickListener(this);
        byd_charger.setOnClickListener(this);
        quickcharge.setOnClickListener(this);

        Calculator20.setOnClickListener(this);
        Calendar20.setOnClickListener(this);
        Clock20.setOnClickListener(this);
        Compass20.setOnClickListener(this);
        Feedback20.setOnClickListener(this);
        Filemanager20.setOnClickListener(this);
        Gallery20.setOnClickListener(this);
        InternalFeedback.setOnClickListener(this);
        Launcher20.setOnClickListener(this);
        Messages20.setOnClickListener(this);
        Music20.setOnClickListener(this);
        Notes20.setOnClickListener(this);
        Soundrecorder20.setOnClickListener(this);
        Themes20.setOnClickListener(this);
        Video20.setOnClickListener(this);
        Weather20.setOnClickListener(this);


        sendRequestWithHttpsURLConnection();

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,SubmitBugs.class);
        intent.putExtra("cookie",cookie);
        intent.putExtra("uri",buttonMaps.get(v.getId()));
        startActivity(intent);
//        switch(v.getId()){
//            case R.id.testpecker:
//                Intent intent1 = new Intent(FileBugs.this,SubmitBugs.class);
//                intent1.putExtra("cookie",cookie);
//                intent1.putExtra("uri","https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=TestPecker");
//                startActivity(intent1);
//                break;
//            case R.id.test_c:
//                Intent intent2 = new Intent(FileBugs.this,SubmitBugs.class);
//                intent2.putExtra("cookie",cookie);
//                intent2.putExtra("uri","https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?sortation=test_c");
//                startActivity(intent2);
//                break;
//            case R.id.fota:
//                Intent intent3 = new Intent(this,SubmitBugs.class);
//                intent3.putExtra("cookie",cookie);
//                intent3.putExtra("uri","https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=FOTA");
//                startActivity(intent3);
//                break;
//            case R.id.QC:
//                Intent intent4 = new Intent(this,SubmitBugs.class);
//                intent4.putExtra("cookie",cookie);
//                intent4.putExtra("uri","https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?product=QualityCenter");
//                startActivity(intent4);
//                break;
//            default:
//                break;
//        }
    }

    private void sendRequestWithHttpsURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                    HttpsURLConnection connection = null;
                    BufferedReader reader = null;
                    try{
                        //https://rdmobilebugzilla.tp-link.com.cn:8008/
                        URL url = new URL("https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi");
                        SSLContext sc = SSLContext.getInstance("TLS");
                        sc.init(null,new TrustManager[]{new MyTrustManager()},new SecureRandom());
                        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                        HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
                        connection = (HttpsURLConnection) url.openConnection();
                        connection.setRequestProperty("Connection","keep-alive");
                        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                        connection.setRequestProperty("Cookie",cookie);

                        String params = "";

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
                                Log.d("mcookie",cookie.split(";")[0]);
                            }
                            Log.d("cookie",sumcookie);
                        }

                        InputStream in = connection.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null){
                            response.append(line);
                        }
                        showResponse(response.toString());
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
                fileBugResult(response);
            }
        });
    }

    private void fileBugResult(String response){
        ArrayList<String> bugzillabody = HtmlParseUtils.getBugItems(response);
        for(int i = 0;i<bugzillabody.size();i++){
            Log.d(String.valueOf(i),bugzillabody.get(i));
        }
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
