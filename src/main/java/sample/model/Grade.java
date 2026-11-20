package sample.model;                                      

public class Grade {                                        

    private int studentId;                                  
    private String courseCode;                             
    private double score;                                   

    public Grade(int studentId, String courseCode, double score) { 
        this.studentId = studentId;                        
        this.courseCode = courseCode;                       
        this.score = score;                                
    }

    public int getStudentId() {                             
        return studentId;                                   
    }

    public String getCourseCode() {                         
        return courseCode;                                  
    }

    public double getScore() {                              
        return score;                                       
    }
}