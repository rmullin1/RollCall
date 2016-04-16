package edu.westga.cs6242.rollcall.model;

/**
 * class Student
 *      Represents a school student attending classes
 */
public class Student {
    int studentNo;
    String studentId;
    String firstName;
    String lastName;

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

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * getStudentName()
     *
     * @return - student first name and last name
     */
    public String getStudentName() {
        return this.firstName + " " + this.lastName;
    }

    public String getStudentNameEx() { return String.format("%s (%s)",
            this.getStudentName(), this.getStudentId());}

    /**
     * toString()
     *      Override of Object.toString() to write out Student instance as string
     * @return - string representation of Student
     */
    @Override
    public String toString() {
        return String.format("StudentNo: %d, StudentId: %s, StudentName: %s",
                this.getStudentNo(), this.getStudentId(), this.getStudentName());
    }
}//class
