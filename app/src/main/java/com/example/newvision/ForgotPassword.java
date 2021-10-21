package com.example.newvision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword  extends AppCompatActivity {

    EditText email,newp;
    Button forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgor_layout);
        email = (EditText)findViewById(R.id.oldemail);
        newp = (EditText)findViewById(R.id.newpass);
         forgot = (Button)findViewById(R.id.bt_pass);

         forgot.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 boolean isUpdate = DataBaseHelper.updatepassword(email.getText().toString(),newp.getText().toString());
                 if (isUpdate == true){
                     Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                     startActivity(i1);
                 }
                 else
                     Toast.makeText(getApplicationContext(),"Somthing went wrong",Toast.LENGTH_LONG).show();
             }
         });
    }

    }
