package com.example.newvision;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private TabLayout tabLayout;
    TextView textView;
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    String _uname,_pass;
    private static final String DB_NAME = "mydatabase.sqlite";
    String[] bankName= {"see more things","See Profile","Edit Your Profile","See Gallery","Log Out","Dial Child helpline"};
    SharedPreferences prf,pref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textView = (TextView)findViewById(R.id.identity);

        Spinner spin = (Spinner)findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa= new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankName);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        dbhelper = DataBaseHelper.getInstance(this,DB_NAME);
        db = dbhelper.getReadableDatabase();
        pref = getSharedPreferences("edit_details",MODE_PRIVATE);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent i = getIntent();
        String username = i.getStringExtra("uname");
        String password = i.getStringExtra("pass");
        Log.e("Second Screen",username+ "." + password);

        cursor = db.rawQuery(String.format("SELECT *FROM %s WHERE %s=? AND %s=?",DataBaseHelper.TABLE_NAME,DataBaseHelper.COLUMN_NAME,
                DataBaseHelper.COLUMN_PASSWORD),new String[]{username,password});
        if (cursor !=null){
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                _uname = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
                _pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PASSWORD));
                String _username = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_USERNAME));
                textView.setText(textView.getText() + _username);


            }
        }



        viewPager  = findViewById(R.id.pager);
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

            switch (position) {
               case 0:
                   break;


               case 1:
               {  Intent intent = new Intent(MenuActivity.this,ProfileActivity.class);
                   intent.putExtra("uname",_uname);
                   intent.putExtra("pass",_pass);

                   startActivity(intent);

                   break;
               }
                case 2:{
                    Intent intent = new Intent(MenuActivity.this,EditProfile.class);
                    intent.putExtra("uname",_uname);
                    intent.putExtra("pass",_pass);
                    startActivity(intent);
                    break;
                }

               case 3:{
                   Intent i = new Intent(MenuActivity.this,GalleryActivity.class);
                   startActivity(i);
               break;}

               case 4: {
                   AlertDialog.Builder builder= new AlertDialog.Builder(MenuActivity.this);
                   builder.setTitle("Two Alert");
                   builder.setMessage("Are you sure you want to logOut");
                   builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           Toast.makeText(getApplicationContext(),"You Logged Out..",Toast.LENGTH_LONG).show();


                           SharedPreferences.Editor editor = pref.edit();
                           editor.clear();
                           editor.commit();
                           Intent i = new Intent(getApplicationContext(),MainActivity.class);
                           startActivity(i);
                       }
                   });
                   builder.show(); break;}

                case 5:
                { Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1098"));
                   startActivity(intent);
                    break;}


            }


        }



    @Override
    public void onNothingSelected(AdapterView<?> arg0){}
}
