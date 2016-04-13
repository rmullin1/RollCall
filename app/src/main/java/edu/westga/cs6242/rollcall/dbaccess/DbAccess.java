package edu.westga.cs6242.rollcall.dbaccess;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.westga.cs6242.rollcall.model.*;
/**
 * Created by Wayne on 4/12/2016.
 */
public class DbAccess {

    /**
     * DateToString()
     *      Converts a Date value to a string for SqlLite database
     * @param date - value;
     * @return - string equivalent in ISO standard format
     */
    public static String DateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }

    /**
     * StringToDate()
     *      Converts a String value to a Date for SqlLite database
     * @param dateString - ISO standard date
     * @return  - Date equivalent of string
     * @throws ParseException
     */
    public static Date StringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.parse(dateString);
    }

    /**
     * addSchoolClass()
     *      Adds a new SchoolClass entry to the database
     * @param obj - SchoolClass object to add
     * @return - returns the new classNo for the entry
     */
    public int addSchoolClass(SQLiteDatabase db, SchoolClass obj) {
        try {
            ContentValues values = new ContentValues();
            values.put("classId", obj.getClassId());
            values.put("className", obj.getClassName());
            int classNo = (int)db.insert(DbHandler.SCHOOLCLASS_TABLE_NAME, null, values);
            db.close();
            obj.setClassNo(classNo);
            return classNo;

        } catch (Exception ex) {
            throw ex;
        }
        finally {
            if (db != null)
                db.close();
        }
    }//addSchoolClass()

    /**
     * getSchoolClassByNo()
     *      Gets a SchoolClass object specified by the classNo.
     * @param db - database
     * @param classNo - classNo
     * @return - matching class object
     */
    public SchoolClass getSchoolClassByNo(SQLiteDatabase db, int classNo) {
        SchoolClass schoolClass = new SchoolClass();
        String sql =
                "SELECT * FROM SchoolClass WHERE classNo = ?";

        try {
            String[] args = {Integer.toString(classNo)};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                schoolClass.setClassNo(classNo);
                schoolClass.setClassId(cursor.getString(1));
                schoolClass.setClassName(cursor.getString(2));
            }
            return schoolClass;
        } catch (Exception ex) {
            throw ex;
        }
    }//getSchoolClassByNo()


    /**
     * addStudent()
     *      Adds a new Student entry to the database
     * @param obj - Student object to add
     * @return - returns the new studentNo for the entry
     */
    public int addStudent(SQLiteDatabase db, Student obj) {
        try {
            ContentValues values = new ContentValues();
            values.put("studentId", obj.getStudentId());
            values.put("studentFirstName", obj.getFirstName());
            values.put("studentLastName", obj.getLastName());
            int studentNo = (int)db.insert(DbHandler.STUDENT_TABLE_NAME, null, values);
            db.close();
            obj.setStudentNo(studentNo);
            return studentNo;
        } catch (Exception ex) {
            throw ex;
        }
        finally {
            if (db != null)
                db.close();
        }
    }//AddStudent()

    /**
     * getStudentByNo()
     *      Gets a Student object specified by the studentNo.
     * @param db - database
     * @param studentNo - studentNo
     * @return - matching class object
     */
    public Student getStudentByNo(SQLiteDatabase db, int studentNo) {
        Student student = new Student();
        String sql =
                "SELECT * FROM Student WHERE studentNo = ?";

        try {
            String[] args = {Integer.toString(studentNo)};
            Cursor cursor = db.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                student.setStudentNo(studentNo);
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
     * addAttendanceRecord()
     *      Adds a new Attendance entry to the database
     * @param date - date of the Attendance
     * @param classNo - classNo for the class
     * @param studentNo - studentno for the student
     * @return - returns the new AttendanceNo for the entry
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
     * @param db - database
     * @param recordNo - recordNo
     * @return - matching class object
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

    /**
     * DeleteSchoolClass()
     * @param classNo - classNo to delete
     */
    public void DeleteSchoolClass(int classNo) {
        SQLiteDatabase db = null;
        try {
            String sql =
                    "DELETE FROM ? WHERE classNo = ?";
//            Object[] args = {SCHOOLCLASS_TABLE_NAME, classNo};
//            db = this.getWritableDatabase();
//            db.execSQL(sql, args);
        } catch (Exception ex) {
            throw ex;
        }
    }//DeleteSchoolClass()

}//class
