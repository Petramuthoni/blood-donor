package com.example.blood;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class message extends AppCompatActivity {
    TextInputEditText ed1,ed2;
    TextView t1;
    Button b1;
    ImageView i;
    public static final int MY_PERSSIONS_request_send_sms=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        t1 = (TextView) findViewById(R.id.text);
        ed1 = (TextInputEditText) findViewById(R.id.sms);
        ed2 = (TextInputEditText) findViewById(R.id.phone1);
        i = (ImageView) findViewById(R.id.logo);
        b1 = (Button) findViewById(R.id.btnsms);
    }
    public void sendSms(View view) {
        int permissioncheck= ContextCompat.checkSelfPermission(message.this, Manifest.permission.SEND_SMS);
        if(permissioncheck == PackageManager.PERMISSION_GRANTED){
            mymessage();
        }else{
            ActivityCompat.requestPermissions(message.this,new String[]{Manifest.permission.SEND_SMS},MY_PERSSIONS_request_send_sms);
        }
    }

            private void mymessage() {
                String myNumber=ed2.getText().toString();
                String mymsg=ed1.getText().toString();
                if(myNumber==null || myNumber.equals("")|| mymsg==null || mymsg.equals("")){
                    Toast.makeText(message.this,"Field cannot be empty",Toast.LENGTH_SHORT).show();
                }else{
                    if(TextUtils.isDigitsOnly(myNumber)){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(myNumber,null,mymsg,null,null);
                        Toast.makeText(message.this,"Message Sent ",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(message.this,"Please Enter Integer Only ",Toast.LENGTH_SHORT).show();


                    }
                }
            }

        @Override
        public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
            switch(requestCode){
                case MY_PERSSIONS_request_send_sms:
                {
                    if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        mymessage();
                    }else{
                        Toast.makeText(message.this,"No permission",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }
    }



