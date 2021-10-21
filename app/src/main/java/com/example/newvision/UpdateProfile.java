package com.example.newvision;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfile extends AppCompatActivity {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    String _uname,_pass;
    Button ok;
    SharedPreferences prf,pref;
    TextView update,textView,txtuid,txtname,txtaddress,txtoccupation,txtmob,txtemail,txtgender,txtuname,txtpass;
    private static final String DB_NAME = "mydatabase.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        textView = (TextView)findViewById(R.id.update);
        ok = (Button)findViewById(R.id.bt_Ok);

        txtuname = (TextView)findViewById(R.id.up_username);
        txtpass = (TextView)findViewById(R.id.up_password);
        txtuid = (TextView)findViewById(R.id.up_id);
        txtname = (TextView)findViewById(R.id.up_name);
        txtaddress = (TextView)findViewById(R.id.up_add);
        txtoccupation = (TextView)findViewById(R.id.up_zip);
        txtmob = (TextView)findViewById(R.id.up_mob);
        txtemail = (TextView)findViewById(R.id.up_email);
        txtgender = (TextView)findViewById(R.id.up_gender);

        dbhelper = DataBaseHelper.getInstance(this,DB_NAME);
        db = dbhelper.getReadableDatabase();
        pref = getSharedPreferences("edit_details",MODE_PRIVATE);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);

       // String username = prf.getString("username",null);
        //String password = prf.getString("password",null);

      //  String uid = prf.getString("userid",null);
       // String name = prf.getString("name",null);
        //String address = prf.getString("address",null);
        //String zip = prf.getString("zip",null);
        //String mobile = prf.getString("mobile",null);
        //String email = prf.getString("email",null);
        //String stream = prf.getString("stream",null);
        //String gender = prf.getString("gender",null);
        //String pass = prf.getString("pass",null);

       // boolean isUpdate = DataBaseHelper.updateData(uid,name,address,zip,mobile,email,stream,gender,pass);

       // if (isUpdate == true){

        Intent i = getIntent();
        String username = i.getStringExtra("uname");
        String password = i.getStringExtra("pass");
        Log.e("Second Screen",username+ "." + password);

        cursor = db.rawQuery(String.format("SELECT *FROM %s WHERE %s=? AND %s=?",DataBaseHelper.TABLE_NAME,DataBaseHelper.COLUMN_NAME,
                    DataBaseHelper.COLUMN_PASSWORD),new String[]{username,password} );
            if (cursor != null){
                if (cursor.getCount() > 0){
                    cursor.moveToFirst();

                    _uname = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
                    _pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PASSWORD));

                    String _uid = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_UID));
                    String _username = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_USERNAME));
                    String _zip = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_OCCUPATION));
                    String _address = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_ADDRESS));
                    String _email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_EMAIL));
                    String _mobile = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_MOBILE));
                    String _gender = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_GENDER));

                    txtuname.setText(_uname);
                    txtpass.setText(_pass);
                    txtaddress.setText(_address);
                    txtoccupation.setText(_zip);
                    txtmob.setText(_mobile);
                    txtgender.setText(_gender);

                    txtuid.setText(_uid);
                    txtname.setText(_username);
                    txtemail.setText(_email);

                }
            }
ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UpdateProfile.this,MenuActivity.class);
        intent.putExtra("uname",_uname);
        intent.putExtra("pass",_pass);

        startActivity(intent);

    }
});


    }


}
