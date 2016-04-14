package edu.westga.cs6242.rollcall.model;

/**
 * Created by Wayne on 4/13/2016.
 */
public class AttendanceLine {
    private int studentNo;
    private String studentName;
    private boolean isPresent;

    public AttendanceLine(String name) {
        this.studentName = name;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(int value) {
        this.studentNo = value;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String value) {
        this.studentName = value;
    }

    public boolean getIsPresent() {
        return this.isPresent;
    }

    public void setIsPresent(boolean value) {
        this.isPresent = value;
    }

}//class
