package com.example.guishaoli.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

    String[] res = {"appearance","audio","bluetooth","display","earphone","fm","gps","input","memory","multimedia","MVB","pm","reliability","RF","self_test","sensor","sim","software","Speaker","usb","wlan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_bugs);

        testCases = (Spinner) findViewById(R.id.testcase);
        ifRecovery = (Spinner) findViewById(R.id.ifrecovery) ;
        caseName = (LinearLayout) findViewById(R.id.casename);
        recovery = (LinearLayout) findViewById(R.id.recovery);
        advancedFieldsBtn = (Button) findViewById(R.id.advancedfields);
        autoText = (AutoCompleteTextView) findViewById(R.id.autotextview);
        advancedfieldslin = (LinearLayout) findViewById(R.id.advancedfieldslin);
        attachment = (Button) findViewById(R.id.attachment);

        assert attachment != null;
        assert advancedfieldslin != null;
        assert autoText != null;
        assert advancedFieldsBtn != null;
        assert testCases != null;
//        assert caseName != null;

        advancedFieldsBtn.setOnClickListener(this);
        attachment.setOnClickListener(this);

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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void postFileWithParams(String url,String params,File file){
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", file.getName(), body);
        }

    }


}
