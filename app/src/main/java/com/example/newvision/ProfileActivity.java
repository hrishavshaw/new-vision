package com.example.newvision;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    TextView textView,txtuid,txtname,txtaddress,txtoccupation,txtmob,txtemail,txtgender,txtuname,txtpass;
    Button edit,menu;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    String _uname,_pass;
    private static final String DB_NAME = "mydatabase.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = (TextView)findViewById(R.id.TextViewUserEmail);


        txtuname=(TextView)findViewById(R.id.username);
        txtpass=(TextView)findViewById(R.id.upassword);
        txtuid=(TextView)findViewById(R.id.uid);
        txtname=(TextView)findViewById(R.id.uname);
        txtaddress=(TextView)findViewById(R.id.uadd);
        txtoccupation=(TextView)findViewById(R.id.uzip);
        txtmob=(TextView)findViewById(R.id.umob);
        txtemail=(TextView)findViewById(R.id.email);
        txtgender=(TextView)findViewById(R.id.ugender);


        dbhelper = DataBaseHelper.getInstance(this,DB_NAME);
        db = dbhelper.getReadableDatabase();
        Intent i = getIntent();
        String username = i.getStringExtra("uname");
        String password = i.getStringExtra("pass");
        Log.e("Second Screen",username+ "." + password);

        cursor = db.rawQuery(String.format("SELECT *FROM %s WHERE %s=? AND %s=?",DataBaseHelper.TABLE_NAME,DataBaseHelper.COLUMN_NAME,
                DataBaseHelper.COLUMN_PASSWORD),new String[]{username,password});
        if (cursor !=null){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                _uname = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
                _pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PASSWORD));

                String _uid=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_UID));
                String _username=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_USERNAME));
                String _zip=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_OCCUPATION));
                String _address=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_ADDRESS));
                String _email=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_EMAIL));
                String _mobile=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_MOBILE));
                String _gender=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_GENDER));

                txtuname.setText(_uname);
                txtpass.setText(_pass);
                txtaddress.setText(_address);
                txtoccupation.setText(_zip);
                txtmob.setText(_mobile);
                txtgender.setText(_gender);
                txtuid.setText(_uid);
                txtname.setText(_username);
                txtemail.setText(_email);
                textView.setText(textView.getText() + _username);
            }
        }







    }

    }
