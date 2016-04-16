package edu.westga.cs6242.rollcall.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class Attendance
 */
public class Attendance {
    public int attendanceNo;
    public Date attendanceDate;
    public SchoolClass schoolClass;
    public Student student;
    public boolean wasPresent;

    /**
     * ACCESSSORS
     */

    public int getAttendanceNo() {
        return this.attendanceNo;
    }

    public void setAttendanceNo(int value) {
        this.attendanceNo = value;
    }

    public Date getAttendanceDate() {
        return this.attendanceDate;
    }

    public void setAttendanceDate(Date value) {
        this.attendanceDate = value;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolClass getSchoolClass() {
        return this.schoolClass;
    }

    public void setSchoolClass(SchoolClass value) {
        this.schoolClass = value;
    }

    public boolean getWasPresent() {
        return this.wasPresent;
    }

    public void setWasPresent(boolean value) {
        this.wasPresent = value;
    }

    /**
     * toString()
     *      Override of Object.toString() to write out Attendance instance as string
     * @return - string representation of Attendance Record
     */
    @Override
    public String toString() {
        return String.format("AttendanceNo: %d, Date: %s, Class: %s, Student: %s, %s",
                this.getAttendanceNo(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.getAttendanceDate()),
                this.getSchoolClass().getClassName(),
                this.getStudent().getStudentName(), this.getWasPresent() ? "Present" : "Absent");
    }


}//class
