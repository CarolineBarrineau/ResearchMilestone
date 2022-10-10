package com.example.testproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ButtonHelper extends SQLiteOpenHelper {
    public static final String buttonName = "login.db";

    // constructors were given after making the extends
    public ButtonHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    public void onCreate(SQLiteDatabase loginDatabase){
        // creates a table that takes in the username, password, and email address
        loginDatabase.execSQL("create Table users (username TEXT primary key, email TEXT primary key, password TEXT primary key)");

    }
    public void onUpgrade(SQLiteDatabase loginDatabase, int i, int i1) {
        loginDatabase.execSQL("drop Table if exists users");
    }
    // insert data into the database
    public Boolean insertData(String username, String email_address, String password){
        SQLiteDatabase loginDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email_address);
        contentValues.put("password", password);
        long results = loginDatabase.insert("users", null, contentValues);
        if( results == -1)
            return false;
        else
            return true;
    }
    // checks for the username associated
    public Boolean checkusername(String username){
        SQLiteDatabase loginDatabase = this.getWritableDatabase();
        Cursor c = loginDatabase.rawQuery("select * from users where username = ?", new String[] {username});
        // sees if that username is in the database is in the database by counting it
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }
    // checks for the email associated
    public Boolean checkuseremail(String username, String email){
        SQLiteDatabase loginDatabase = this.getWritableDatabase();
        Cursor c = loginDatabase.rawQuery("select * from user where user = ? and email = ?", new String[] {username,email});
        // sees if that username and email is in the database by counting it
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }
    // checks all usernames for the pair of username and password that matches the login input
    public Boolean checkuserpass(String username, String password){
        SQLiteDatabase loginDatabase = this.getWritableDatabase();
        Cursor c = loginDatabase.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        // sees if that username and password is in the database is in the database by counting it
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }

}
