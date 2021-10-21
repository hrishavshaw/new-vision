package com.example.newvision;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
    }

    public void onSubmit(View v){
       final RadioButton rb = (RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        AlertDialog.Builder builder= new AlertDialog.Builder(FormActivity.this);
        builder.setTitle("Thank you NOTE:");
        builder.setMessage("Thank you for becoming "+rb.getText()+" and donating the things.Your contribution will be beneficial for Orphanage.Thank you for your help..");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FormActivity.this,"Now you are"+rb.getText(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.show();


    }
}
