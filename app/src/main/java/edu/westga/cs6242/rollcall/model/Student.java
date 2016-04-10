package edu.westga.cs6242.rollcall.model;

/**
 * class Student
 *      Represents a school student attending classes
 */
public class Student {
    int studentNo;
    String studentId;
    String studentFirstName;
    String studentLastName;

    /**
     * ACCESSSORS
     */
    public int getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(int value) {
        this.studentNo = value;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String value) {
        this.studentId = value;
    }

    public String getStudentFirstName() {
        return this.studentFirstName;
    }

    public void setStudentFirstName(String value) {
        this.studentFirstName = value;
    }

    public String getStudentLastName() {
        return this.studentLastName;
    }

    public void setStudentLastName(String value) {
        this.studentLastName = value;
    }

    /**
     * getStudentName()
     *
     * @return - student first name and last name
     */
    public String getStudentName() {
        return this.studentFirstName + " " + this.studentLastName;
    }

    /**
     * toString()
     *      Override of Object.toString() to write out Student instance as string
     * @return - string representation of Student
     */
    @Override
    public String toString() {
        return String.format("StudentNo: %d, StudentId: %s, StudentName: %s",
                this.studentNo, this.studentId, this.getStudentName());
    }
}//class
