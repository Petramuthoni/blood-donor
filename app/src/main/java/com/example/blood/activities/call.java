package com.example.blood.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.R;

public class call extends AppCompatActivity {
    TextInputEditText phoneno;
    Button c;
    ImageView image;
    TextView t;
    String sNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        t=(TextView)findViewById(R.id.text);
        phoneno=(TextInputEditText)findViewById(R.id.phone);
        c=(Button)findViewById(R.id.btncall);
        image=(ImageView)findViewById(R.id.logo);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_CALL);
                sNum=phoneno.getText().toString();
                if(sNum.trim().isEmpty()){
                    i.setData(Uri.parse("tel: 7788994455"));
                }
                else{
                    i.setData(Uri.parse("tel:"+sNum));
                }
                if(ActivityCompat.checkSelfPermission(call.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(call.this,"please grant the permission to call",Toast.LENGTH_SHORT).show();
                    requestPermission();

                }
                else{
                    startActivity(i);
                }

            }

            private void requestPermission() {
                ActivityCompat.requestPermissions(call.this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });



    }
}
