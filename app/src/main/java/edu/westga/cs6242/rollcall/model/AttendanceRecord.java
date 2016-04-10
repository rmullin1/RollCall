package edu.westga.cs6242.rollcall.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class AttendanceRecord
 */
public class AttendanceRecord {
    public int attendanceRecordNo;
    public Date attendanceDate;
    public SchoolClass schoolClass;
    public Student student;
    public boolean wasPresent;

    /**
     * ACCESSSORS
     */

    public int getAttendanceRecordNo() {
        return this.attendanceRecordNo;
    }

    public void setAttendanceRecordNo(int value) {
        this.attendanceRecordNo = value;
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
        return String.format("Date: %s, Class: %s, Student: %s, %s",
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.attendanceDate),
                this.schoolClass.getClassName(),
                this.student.getStudentName(), this.wasPresent ? "Present" : "Absent");
    }


}//class
