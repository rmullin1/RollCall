package edu.westga.cs6242.rollcall;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.westga.cs6242.rollcall.model.Enrollment;

/**
 * Unit Tests ofr the Enrollment class
 */

public class EnrollmentUnitTest {

    @Test
    public void TestEnrollment()
    {
        Enrollment enrollment = new Enrollment();
        enrollment.setSchoolClassNo(2);
        enrollment.setStudentNo(3);
        assertEquals("SchoolClassNo: 2, StudentNo: 3", enrollment.toString());
    }

}