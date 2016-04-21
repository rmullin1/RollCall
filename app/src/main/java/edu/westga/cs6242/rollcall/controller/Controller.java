package edu.westga.cs6242.rollcall.controller;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import edu.westga.cs6242.rollcall.DbHandler;
import edu.westga.cs6242.rollcall.dbaccess.*;
import edu.westga.cs6242.rollcall.model.*;

/**
 * Class Controller
 *      A controller for the RollCall application
 */
public class Controller {

    private DbHandler dbHandler;
    private DbAccess dbAccess;

    public Controller() {
        //delete existing database, for a fresh start
        //App.getContext().deleteDatabase(DbHandler.DATABASE_NAME);

        //create db access models
        this.dbHandler = new DbHandler(App.getContext());
        this.dbAccess = new DbAccess();

        //LOAD TEST DATA for testing or demo
        //loadTestData();
    }

    public DbHandler getDbHandler() {
        return this.dbHandler;
    }

    public DbAccess getDbAccess() {
        return this.dbAccess;
    }

    public SQLiteDatabase getReadableDatabase() {
        return getDbHandler().getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return getDbHandler().getWritableDatabase();
    }

    // ## DANGER ## deletes the current database
    public void deleteDatabase() {
        App.getContext().deleteDatabase("RollCall.db");
        this.dbHandler = new DbHandler(App.getContext());
    }

    // ## WARNING ## loads demo dat on top of any existing data.  Unless the database has
    // been deleted using the above method first, the outcome may product an unexpected
    // affect, especially on automated testing.
    public void loadDemoData() {
        this.loadTestData();
    }

    /**
     * Verifies that the specified ClassId does not already exist or else is associated
     * with the specified currentClassNo.
     */
    public boolean verifyUniqueClassId(String classId, int currentClassNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            int existingNo = dbAccess.getSchoolClassNoById(db, classId);
            if (existingNo > 0 && existingNo != currentClassNo)
                return false;
            return true;
        } catch (Exception ex) {
            String txt = ex.getMessage();
            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }//VerifyUniqueClassID()

    public boolean verifyUniqueClassId(String classId) {
        return verifyUniqueClassId(classId, 0);
    }

    //add new Student Class
    public int addNewClass(String classId, String className ) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            int classNo = dbAccess.addSchoolClass(db, classId, className);
            return classNo;
        } catch (Exception ex) {
            return -1;
        } finally {
            if(db != null)
                db.close();
        }
    }//addNewClass()

    public boolean updateClass(int classNo, String classId, String className) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            dbAccess.updateSchoolClass(db, classNo, classId, className);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if(db != null)
                db.close();
        }
    }//updateSchoolClass

    public boolean deleteClass(int classNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            dbAccess.deleteSchoolClass(db, classNo);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if(db != null)
                db.close();
        }
    }//deleteClass()

    public ArrayList<SchoolClass> getSchoolClassList() {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            return dbAccess.getClassList(db);
        } catch (Exception ex) {
            return null;
        } finally {
            if (db != null)
                db.close();
        }
    }//getSchoolClassList()

    // SchoolClass access methods
    //=============================================================================================
    // Student access methods

    /**
     * Verifies that the specified StudentId does not already exist or else is associated
     * with the specified currentStudentNo.
     */
    public boolean verifyUniqueStudentId(String studentId, int currentStudentNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            int existingNo = dbAccess.getStudentNoById(db, studentId);
            if (existingNo > 0 && existingNo != currentStudentNo)
                return false;
            return true;
        } catch (Exception ex) {
            String txt = ex.getMessage();
            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }//VerifyUniqueStudentId()

    public boolean verifyUniqueStudentId(String studentId) {
        return verifyUniqueStudentId(studentId, 0);
    }

    public int addNewStudent(String studentId, String firstName, String lastName ) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            int studentNo = dbAccess.addStudent(db, studentId, firstName, lastName);
            db.close();
            return studentNo;
        } catch (Exception ex) {
            return -1;
        } finally {
            if(db != null)
                db.close();
        }
    }//addNewStudent()

    public boolean updateStudent(int studentNo, String studentId, String firstName, String lastName) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            dbAccess.updateStudent(db, studentNo, studentId, firstName, lastName);
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if(db != null)
                db.close();
        }
    }//updateStudent

    public boolean deleteStudent(int studentNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            dbAccess.deleteStudent(db, studentNo);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if(db != null)
                db.close();
        }
    }//deleteStudent()

    public ArrayList<Student> getStudentList() {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            return dbAccess.getStudentList(db);
        } catch (Exception ex) {
            return null;
        } finally {
            if (db != null)
                db.close();
        }
    }//getStudentList()

    // Student Access Methods
    //=============================================================================================
    // Enrollment Access Methods

    public int addNewEnrollment(int classNo, int studentNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            boolean bb = dbAccess.addEnrollment(db, classNo, studentNo);
            return 1;
        } catch (Exception ex) {
            return -1;
        } finally {
            if(db != null)
                db.close();
        }
    }//addNewEnrollment()

    public boolean deleteEnrollment(int classNo, int studentNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            boolean bb = dbAccess.deleteEnrollment(db, classNo, studentNo);
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if(db != null)
                db.close();
        }
    }//deleteEnrollment()

    public ArrayList<Enrollment> getEnrollmentList() {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            return dbAccess.getEnrollmentList(db);
        } catch (Exception ex) {
            String err = ex.getMessage();
            return null;
        } finally {
            if (db != null)
                db.close();
        }
    }//getEnrollmentList()

    //gets a roll list to populate the call roll listview
    public ArrayList<AttendanceLine> getRollListByClass(int classNo) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            return dbAccess.getRollListByClass(db, classNo);
        } catch (Exception ex) {
            String err = ex.getMessage();
            return null;
        } finally {
            if (db != null)
                db.close();
        }
    }//getRollListByClassId

    public boolean setAttendanceRecords(Date date, int classNo, ArrayList<AttendanceLine> list) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getReadableDatabase();
            return dbAccess.setAttendanceRecords(db, date, classNo, list);
        } catch (Exception ex) {
            String err = ex.getMessage();
            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }//setAttendanceRecords()

    // Enrollment Access Methods
    //=============================================================================================
    // Attendance Access Methods

    public int addNewAttendance(Date date, int classNo, int studentNo, boolean wasPresent) {
        SQLiteDatabase db = null;
        try {
            db = dbHandler.getWritableDatabase();
            boolean bb = dbAccess.deleteEnrollment(db, classNo, studentNo);
            return 1;
        } catch (Exception ex) {
            return -1;
        } finally {
            if(db != null)
                db.close();
        }
    }//addNewAttendance()


    //LOAD TEST DATA for testing and demo
    private void loadTestData() {
        SQLiteDatabase db = this.getWritableDatabase();
        int key;
        //create three classes
        key = addNewClass("C001", "Class 1");
        key = addNewClass("C002", "Class 2");
        key = addNewClass("C003", "Class 3");

        //create 10 students
        key = addNewStudent("S001", "Abby", "Adams");
        key = addNewStudent("S002", "Betty", "Baker");
        key = addNewStudent("S003", "Charles", "Cline");
        key = addNewStudent("S004", "Dan", "Davis");
        key = addNewStudent("S005", "Ed", "Edwards");
        key = addNewStudent("S006", "Fran", "Frank");
        key = addNewStudent("S007", "Gail", "Gaines");
        key = addNewStudent("S008", "Hank", "Howard");
        key = addNewStudent("S009", "Ida", "Ives");
        key = addNewStudent("S010", "Jan", "James");

        //add enrollments
        key = addNewEnrollment(1,1);
        key = addNewEnrollment(1,2);
        key = addNewEnrollment(1,3);
        key = addNewEnrollment(1,4);
        key = addNewEnrollment(1,5);
        key = addNewEnrollment(2,4);
        key = addNewEnrollment(2,5);
        key = addNewEnrollment(2,6);
        key = addNewEnrollment(2,7);
        key = addNewEnrollment(2,8);
        key = addNewEnrollment(3,7);
        key = addNewEnrollment(3,8);
        key = addNewEnrollment(3,9);
        key = addNewEnrollment(3,10);

    }//loadTestData()

}//class
