package com.example.hansel_christopher.engagerpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amardeep on 10/26/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "loopwiki.com";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";
    public static final String TABLE_COMPLAINTS = "complaints";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    public static final String KEY_IDC = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_TITLE = "title";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DESC = "descr";
    public static final String KEY_DEPT = "dept";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_UID = "uid";
    private static final String TABLE_TAG = "tags";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";
    public static final String SQL_TABLE_COMPLAINTS = " CREATE TABLE " + TABLE_COMPLAINTS
            + " ( "
            + KEY_IDC + " INTEGER PRIMARY KEY, "
            + KEY_TITLE + " TEXT, "
            + KEY_DESC + " TEXT, "
            + KEY_DEPT + " TEXT, "
            + KEY_PHONE + " INTEGER, "
            + KEY_UID + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_COMPLAINTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }


    public void addComplaint(Complaint complaint) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_TITLE, complaint.title);

        //Put email in  @values
        values.put(KEY_DESC, complaint.descr);

        //Put password in  @values
        values.put(KEY_DEPT, complaint.dept);
        values.put(KEY_PHONE, complaint.num);
        values.put(KEY_UID, complaint.uid);

        // insert row
        long todo_id = db.insert(TABLE_COMPLAINTS, null, values);
    }


    public List<Complaint> getAllComplaints() {
        List<Complaint> todos = new ArrayList<Complaint>();

        String selectQuery = "SELECT  * FROM " + TABLE_COMPLAINTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Complaint td = new Complaint();
                td.setId(c.getString((c.getColumnIndex(KEY_IDC))));
                td.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                td.setDept(c.getString(c.getColumnIndex(KEY_DEPT)));
                td.setDescr(c.getString(c.getColumnIndex(KEY_DESC)));
                td.setUid(c.getString(c.getColumnIndex(KEY_UID)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }



    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}