package sample.model;
public class Student {
   private String studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentDepartment;
    private int academicYear;
    
    public Student(String studentId, String studentName, String studentEmail, String studentPhone, String studentDepartment, int academicYear) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentDepartment = studentDepartment;
        this.academicYear = academicYear;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }
    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public int getAcademicYear() {
        return academicYear;
    }
  
}

