package edu.westga.cs6242.rollcall.model;

/**
 * class SchoolClass
 */
public class SchoolClass {
    private int classNo;
    private String classId;
    private String className;

    /**
     * ACCESSSORS
     */
    public int getClassNo() {
        return this.classNo;
    }

    public void setClassNo(int value) {
        this.classNo = value;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String value) {
        this.classId = value;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * toString()
     *      Override of Object.toString() to write out SchoolClass instance as string
     * @return - string representation of School Class
     */
    @Override
    public String toString() {
        return String.format("ClassNo: %d, ClassId: %s, ClassName: %s",
                this.classNo, this.classId, this.className);
    }

}//class
