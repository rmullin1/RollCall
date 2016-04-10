package edu.westga.cs6242.rollcall;

import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.*;
import edu.westga.cs6242.rollcall.model.AttendanceRecord;
import edu.westga.cs6242.rollcall.model.SchoolClass;
import edu.westga.cs6242.rollcall.model.Student;

/**
 * Unit Tests for the AddendanceRecord class
 */
public class AttendanceRecordUnitTest {

    private Student student;
    private SchoolClass schoolClass;
    private Calendar calendar;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private void setup() {
        this.schoolClass = new SchoolClass();
        this.schoolClass.setClassNo(1);
        this.schoolClass.setClassName("Math 101");

        this.student = new Student();
        this.student.setStudentNo(2);
        this.student.setStudentId("S11");
        this.student.setStudentFirstName("John");
        this.student.setStudentLastName("Smith");

        this.calendar = Calendar.getInstance();
        this.calendar.set(2016, Calendar.APRIL, 10, 18, 0);
    }

    @Test
    public void testToString1() {
        setup();
        AttendanceRecord rec = new AttendanceRecord();
        rec.setAttendanceDate(this.calendar.getTime());
        rec.setSchoolClass(this.schoolClass);
        rec.setStudent(this.student);
        rec.setWasPresent(true);
        assertEquals("Date: 2016-04-10 18:00, Class: Math 101, Student: John Smith, Present",
                rec.toString());
    }

    @Test
    public void testToString2() {
        setup();
        this.student.setStudentFirstName("Jill");
        this.student.setStudentLastName("Brown");
        this.schoolClass.setClassName("Math 102");
        AttendanceRecord rec = new AttendanceRecord();
        this.calendar.set(Calendar.HOUR_OF_DAY, 20);
        rec.setAttendanceDate(this.calendar.getTime());
        rec.setSchoolClass(this.schoolClass);
        rec.setStudent(this.student);
        rec.setWasPresent(false);

        assertEquals("Date: 2016-04-10 20:00, Class: Math 102, Student: Jill Brown, Absent",
                rec.toString());
    }

}