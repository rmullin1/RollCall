package edu.westga.cs6242.rollcall;

import org.junit.Test;

import static org.junit.Assert.*;
import edu.westga.cs6242.rollcall.model.Student;

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
        student.setFirstName("Cindy");
        student.setLastName("Clark");
        assertEquals("StudentNo: 1, StudentId: S01, StudentName: Cindy Clark",
                student.toString());
    }

}