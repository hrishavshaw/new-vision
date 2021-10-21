package com.example.newvision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase sqLiteDb;
    private static DataBaseHelper instance;
    private static final String DB_NAME = "mydatabase.sqlite";
    static final int DB_VERSION = 1;
    private Context context;
    static Cursor cursor = null;
    public static final String TABLE_NAME = "information";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_UID = "id";
    public static final String COLUMN_USERNAME = "Name";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_OCCUPATION = "Occupation";
    public static final String COLUMN_MOBILE = "Mobile";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_GENDER = "Gender";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static void initialize(Context context, String databaseName) {

        if (instance == null) {

            if (!checkDatabase(context, databaseName)) {

                try {
                    copyDatabase(context, databaseName);
                } catch (IOException e) {
                    System.out.println(databaseName + "does not exists");
                }
            }
            instance = new DataBaseHelper(context);
            sqLiteDb = instance.getWritableDatabase();
            System.out.println("instance of  " + databaseName + " created ");
        }
    }

    public static final DataBaseHelper getInstance(Context context, String databaseName) {
        initialize(context, databaseName);
        return instance;
    }

    public SQLiteDatabase getDatabase() {
        return sqLiteDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private  static  void copyDatabase(Context aContext,String databaseName) throws IOException{
        InputStream myInput = aContext.getAssets().open(databaseName);
        String outFileName = getDatabasePath(aContext,databaseName);

        File f = new File("/data/data/" + aContext.getPackageName()+ "/databases/");
        if(!f.exists()) f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
        System.out.println(databaseName + "copied");
    }

    public static boolean checkDatabase(Context aContext,String databaseName){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = getDatabasePath(aContext,databaseName);
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        }catch (SQLException e){
            System.out.println(databaseName + "does not exists");
        }
        return checkDB != null ? true : false;
    }
    private static String getDatabasePath(Context aContext, String databaseName){
        return "/data/data/" + aContext.getPackageName() + "/databases/" + databaseName;
    }
    public static Cursor rawQuery(String query){
        try {
            if (sqLiteDb.isOpen()){
                sqLiteDb.close();
            }
            sqLiteDb = instance.getWritableDatabase();
            cursor = null;
            cursor = sqLiteDb.rawQuery(query,null);

        }catch (Exception e){
            System.out.println("DB ERROR  " + e.getMessage());
            e.printStackTrace();
        }
        return cursor;
    }
    public static Cursor getAllData(){
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }
    public static Cursor getData(){
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }
    public static String execute(String query){
        try {
            if (sqLiteDb.isOpen()){
                sqLiteDb.close();
            }
            sqLiteDb = instance.getWritableDatabase();
            sqLiteDb.execSQL(query);
        }catch (Exception e){
            System.out.println("DD ERROR  " + e.getMessage());
            e.printStackTrace();
        }
        return query;
    }
    public static boolean updateData(String id,String name,String address,String email,String occupation,String password,String mobile,
                                     String gender){

        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_UID,id);
        contentValues.put(COLUMN_USERNAME,name);
        contentValues.put(COLUMN_ADDRESS,address);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_OCCUPATION,occupation);
        contentValues.put(COLUMN_PASSWORD,password);
        contentValues.put(COLUMN_MOBILE,mobile);
        contentValues.put(COLUMN_GENDER,gender);


        db.update(TABLE_NAME,contentValues,"id = ?",new String[] { id });
        return true;
    }
    public static boolean updatepassword(String email,String newp){
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_PASSWORD,newp);
        db.update(TABLE_NAME,contentValues,"Email = ?",new String[]{email});
        return true;
    }
}

