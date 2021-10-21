package com.example.newvision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends Activity {
    private Button btSubmit;
    private Button btCancel;
    private EditText etUID;
    private EditText etName;
    private EditText etAddress;
    private EditText etOccupation;
    private EditText etMobile;
    private EditText etEmail;
    private EditText etGender;
    private EditText etUsername;
    private EditText etPassword;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);
       // textView1 = findViewById(R.id.textView1);
        //final Typeface f = Typeface.createFromAsset(getAssets(),"OpenSans-BoldItalic.ttf");
        //textView1.setTypeface(f);
        btSubmit = (Button)findViewById(R.id.bt_submit);
        btCancel = (Button)findViewById(R.id.bt_reg_cancel);
        etUID = (EditText)findViewById(R.id.ed_ud);
        etName = (EditText)findViewById(R.id.ed_name);
        etAddress = (EditText)findViewById(R.id.ed_add);
        etOccupation = (EditText)findViewById(R.id.ed_zip);
        etMobile = (EditText)findViewById(R.id.ed_mob);
        etEmail = (EditText)findViewById(R.id.ed_email);
        etGender = (EditText)findViewById(R.id.ed_gender);
        etUsername = (EditText)findViewById(R.id.ed_username);
        etPassword = (EditText)findViewById(R.id.ed_password);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertQuery = "INSERT INTO information VALUES('"
                        + etUID.getText().toString() + "'," + "'"
                        + etName.getText().toString() + "'," + "'"
                        + etAddress.getText().toString() + "','"
                        + etEmail.getText().toString() + "','"
                        + etOccupation.getText().toString() + "','"
                        + etUsername.getText().toString() + "','"
                        + etPassword.getText().toString() + "','"
                        + etMobile.getText().toString() + "','"
                        + etGender.getText().toString() + "')";


                String  rowInserted = DataBaseHelper.execute(insertQuery);

                if (rowInserted != null) {
                    Toast.makeText(getApplicationContext(),"New row added,row id: " + rowInserted,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserRegistration.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();



            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

}}
