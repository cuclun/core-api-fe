package tttc.com.model;

import tttc.com.App;

import java.text.ParseException;
import java.util.Date;

public class Fresher extends Candidate {
    private Date graduation_date;
    private String graduation_rank;
    private String education;

    public Fresher() {
        super();
    }

    public Fresher(String candidateID, String fullName, Date birthDay, String phone, String email, int candidate_type, Date graduation_date, String graduation_rank, String education) {
        super(candidateID, fullName, birthDay, phone, email, candidate_type);
        this.graduation_date = graduation_date;
        this.graduation_rank = graduation_rank;
        this.education = education;
    }

    @Override
    public void inputInfo() throws ParseException {
        super.inputInfo();

        System.out.print("Enter Graduation Date: ");
        this.graduation_date = App.dateFormat.parse(App.sc.nextLine());

        System.out.print("Enter Graduation Rank: ");
        this.graduation_rank = App.sc.nextLine();

        System.out.print("Enter Education: ");
        this.education = App.sc.nextLine();
    }

    @Override
    public void showMe() {
        super.showInfo();
        System.out.println("Graduation Date: " + graduation_date);
        System.out.println("Graduation Rank: " + graduation_rank);
        System.out.println("Graduation Education: " + education);
        System.out.println("---------");
    }

    @Override
    public String toString() {
        return super.toString() + ",," + App.dateFormat.format(graduation_date) + "," + graduation_rank + "," + education + ",,,";
    }
}
