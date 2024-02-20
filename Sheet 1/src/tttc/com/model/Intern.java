package tttc.com.model;

import tttc.com.App;

import java.text.ParseException;
import java.util.Date;

public class Intern extends Candidate {
    private String major;
    private String semester;
    private String universityName;

    public Intern() {
        super();
    }

    public Intern(String candidateID, String fullName, Date birthDay, String phone, String email, int candidate_type, String major, String semester, String universityName) {
        super(candidateID, fullName, birthDay, phone, email, candidate_type);
        this.major = major;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public void inputInfo() throws ParseException {
        super.inputInfo();

        System.out.print("Enter Major: ");
        this.major = App.sc.nextLine();

        System.out.print("Enter Semester: ");
        this.semester = App.sc.nextLine();

        System.out.print("Enter University Name: ");
        this.universityName = App.sc.nextLine();
    }

    @Override
    public void showMe() {
        super.showInfo();
        System.out.println("Major: " + major);
        System.out.println("Semester: " + semester);
        System.out.println("University Name: " + universityName);
        System.out.println("---------");
    }

    @Override
    public String toString() {
        return super.toString() + ",,,,," + major + "," + semester + "," + universityName;
    }
}
