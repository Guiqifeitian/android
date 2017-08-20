package com.example.guishaoli.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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
    String filename;
    private Button submit;
    String cookie;

    String[] res = {"appearance","audio","bluetooth","display","earphone","fm","gps","input","memory","multimedia","MVB","pm","reliability","RF","self_test","sensor","sim","software","Speaker","usb","wlan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_bugs);

        Intent intent = getIntent();
        cookie = intent.getStringExtra("cookie");

        testCases = (Spinner) findViewById(R.id.testcase);
        ifRecovery = (Spinner) findViewById(R.id.ifrecovery) ;
        caseName = (LinearLayout) findViewById(R.id.casename);
        recovery = (LinearLayout) findViewById(R.id.recovery);
        advancedFieldsBtn = (Button) findViewById(R.id.advancedfields);
        autoText = (AutoCompleteTextView) findViewById(R.id.autotextview);
        advancedfieldslin = (LinearLayout) findViewById(R.id.advancedfieldslin);
        attachment = (Button) findViewById(R.id.attachment);
        submit = (Button) findViewById(R.id.submit);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,res);
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
                    postFileWithParams(filename);
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void postFileWithParams(final String filename){
        sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if(sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        final HashMap<String,String> map = new HashMap<String,String>();
        map.put("product","test_pro");
        map.put("version","unspecified");
        map.put("cf_module","Undefined");
        map.put("cf_bugcategory","Function");
        map.put("cf_applynumber","");
        map.put("bug_severity","B");
        map.put("cf_reproduce","must");
        map.put("cf_recoverable","---");
        map.put("cf_testcase","无");
        map.put("cf_reproducesteps","无");
        map.put("cf_testresult","无");
        map.put("cf_expectresult","无");
        map.put("cf_testanalysis","---");
        map.put("cf_bugimpact","无");
        map.put("op_sys","Windows");
        map.put("priority","P3");
        map.put("assigned_to","leixuelian@tp-link.com.cn");
        map.put("rep_platform","PC");
        map.put("short_desc","nothing");
        map.put("component","ProductID_1_Comp");
        map.put("token","");
        new Thread(){
            @Override
            public void run() {
                FileUploader.upload("https://rdmobilebugzilla.tp-link.com.cn:8008/post_bug.cgi", new File(filename), map, new FileUploader.FileUploadListener() {
                    @Override
                    public void onProgress(long pro, double precent) {
                        Log.i("cky", precent+"");
                    }

                    @Override
                    public void onFinish(int code, String res, Map<String, List<String>> headers) {
                        Log.i("cky", res);
                    }
                },cookie);
            }
        }.start();
    }


}
