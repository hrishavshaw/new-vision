package com.example.newvision;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage  extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper dbhelper;
    Cursor cursor;
    private  static final String DB_NAME = "mydatabase.sqlite";
    Button btnsignin;
    TextView btnForgot,btnAdmin;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        final EditText _txtuname = (EditText)findViewById(R.id.txtemail);
        final EditText _txtpass = (EditText)findViewById(R.id.txtpass);
        Button _btnlogin = (Button)findViewById(R.id.btnsignin);
        TextView _btnreg = (TextView)findViewById(R.id.btnreg);
        TextView _btnForgot = (TextView)findViewById(R.id.btnforgot);
        //TextView _btnAdmin = (TextView)findViewById(R.id.btnadmin);

        dbhelper = DataBaseHelper.getInstance(this,DB_NAME);
        db = dbhelper.getReadableDatabase();

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _txtuname.getText().toString();
                String password = _txtpass.getText().toString();

                cursor = db.rawQuery(String.format("SELECT *FROM %s WHERE %s=? AND %s=?",DataBaseHelper.TABLE_NAME,DataBaseHelper.COLUMN_NAME,DataBaseHelper.COLUMN_PASSWORD),new String[] {username,password});
                if (cursor != null){
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        String _uname  = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
                        String _pass  = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PASSWORD));

                        Toast.makeText(LoginPage.this,"Login Success",Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.commit();

                        Intent intent = new Intent(LoginPage.this,MenuActivity.class);
                        intent.putExtra("uname",_uname);
                        intent.putExtra("pass",_pass);

                        startActivity(intent);
                        finish();
                    }
                    else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginPage.this);
                        builder.setTitle("Alert");
                        builder.setMessage("UserName or Password is wrong.. ");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,UserRegistration.class);
                startActivity(intent);
            }
        });

        _btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

    }
}
