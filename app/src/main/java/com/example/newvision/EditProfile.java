package com.example.newvision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile  extends AppCompatActivity {

    TextView textView,txtuname,txtuid;
    EditText etname,etaddress,etoccupation,etmobile,etemail,etgender,etpass;

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    SharedPreferences prf,pref;

    Button edit;
    private static final String DB_NAME = "mydatabase.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        textView = (TextView)findViewById(R.id.textView011);
        edit = (Button)findViewById(R.id.bt_update);
        txtuname = (TextView)findViewById(R.id.username);
        txtuid = (TextView)findViewById(R.id.uid);
        etname = (EditText)findViewById(R.id.name);
        etaddress = (EditText)findViewById(R.id.address);
        etoccupation = (EditText)findViewById(R.id.zip);
        etemail = (EditText)findViewById(R.id.email);
        etmobile = (EditText)findViewById(R.id.mobile);
        etgender = (EditText)findViewById(R.id.gender);
        etpass = (EditText)findViewById(R.id.password);


        dbhelper  = DataBaseHelper.getInstance(this,DB_NAME);
        db = dbhelper.getReadableDatabase();
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        pref  = getSharedPreferences("edit_details",MODE_PRIVATE);

        Intent i = getIntent();
        final String username = i.getStringExtra("uname");
        final String password = i.getStringExtra("pass");
        Log.e("Second Screen",username + "." + password);

        cursor = db.rawQuery(String.format("SELECT *FROM %s WHERE %s=? AND %s=?",DataBaseHelper.TABLE_NAME,DataBaseHelper.COLUMN_NAME,
                DataBaseHelper.COLUMN_PASSWORD), new String[]{username,password});

        if(cursor != null){
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                String _uid = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_UID));
                String _username = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_USERNAME));
                String _zip = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_OCCUPATION));
                String _address = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_ADDRESS));
                String _email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_EMAIL));
                String _mobile = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_MOBILE));
                String _gender = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_GENDER));


                txtuname.setText(username);
                etpass.setText(password);
                etoccupation.setText(_zip);
                etaddress.setText(_address);
                etmobile.setText(_mobile);
                etgender.setText(_gender);
                txtuid.setText(_uid);
                etname.setText(_username);
                etemail.setText(_email);
            }
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                String userid = txtuid.getText().toString();
                String name = etname.getText().toString();
                String address = etaddress.getText().toString();
                String occupation = etoccupation.getText().toString();
                String mobile = etmobile.getText().toString();
                String email = etemail.getText().toString();
                String gender = etgender.getText().toString();
                String pass = etpass.getText().toString();

                boolean isUpdate = DataBaseHelper.updateData(userid,name,address,email,occupation,pass,mobile,
                        gender);
                if (isUpdate == true){

                Intent i1 = new Intent(getApplicationContext(),UpdateProfile.class);

                i1.putExtra("uname",username);
                i1.putExtra("pass",pass);
              //  SharedPreferences.Editor editor = pref.edit();
                //editor.putString("userid",userid);
                //editor.putString("name",name);
                //editor.putString("address",address);
                //editor.putString("zip",zip);
                //editor.putString("mobile",mobile);
                //editor.putString("email",email);
                //editor.putString("stream",stream);
                //editor.putString("gender",gender);
                //editor.putString("pass",pass);

                //editor.commit();
                startActivity(i1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Somthing went wrong",Toast.LENGTH_LONG).show();

            }
           });


    }
}
