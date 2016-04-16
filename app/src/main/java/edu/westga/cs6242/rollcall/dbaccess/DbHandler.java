package edu.westga.cs6242.rollcall.dbaccess;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.westga.cs6242.rollcall.model.*;

/**
 * class DbHandler
 *     Creates, initializes, and maintains the RollCall database
 */
public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RollCall.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SCHOOLCLASS_TABLE_NAME = "SchoolClass";
    public static final String STUDENT_TABLE_NAME = "Student";
    public static final String ATTENDANCE_TABLE_NAME = "Attendance";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }//constructor

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//constructor(context)


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        try {
            sql =
                    "CREATE TABLE " + SCHOOLCLASS_TABLE_NAME + " ( " +
                            "classNo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "classId varChar(30) NOT NULL UNIQUE, " +
                            "className VARCHAR(30) NOT NULL)";
            db.execSQL(sql);

            sql =
                    "CREATE TABLE " + STUDENT_TABLE_NAME + "( " +
                            "studentNo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "studentId VARCHAR(30) UNIQUE NOT NULL, " +
                            "studentFirstName VARCHAR(30) NOT NULL, " +
                            "studentLastName VARCHAR(30) NOT NULL)";
            db.execSQL(sql);

            sql =
                    "CREATE TABLE " + ATTENDANCE_TABLE_NAME + "( " +
                            "attendanceNo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "attendanceDate VARCHAR(20) NOT NULL, " +
                            "studentNo INTEGER NOT NULL REFERENCES student(studentNo), " +
                            "classNo INTEGER NOT NULL REFERENCES studentClass(classNo), " +
                            "wasPresent BOOLEAN NOT NULL)";

            db.execSQL(sql);
        }//try
        catch (Exception ex) {
            throw ex;
        }
    }//onCreate


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCHOOLCLASS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTENDANCE_TABLE_NAME);
        onCreate(db);
    }//onUpdate



}//class
