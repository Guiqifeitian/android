package com.example.guishaoli.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FileBugs extends AppCompatActivity implements View.OnClickListener {

    private Button testpecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_bugs);

        testpecker = (Button) findViewById(R.id.testpecker);

        assert  testpecker != null;

        testpecker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.testpecker:
                Intent intent = new Intent(FileBugs.this,SubmitBugs.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
