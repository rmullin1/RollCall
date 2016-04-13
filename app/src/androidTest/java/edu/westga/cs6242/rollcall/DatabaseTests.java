package edu.westga.cs6242.rollcall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

import edu.westga.cs6242.rollcall.model.*;
import edu.westga.cs6242.rollcall.dbaccess.*;

/**
 * Created by Wayne on 4/12/2016.
 */
public class DatabaseTests extends ActivityInstrumentationTestCase2<MainActivity> {

    private DbHandler dbHandler = null;
    private SchoolClass schoolClass = null;
    private Student student = null;
    private Attendance attendance = null;
    private DbAccess dbAccess = null;

    public DatabaseTests() {
        super(MainActivity.class);
    }//constructor

    public void setUp(boolean newDatabase) throws Exception {
        Context context = getInstrumentation().getTargetContext().getApplicationContext();
        if (newDatabase)
            context.deleteDatabase(DbHandler.DATABASE_NAME);
        dbHandler = new DbHandler(context);
        dbAccess = new DbAccess();

    }//setUp()

    //test MainActivity exists
    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }//testMainActivityExists()

    //test database Construction
    public void testDatabaseConstruction() throws Exception {
        try {
            setUp(true);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testDatabaseConstruction()

    //----------------------------------------------------------------------------------------
    //test adding School Class to database
    public void testSchoolClassTableAdd() {
        try {
            setUp(false);
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setClassId("M103");
            schoolClass.setClassName("Math 103");
            //DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            DbHandler handler = new DbHandler(App.getContext());

            SQLiteDatabase db = dbHandler.getWritableDatabase();
            int classNo = dbAccess.addSchoolClass(db, schoolClass);
            assertTrue(classNo > 0);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableInsert()

    //test fetch School Class from database by PK
    public void testSchoolClassTableGetByNo() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            SchoolClass schoolClass = dbAccess.getSchoolClassByNo(db, 1);
            assertEquals(schoolClass.getClassName(), "Math 103");
            assertEquals(schoolClass.getClassId(), "M103");
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableGetById()

    //----------------------------------------------------------------------------------------
    public void testStudentTableAdd() {
        try {
            setUp(false);
            Student student = new Student();
            student.setStudentId("S10");
            student.setFirstName("John");
            student.setLastName("Smith");
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());

            SQLiteDatabase db = dbHandler.getWritableDatabase();
            int studentNo = dbAccess.addStudent(db, student);
            assertTrue(studentNo > 0);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableAdd()

    public void testStudentTableGetByNo() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            Student student = dbAccess.getStudentByNo(db, 1);
            assertEquals(student.getStudentId(), "S10");
            assertEquals(student.getFirstName(), "John");
            assertEquals(student.getLastName(), "Smith");
        } catch (Exception ex) {
            assertTrue(false);
        }
        assertTrue(true);
    }//testSchoolClassTableQuery()

    //Note: 'X' in name forces it to run after the previous tests -- alphabetic
    public void testXAttendanceTableAdd() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            int classNo = 1;
            int studentNo = 1;
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            int attendanceNo = dbAccess.addAttendance(db, new Date(), classNo, studentNo, true);
            assertTrue(attendanceNo > 0);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testAttendanceTableAdd()

    //Note: 'X' in name forces it to run after the previous tests -- alphabetic
    public void testXAttendanceTableGetByNo() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            Attendance attendance = dbAccess.getAttendanceByNo(db, 1);
            SchoolClass schoolClass = attendance.getSchoolClass();
            Student student = attendance.getStudent();
            //this test could fail if the minute rolls over between entering the record and now, rerun on failure
            assertEquals(DbAccess.DateToString(attendance.getAttendanceDate()), DbAccess.DateToString(new Date()));
            assertEquals(attendance.getWasPresent(), true);
            assertEquals(schoolClass.getClassNo(), 1);
            assertEquals(schoolClass.getClassName(), "Math 103");
            assertEquals(schoolClass.getClassId(), "M103");
            assertEquals(student.getStudentNo(), 1);
            assertEquals(student.getStudentId(), "S10");
            assertEquals(student.getFirstName(), "John");
            assertEquals(student.getLastName(), "Smith");
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableQuery()

}
