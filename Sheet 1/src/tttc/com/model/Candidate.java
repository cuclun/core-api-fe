package tttc.com.model;

import tttc.com.App;
import tttc.com.valid.DateValid;

import java.text.ParseException;
import java.util.Date;

public abstract class Candidate {
    private String candidateID;
    private String fullName;
    private Date birthDay;
    private String phone;
    private String email;
    private int candidate_type;

    public Candidate() {
        super();
    }

    public Candidate(String candidateID, String fullName, Date birthDay, String phone, String email, int candidate_type) {
        this.candidateID = candidateID;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.candidate_type = candidate_type;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCandidate_type() {
        return candidate_type;
    }

    public void setCandidate_type(int candidate_type) {
        this.candidate_type = candidate_type;
    }

    public void inputInfo() throws ParseException {
        System.out.print("Enter CandidateID: ");
        this.candidateID = App.sc.nextLine();

        System.out.print("Enter FullName: ");
        this.fullName = App.sc.nextLine();

        System.out.print("Enter BirthDay (dd/MM/yyyy): ");
        this.birthDay = App.dateFormat.parse(App.sc.nextLine());

        System.out.print("Enter Phone: ");
        this.phone = App.sc.nextLine();

        System.out.print("Enter Email: ");
        this.email = App.sc.nextLine();
    }

    public void showInfo() {
        System.out.println("CandidateID: " + candidateID);
        System.out.println("FullName: " + fullName);
        System.out.println("Birthday: " + birthDay);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("CandidateType: " + (candidate_type == 0 ? "Experience" : candidate_type == 1 ? "Fresher" : "Intern"));
    }

    @Override
    public String toString() {
        return candidateID + "," + fullName + "," + App.dateFormat.format(birthDay) + "," + phone + "," + email + "," + candidate_type + ",";
    }

    public abstract void showMe();
}
