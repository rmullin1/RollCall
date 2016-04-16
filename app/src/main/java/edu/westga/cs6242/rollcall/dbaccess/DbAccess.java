package edu.westga.cs6242.rollcall.dbaccess;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.westga.cs6242.rollcall.model.*;
/**
 * Created by Wayne on 4/12/2016.
 */
public class DbAccess {

    /**
     * DateToString()
     *      Converts a Date value to a string for SqlLite database
     */
    public static String DateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }

    /**
     * StringToDate()
     *      Converts a String value to a Date for SqlLite database
     */
    public static Date StringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.parse(dateString);
    }

    //=============================================================================================
    // SCHOOL CLASS ACCESS METHODS

    /**
     * addSchoolClass()
     *      Adds a new SchoolClass entry to the database
     */
    public int addSchoolClass(SQLiteDatabase db, String classId, String className) {
        try {
            ContentValues values = new ContentValues();
            values.put("classId", classId);
            values.put("className", className);
            int classNo = (int)db.insert(DbHandler.SCHOOLCLASS_TABLE_NAME, null, values);
            if (classNo < 1)
                throw new Exception("db.insert() < 1");
            return classNo;
        } catch (Exception ex) {
            return -1;
        }
    }//addSchoolClass()

    /**
     * getSchoolClassByNo()
     *      Gets a SchoolClass object specified by the classNo.
     */
    public SchoolClass getSchoolClassByNo(SQLiteDatabase db, int classNo) {
        SchoolClass schoolClass = null;
        String sql =
                "SELECT * FROM SchoolClass WHERE classNo = ?";
        try {
            String[] args = {Integer.toString(classNo)};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                schoolClass = new SchoolClass();
                schoolClass.setClassNo(cursor.getInt(0));
                schoolClass.setClassId(cursor.getString(1));
                schoolClass.setClassName(cursor.getString(2));
            }
            return schoolClass;
        } catch (Exception ex) {
            throw ex;
        }
    }//getSchoolClassByNo()

    /**
     * getSchoolClassNoById
     *      Gets the schoolClassNo specified by the classId
     */
    public int getSchoolClassNoById (SQLiteDatabase db, String classId){
        int schoolClassNo = -1;
        String sql =
                "SELECT * FROM SchoolClass WHERE classId = ?";

        try {
            String[] args = {classId};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                schoolClassNo = cursor.getInt(0);
            }
            return schoolClassNo;
        } catch (Exception ex) {
            throw ex;
        }
    }//getSchoolClassNoById

    /**
     * updateSchoolClass()
     *      Updates SchoolClass entry to the database
     */
    public boolean updateSchoolClass(SQLiteDatabase db, int classNo, String classId, String className) {
        try {
            String sql =
                    "UPDATE SchoolClass " +
                    "SET classId = ?, className = ? " +
                    "WHERE classNo = ?";
            Object[] args = {classId, className, classNo};
            db.execSQL(sql, args);
            return true;

        } catch (Exception ex) {
            throw ex;
        }
    }//updateSchoolClass()

    /**
     * deleteSchoolClass()
     *      Deletes the school class entry specified by classNo
     */
    public void deleteSchoolClass(SQLiteDatabase db, int classNo) {
        try {
            String sql =
                    "DELETE FROM SchoolClass WHERE classNo = ?";
            Object[] args = {classNo};
            db.execSQL(sql, args);
        } catch (Exception ex) {
            throw ex;
        }
    }//deleteSchoolClass()

    public ArrayList<SchoolClass> getClassList(SQLiteDatabase db) {
        ArrayList<SchoolClass> list = new ArrayList<SchoolClass>();
        String sql = "SELECT * FROM SchoolClass ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setClassNo(cursor.getInt(0));
            schoolClass.setClassId(cursor.getString(1));
            schoolClass.setClassName(cursor.getString(2));
            list.add(schoolClass);
        }
        cursor.close();
        return list;
    }//getClassList()


    // SCHOOL CLASS ACCESS METHODS
    //=============================================================================================
    // STUDENT ACCESS METHODS

    /**
     * addStudent()
     */
    public int addStudent(SQLiteDatabase db, String studentId, String firstName, String lastName) {
        try {
            ContentValues values = new ContentValues();
            values.put("studentId", studentId);
            values.put("studentFirstName", firstName);
            values.put("studentLastName", lastName);
            int studentNo = (int)db.insert(DbHandler.STUDENT_TABLE_NAME, null, values);
            return studentNo;
        } catch (Exception ex) {
            throw ex;
        }
    }//AddStudent()

    /**
     * getStudentByNo()
     *      Gets a Student object specified by the studentNo.
     */
    public Student getStudentByNo(SQLiteDatabase db, int studentNo) {
        Student student = null;
        String sql =
                "SELECT * FROM Student WHERE studentNo = ?";

        try {
            String[] args = {Integer.toString(studentNo)};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                student = new Student();
                student.setStudentNo(cursor.getInt(0));
                student.setStudentId(cursor.getString(1));
                student.setFirstName(cursor.getString(2));
                student.setLastName(cursor.getString(3));
            }
            return student;
        } catch (Exception ex) {
            throw ex;
        }
    }//getStudentByNo()

    /**
     * getStudentNoById()
     *      Gets a StudentNo specified by the studentId
     */
    public int getStudentNoById(SQLiteDatabase db, String studentId) {
        int studentNo = -1;
        String sql =
                "SELECT * FROM Student WHERE studentId = ?";
        try {
            String[] args = {studentId};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                studentNo = cursor.getInt(0);
            }
            return studentNo;
        } catch (Exception ex) {
            throw ex;
        }
    }//getStudentByNo()

    /**
     * updateStudent()
     *      Updates Student entry to the database
     */
    public boolean updateStudent(SQLiteDatabase db, int studentNo,
                                 String studentId, String firstName, String lastName) {
        try {
            String sql =
                    "UPDATE Student " +
                            "SET studentId = ?, studentFirstName = ?, studentLastName = ? " +
                            "WHERE studentNo = ?";
            Object[] args = {studentId, firstName, lastName, studentNo};
            db.execSQL(sql, args);
            return true;
        } catch (Exception ex) {
            String x = ex.getMessage();
            throw ex;
        }
    }//updateStudent()

    /**
     * deleteStudent()
     *      Deletes the student entry specified by studentNo
     */
    public void deleteStudent(SQLiteDatabase db, int studentNo) {
        try {
            String sql =
                    "DELETE FROM Student WHERE studentNo = ?";
            Object[] args = {studentNo};
            db.execSQL(sql, args);
        } catch (Exception ex) {
            throw ex;
        }
    }//deleteStudent()

    public ArrayList<Student> getStudentList(SQLiteDatabase db) {
        ArrayList<Student> list = new ArrayList<Student>();
        String sql = "SELECT * FROM Student ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setStudentNo(cursor.getInt(0));
            student.setStudentId(cursor.getString(1));
            student.setFirstName(cursor.getString(2));
            student.setLastName(cursor.getString(3));
            list.add(student);
        }
        cursor.close();
        return list;
    }//getStudentList()

    // STUDENT ACCESS METHODS
    //=============================================================================================
    // ATTDNDANCE ACCESS METHODS

    /**
     * addAttendanceRecord()
     *      Adds a new Attendance entry to the database
     */
    public int addAttendance(SQLiteDatabase db,
                             Date date, int classNo, int studentNo, boolean wasPresent) {
        try {
            ContentValues values = new ContentValues();
            values.put("attendanceDate", DateToString(date));
            values.put("classNo", classNo);
            values.put("studentNo", studentNo);
            values.put("wasPresent", wasPresent);
            int attendanceNo = (int)db.insert(DbHandler.ATTENDANCE_TABLE_NAME, null, values);
            db.close();
            return attendanceNo;
        } catch (Exception ex) {
            throw ex;
        }
        finally {
            if (db != null)
                db.close();
        }
    }//addAttendance()

    /**
     * getAttendanceByNo()
     *      Gets an Attendance object specified by the recordNo.
     */
    public Attendance getAttendanceByNo(SQLiteDatabase db, int recordNo) throws ParseException {
        Attendance attendance = new Attendance();
        SchoolClass schoolClass = new SchoolClass();
        Student student = new Student();
        String sql =
                "SELECT * FROM Attendance ar WHERE ar.attendanceNo = ?";

        try {
            String[] args = {Integer.toString(recordNo)};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                attendance.setAttendanceNo(recordNo);
                attendance.setAttendanceDate(StringToDate(cursor.getString(1)));
                int classNo = cursor.getInt(2);
                int studentNo = cursor.getInt(3);
                attendance.setWasPresent(cursor.getInt(4) != 0);
                attendance.setSchoolClass(getSchoolClassByNo(db, classNo));
                attendance.setStudent(getStudentByNo(db, studentNo));
            }
            return attendance;
        } catch (Exception ex) {
            throw ex;
        }
    }//getAttendanceByNo()


}//class
