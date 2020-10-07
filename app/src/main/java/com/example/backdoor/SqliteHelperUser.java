package com.example.backdoor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelperUser extends SQLiteOpenHelper {


    //DATABASE NAME
    public static final String DATABASE_NAME_USER = "userDataBase";
    //DATABASE VERSION
    public static final int DATABASE_VERSION_USER = 1;
    //TABLE NAME
    public static final String TABLE_USERS_USER = "users";
    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID_USER = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME_USER = "username";
    //COLUMN email
    public static final String KEY_EMAIL_USER = "email";
    //COLUMN password
    public static final String KEY_PASSWORD_USER = "password";


    public SqliteHelperUser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME_USER, null, DATABASE_VERSION_USER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table when oncreate gets called
        db.execSQL(" CREATE TABLE " + TABLE_USERS_USER
                + " ( "
                + KEY_ID_USER + " INTEGER PRIMARY KEY, "
                + KEY_USER_NAME_USER + " TEXT, "
                + KEY_EMAIL_USER + " TEXT, "
                + KEY_PASSWORD_USER + " TEXT"
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table to create new one if database version updated
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS_USER);
    }

    //using this method we can add users to user table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME_USER, user.userNameUser);
        values.put(KEY_EMAIL_USER, user.emailUser);
        values.put(KEY_PASSWORD_USER, user.passwordUser);
        long todo_id = db.insert(TABLE_USERS_USER, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS_USER,// Selecting Table
                new String[]{KEY_ID_USER, KEY_USER_NAME_USER, KEY_EMAIL_USER, KEY_PASSWORD_USER},//Selecting columns want to query
                KEY_EMAIL_USER + "=?",
                new String[]{user.emailUser},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.passwordUser.equalsIgnoreCase(user1.passwordUser)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExistsUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS_USER,// Selecting Table
                new String[]{KEY_ID_USER, KEY_USER_NAME_USER, KEY_EMAIL_USER, KEY_PASSWORD_USER},//Selecting columns want to query
                KEY_EMAIL_USER + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public boolean isPasswordExistsUser(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS_USER,// Selecting Table
                new String[]{KEY_ID_USER, KEY_USER_NAME_USER, KEY_EMAIL_USER, KEY_PASSWORD_USER},//Selecting columns want to query
                KEY_PASSWORD_USER + "=?",
                new String[]{password},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given password so return true
            return true;
        }

        //if password does not exist return false
        return false;
    }
}
