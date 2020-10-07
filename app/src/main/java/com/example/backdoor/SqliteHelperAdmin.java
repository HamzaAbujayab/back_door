package com.example.backdoor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelperAdmin extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME_ADMIN = "adminDataBase";
    //DATABASE VERSION
    public static final int DATABASE_VERSION_ADMIN = 2;
    //TABLE NAME
    public static final String TABLE_ADMIN= "admins";
    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID_ADMIN = "idAdmin";
    //COLUMN user name
    public static final String KEY_USER_NAME_ADMIN = "username";
    //COLUMN email
    public static final String KEY_EMAIL_ADMIN = "email";
    //COLUMN password
    public static final String KEY_PASSWORD_ADMIN = "password";



    public SqliteHelperAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME_ADMIN, null, DATABASE_VERSION_ADMIN);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table when oncreate gets called
        db.execSQL(" CREATE TABLE " + TABLE_ADMIN
                + " ( "
                + KEY_ID_ADMIN + " INTEGER PRIMARY KEY, "
                + KEY_USER_NAME_ADMIN + " TEXT, "
                + KEY_EMAIL_ADMIN + " TEXT, "
                + KEY_PASSWORD_ADMIN + " TEXT"
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table to create new one if database version updated
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ADMIN);
    }

    public void addAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME_ADMIN, admin.userNameAdmin);
        values.put(KEY_EMAIL_ADMIN, admin.emailAdmin);
        values.put(KEY_PASSWORD_ADMIN, admin.passwordAdmin);
        long todo_id = db.insert(TABLE_ADMIN, null, values);
    }

    public Admin Authenticate(Admin admin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,// Selecting Table
                new String[]{KEY_ID_ADMIN, KEY_USER_NAME_ADMIN, KEY_EMAIL_ADMIN, KEY_PASSWORD_ADMIN},//Selecting columns want to query
                KEY_EMAIL_ADMIN + "=?",
                new String[]{admin.emailAdmin},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            Admin admin1 = new Admin(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (admin.passwordAdmin.equalsIgnoreCase(admin1.passwordAdmin)) {
                return admin1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExistsAdmin(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,// Selecting Table
                new String[]{KEY_ID_ADMIN, KEY_USER_NAME_ADMIN, KEY_EMAIL_ADMIN, KEY_PASSWORD_ADMIN},//Selecting columns want to query
                KEY_EMAIL_ADMIN + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public boolean isPasswordExistsAdmin(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,// Selecting Table
                new String[]{KEY_ID_ADMIN, KEY_USER_NAME_ADMIN, KEY_EMAIL_ADMIN, KEY_PASSWORD_ADMIN},//Selecting columns want to query
                KEY_EMAIL_ADMIN + "=?",
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
