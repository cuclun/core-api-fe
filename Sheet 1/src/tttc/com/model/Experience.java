package tttc.com.model;

import tttc.com.App;

import java.text.ParseException;
import java.util.Date;

public class Experience extends Candidate {
    private int expInYear;
    private String proSkill;

    public Experience() {
        super();
    }

    public Experience(String candidateID, String fullName, Date birthDay, String phone,
                      String email, int candidate_type, int expInYear, String proSkill) {
        super(candidateID, fullName, birthDay, phone, email, candidate_type);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    @Override
    public void inputInfo() throws ParseException {
        super.inputInfo();

        System.out.print("Enter Experience In Year: ");
        this.expInYear = Integer.parseInt(App.sc.nextLine());

        System.out.print("Enter Professional Skill: ");
        this.proSkill = App.sc.nextLine();
    }

    @Override
    public void showMe() {
        super.showInfo();
        System.out.println("Experience in year: " + expInYear);
        System.out.println("Professional skills : " + proSkill);
        System.out.println("---------");
    }

    @Override
    public String toString() {
        return super.toString() + expInYear + "," + proSkill + ",,,,,,";
    }
}
