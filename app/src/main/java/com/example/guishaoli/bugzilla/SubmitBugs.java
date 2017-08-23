package com.example.guishaoli.bugzilla;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.guishaoli.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SubmitBugs extends AppCompatActivity implements View.OnClickListener{

    private Spinner testCases;
    private Spinner ifRecovery;
    private LinearLayout caseName;
    private LinearLayout recovery;
    private Button advancedFieldsBtn;
    private AutoCompleteTextView autoText;
    private LinearLayout advancedfieldslin;
    private Button attachment;
    File sdDir;
    public String filename = null;
    private Button submit;
    String cookie;
    String token;
    String component;
    String product;
    ArrayList<String> versionlist;
    ArrayAdapter<String> arr_adapter;
    String assignee;

    private Spinner version;
    private Spinner cf_module;
    private Spinner cf_bugcategory;
    private EditText summary;
    private Spinner bug_severity;
    private Spinner cf_reproduce;
    private EditText cf_recovercondition;
    //private Spinner cf_testcase;
    private EditText cf_casename;
    private EditText cf_testtop;
    private EditText cf_accompanydevice;
    private EditText cf_reproducesteps;
    private EditText cf_testresult;
    private EditText cf_expectresult;
    private EditText cf_comparetest;
    private EditText cf_problemproducts;
    private EditText cf_problemanalysis;
    private Spinner cf_testanalysis;
    private EditText cf_bugimpact;
    private EditText comment;


    String[] res = {"appearance","audio","bluetooth","display","earphone","fm",
            "gps","input","memory","multimedia","MVB","pm","reliability",
            "RF","self_test","sensor","sim","software","Speaker","usb","wlan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.submit_bugs);

        Intent intent = getIntent();
        cookie = intent.getStringExtra("cookie");
        String uri = intent.getStringExtra("uri");
        Log.d("cookie",cookie);
        sendRequestWithHttpsURLConnec(uri);
        setContentView(R.layout.submit_bugs);

        testCases = (Spinner) findViewById(R.id.cf_testcase);
        ifRecovery = (Spinner) findViewById(R.id.ifrecovery) ;
        caseName = (LinearLayout) findViewById(R.id.casename);
        recovery = (LinearLayout) findViewById(R.id.recovery);
        advancedFieldsBtn = (Button) findViewById(R.id.advancedfields);
        autoText = (AutoCompleteTextView) findViewById(R.id.autotextview);
        advancedfieldslin = (LinearLayout) findViewById(R.id.advancedfieldslin);
        attachment = (Button) findViewById(R.id.attachment);
        submit = (Button) findViewById(R.id.submit);

        version = (Spinner) findViewById(R.id.version);
        cf_module = (Spinner) findViewById(R.id.cf_module);
        cf_bugcategory = (Spinner) findViewById(R.id.cf_bugcategory);
        summary = (EditText) findViewById(R.id.short_desc);
        bug_severity = (Spinner) findViewById(R.id.bug_severity);
        cf_reproduce = (Spinner) findViewById(R.id.cf_reproduce);
        cf_recovercondition = (EditText) findViewById(R.id.cf_recovercondition);
        //cf_testcase = (Spinner) findViewById(R.id.cf_testcase);
        cf_casename = (EditText) findViewById(R.id.cf_casename);
        cf_testtop = (EditText) findViewById(R.id.cf_testtop);
        cf_accompanydevice = (EditText) findViewById(R.id.cf_accompanydevice);
        cf_reproducesteps = (EditText) findViewById(R.id.cf_reproducesteps);
        cf_testresult = (EditText) findViewById(R.id.cf_testresult);
        cf_expectresult = (EditText) findViewById(R.id.cf_expectresult);
        cf_comparetest = (EditText) findViewById(R.id.cf_comparetest);
        cf_problemproducts = (EditText) findViewById(R.id.cf_problemproducts);
        cf_problemanalysis = (EditText) findViewById(R.id.cf_problemanalysis);
        cf_testanalysis = (Spinner) findViewById(R.id.cf_testanalysis);
        cf_bugimpact = (EditText) findViewById(R.id.cf_bugimpact);
        comment = (EditText) findViewById(R.id.comment);

        assert submit != null;
        assert attachment != null;
        assert advancedfieldslin != null;
        assert autoText != null;
        assert advancedFieldsBtn != null;
        assert testCases != null;
//        assert caseName != null;

        advancedFieldsBtn.setOnClickListener(this);
        attachment.setOnClickListener(this);
        submit.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,res);
        autoText.setAdapter(adapter);

        testCases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("有")){
                    caseName.setVisibility(View.VISIBLE);
                }else{
                    caseName.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ifRecovery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("能")){
                    recovery.setVisibility(View.VISIBLE);
                }else{
                    recovery.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.advancedfields:
                    if(advancedfieldslin.getVisibility() == View.GONE){
                        advancedfieldslin.setVisibility(View.VISIBLE);
                    }else{
                        advancedfieldslin.setVisibility(View.GONE);
                    }
                    break;
                case R.id.attachment:
                    chooseFile();
                    break;
                case R.id.submit:
                    if(testCases.getSelectedItem().equals("有")){
                        if(!checkInput(cf_module) || !checkInput(cf_bugcategory) ||!checkInput(summary) || !checkInput(cf_casename)
                                || !checkInput(cf_reproducesteps) || !checkInput(cf_testresult) || !checkInput(cf_expectresult)
                                || !checkInput(cf_testanalysis) || !checkInput(cf_bugimpact)){
                            new AlertDialog.Builder(SubmitBugs.this).setTitle("Alert").setMessage("红色选项必须输入内容").setPositiveButton("确定",null).show();
                        }else{
                            postFileWithParams(filename,joinParams());
                        }
                    }else if(testCases.getSelectedItem().equals("无")){
                        if(!checkInput(cf_module) || !checkInput(cf_bugcategory) ||!checkInput(summary)
                                || !checkInput(cf_reproducesteps) || !checkInput(cf_testresult) || !checkInput(cf_expectresult)
                                || !checkInput(cf_testanalysis) || !checkInput(cf_bugimpact)){
                            new AlertDialog.Builder(SubmitBugs.this).setTitle("Alert").setMessage("红色选项必须输入内容").setPositiveButton("确定",null).show();
                        }else{
                            postFileWithParams(filename,joinParams());
                        }
                    }else{
                        new AlertDialog.Builder(SubmitBugs.this).setTitle("Alert").setMessage("红色选项必须输入内容").setPositiveButton("确定",null).show();
                    }
                    break;
            }
    }

    private void chooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "选择文件"), 0);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "亲，木有文件管理器啊-_-!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Log.e("result", "onActivityResult() error, resultCode: " + resultCode);
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == 0) {
            Uri uri = data.getData();
            Log.i("file", "------->" + uri.getPath());
            filename = uri.getPath().toString();

            //Looper.prepare();
            Toast.makeText(SubmitBugs.this,"you choose file:"+filename,Toast.LENGTH_LONG).show();
            //Looper.loop();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private HashMap<String,String> joinParams(){
        HashMap<String,String> map = new HashMap<String,String>();

        map.put("product",product);
        map.put("token",token);
        map.put("component",component);
        map.put("version",version.getSelectedItem().toString());
        map.put("cf_module",cf_module.getSelectedItem().toString());
        map.put("cf_bugcategory",cf_bugcategory.getSelectedItem().toString());
        map.put("cf_applynumber","");
        map.put("short_desc",summary.getText().toString());
        map.put("bug_severity",bug_severity.getSelectedItem().toString());
        map.put("cf_reproduce",cf_reproduce.getSelectedItem().toString());
        map.put("cf_accident","");
        map.put("cf_recoverable",ifRecovery.getSelectedItem().toString());
        map.put("cf_recovercondition",cf_recovercondition.getText().toString());
        map.put("cf_testcase",testCases.getSelectedItem().toString());
        map.put("cf_casename",cf_casename.getText().toString());
        map.put("cf_testtop",cf_testtop.getText().toString());
        map.put("cf_accompanydevice",cf_accompanydevice.getText().toString());
        map.put("cf_reproducesteps",cf_reproducesteps.getText().toString());
        map.put("cf_testresult",cf_testresult.getText().toString());
        map.put("cf_expectresult",cf_expectresult.getText().toString());
        map.put("cf_comparetest",cf_comparetest.getText().toString());
        map.put("cf_problemproducts",cf_problemproducts.getText().toString());
        map.put("cf_testanalysis",cf_testanalysis.getSelectedItem().toString());
        map.put("cf_othertestanalysis","");
        map.put("cf_bugimpact",cf_bugimpact.getText().toString());
        map.put("comment",comment.getText().toString());

        if(!(filename== null)){
            map.put("description","file");
            map.put("contenttypemethod","autodetect");
        }
        map.put("contenttypeselection","text/plain");
        map.put("contenttypeentry","");
        map.put("cc","");
        map.put("assigned_to",assignee);
        map.put("op_sys","Windows");
        map.put("blocked","");
        map.put("keywords","");
        map.put("dependson","");
        map.put("priority","P3");
        map.put("rep_platform","PC");
        return map;
    }

    private void postFileWithParams(final String filename,final HashMap<String,String> map){
        sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if(sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }

        if(filename== null){
            new Thread(){
                @Override
                public void run() {

                    FileUploader.upload("https://rdmobilebugzilla.tp-link.com.cn:8008/post_bug.cgi", null, map, new FileUploader.FileUploadListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onProgress(long pro, double precent) {
                            Log.i("cky", precent+"");
                        }

                        @Override
                        public void onFinish(int code, String res, Map<String, List<String>> headers) {
                            Log.i("cks", res);
                            if(res.charAt(0) == '<' ){
                                String title = HtmlParseUtils.getTitle(res);
                                Looper.prepare();
                                Toast.makeText(SubmitBugs.this,title,Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    },cookie);
                }
            }.start();
        }else {
            new Thread(){
                @Override
                public void run() {

                    FileUploader.upload("https://rdmobilebugzilla.tp-link.com.cn:8008/post_bug.cgi", new File(filename), map, new FileUploader.FileUploadListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onProgress(long pro, double precent) {
                            Log.i("cky", precent+"");
                        }

                        @Override
                        public void onFinish(int code, String res, Map<String, List<String>> headers) {
                            Log.i("cks", res);
                            if(res.charAt(0) == '<' ){
                                String title = HtmlParseUtils.getTitle(res);
                                Looper.prepare();
                                Toast.makeText(SubmitBugs.this,title,Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    },cookie);
                }
            }.start();
        }


    }




    private Handler handler = new Handler() {

        public void handleMessage(Message message){
            switch(message.what){
                case 0:
                    for(String each:versionlist){
                    Log.d("version",each);
                };
                    arr_adapter= new ArrayAdapter<String>(SubmitBugs.this, android.R.layout.simple_spinner_item, versionlist);

                    version.setAdapter(arr_adapter);
                break;

            }
        }
    };

    private void sendRequestWithHttpsURLConnec(final String uri){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Log.d("start","StartTag");
                HttpsURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    //"https://rdmobilebugzilla.tp-link.com.cn:8008/enter_bug.cgi?sortation=test_c"
                    URL url = new URL(uri);
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null,new TrustManager[]{new MyTrustManager()},new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestProperty("Connection","keep-alive");
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    connection.setRequestProperty("Cookie",cookie);

                    String params = "";

                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    OutputStream out = connection.getOutputStream();
                    out.write(params.getBytes("utf-8"));
                    out.flush();
                    out.close();

                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                    Message msg = Message.obtain();
                    msg.what = 0;
                    msg.obj = response.toString();
                    handler.sendMessage(msg);
                    Log.d("end",response.toString());
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
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //responseText.setText(response);
//                fileBugResult(response);
//            }
//        });
        fileBugResult(response);
    }

    private void fileBugResult(String response){
//        ArrayList<String> bugzillabody = HtmlParseUtils.getBugItems(response);
//        for(int i = 0;i<bugzillabody.size();i++){
//            Log.d(String.valueOf(i),bugzillabody.get(i));
//        }
        token = HtmlParseUtils.getToken(response);
        component = HtmlParseUtils.getComponent(response);
        product = HtmlParseUtils.getProduct(response);
        versionlist = HtmlParseUtils.getVersionList(response);
        assignee = HtmlParseUtils.getAssignee(response);
        Log.d("token",token);
        Log.d("component",component);
        Log.d("product",product);
//        for(String each:versionlist){
//            Log.d("version",each);
//        };
    }

    private class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private boolean checkInput(View v){
        if(v instanceof Spinner){
            if(((Spinner) v).getSelectedItem().toString().equals("---")){

                return false;
            }
            return  true;
        }else if(v instanceof EditText){
            if(((EditText) v).getText().toString().trim().equals("")){
                //new AlertDialog.Builder(SubmitBugs.this).setTitle("Alert").setMessage("红色选项必须输入内容").setPositiveButton("确定",null).show();
                return false;
            }
            return true;
        }
        return false;
    }

}
