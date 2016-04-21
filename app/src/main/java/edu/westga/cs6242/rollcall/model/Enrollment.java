package edu.westga.cs6242.rollcall.model;

/**
 * Represents a Student Entrolled in a specific School Class
 */
public class Enrollment {
    private int schoolClassNo;
    private int studentNo;

    public int getSchoolClassNo() {
        return this.schoolClassNo;
    }

    public void setSchoolClassNo(int value) {
        this.schoolClassNo = value;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(int value) {
        this.studentNo = value;
    }

    @Override
    public String toString() {
        return String.format("SchoolClassNo: %d, StudentNo: %d",
                this.getSchoolClassNo(), this.getStudentNo());
    }
}
