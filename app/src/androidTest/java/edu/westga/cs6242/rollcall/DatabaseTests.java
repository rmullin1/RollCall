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
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            int classNo = dbAccess.addSchoolClass(db, "M103", "Math 103");
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
    }//testSchoolClassTableGetByNo()

    //test fetch School Class from database by Id
    public void testSchoolClassTableGetNoById() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            int classNo = dbAccess.getSchoolClassNoById(db, "M103");
            assertEquals(classNo, 1);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableGetNoById()

    //test update School Class
    public void testSchoolClassTableUpdate() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());
            SQLiteDatabase db = dbHandler.getReadableDatabase();
            boolean value = dbAccess.updateSchoolClass(db, 1, "X103", "MATHX 103");
            SchoolClass schoolClass = dbAccess.getSchoolClassByNo(db, 1);
            assertEquals(1, schoolClass.getClassNo());
            assertEquals("X103", schoolClass.getClassId());
            assertEquals("MATHX 103", schoolClass.getClassName());
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableGetNoById()

    //----------------------------------------------------------------------------------------
    public void testStudentTableAdd() {
        try {
            setUp(false);
            DbHandler handler = new DbHandler(getInstrumentation().getTargetContext());

            SQLiteDatabase db = dbHandler.getWritableDatabase();
            int studentNo = dbAccess.addStudent(db, "S10", "John", "Smith");
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
            assertEquals(schoolClass.getClassName(), "MATHX 103");
            assertEquals(schoolClass.getClassId(), "X103");
            assertEquals(student.getStudentNo(), 1);
            assertEquals(student.getStudentId(), "S10");
            assertEquals(student.getFirstName(), "John");
            assertEquals(student.getLastName(), "Smith");
        } catch (Exception ex) {
            assertTrue(false);
        }
    }//testSchoolClassTableQuery()

}
