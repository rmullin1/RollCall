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

/**
 * Unit Tests ofr the SchoolClass class
 */

public class StudentUnitTest {

    @Test
    public void TestSchoolClass()
    {
        Student student = new Student();
        student.setStudentNo(1);
        student.setStudentId("S01");
        student.setStudentFirstName("Cindy");
        student.setStudentLastName("Clark");
        assertEquals("StudentNo: 1, StudentId: S01, StudentName: Cindy Clark",
                student.toString());
    }

}