package edu.westga.cs6242.rollcall;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.westga.cs6242.rollcall.model.SchoolClass;

/**
 * Unit Tests for the AddendanceRecord class
 */

/**
 * Unit Tests ofr the SchoolClass class
 */

public class SchoolClassUnitTest {

    @Test
    public void TestSchoolClass()
    {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassNo(1);
        schoolClass.setClassId("M101");
        schoolClass.setClassName("Math 101");
        assertEquals("ClassNo: 1, ClassId: M101, ClassName: Math 101",
                schoolClass.toString());
    }

}
