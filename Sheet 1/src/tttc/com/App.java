package tttc.com;

import tttc.com.model.Candidate;
import tttc.com.model.Experience;
import tttc.com.model.Fresher;
import tttc.com.model.Intern;
import tttc.com.valid.DateValid;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);
    private static ArrayList<Candidate> candidates = new ArrayList<>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

    public static void readFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineToArray = line.split(",");
                Candidate candidate = null;

                if (lineToArray[5].equals("0")) {
                    candidate = new Experience(lineToArray[0], lineToArray[1], dateFormat.parse(lineToArray[2]),
                            lineToArray[3], lineToArray[4], Integer.parseInt(lineToArray[5]), Integer.parseInt(lineToArray[6]), lineToArray[7]);
                } else if (lineToArray[5].equals("1")) {
                    candidate = new Fresher(lineToArray[0], lineToArray[1], dateFormat.parse(lineToArray[2]),
                            lineToArray[3], lineToArray[4], Integer.parseInt(lineToArray[5]),
                            dateFormat.parse(lineToArray[8]), lineToArray[9], lineToArray[10]);
                } else if (lineToArray[5].equals("2")) {
                    candidate = new Intern(lineToArray[0], lineToArray[1], dateFormat.parse(lineToArray[2]),
                            lineToArray[3], lineToArray[4], Integer.parseInt(lineToArray[5]), lineToArray[11], lineToArray[12], lineToArray[13]);
                }
                candidates.add(candidate);
            }
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("File not found");
        }
    }

//    public static void writeAll(ArrayList<Candidate> listCandidate) {
//        try {
//            FileWriter myWriter = new FileWriter("Candidate.csv");
//            for (Candidate c : listCandidate
//            ) {
//                myWriter.write(c.toString() + "\n");
//            }
//            myWriter.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }

    public static void writeOne(Candidate candidate) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Candidate.csv", true));

            bufferedWriter.write(candidate.toString() + "\n");

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        String select;
        String choose;
        while (true) {
            System.out.println("1: Thêm candidate");
            System.out.println("2: Hiển thị danh sách candidate");
            System.out.print("Chọn gì đi: ");
            select = sc.nextLine();
            switch (select) {
                case "1": {
                    System.out.println("1: Thêm Experience Candidate");
                    System.out.println("2: Thêm Fresher Candidate");
                    System.out.println("3: Thêm Intern Candidate");
                    System.out.print("Chọn gì đi: ");
                    choose = sc.nextLine();
                    Candidate candidate = null;
                    switch (choose) {
                        case "1": {
                            candidate = new Experience();
                            candidate.inputInfo();
                            candidate.setCandidate_type(0);
                            writeOne(candidate);
                            break;
                        }
                        case "2": {
                            candidate = new Fresher();
                            candidate.inputInfo();
                            candidate.setCandidate_type(1);
                            writeOne(candidate);
                            break;
                        }
                        case "3": {
                            candidate = new Intern();
                            candidate.inputInfo();
                            candidate.setCandidate_type(2);
                            writeOne(candidate);
                            break;
                        }
                        default:
                            break;
                    }
                    break;
                }
                case "2": {
                    readFile("Candidate.csv");
                    for (Candidate candidate : candidates) {
                        candidate.showMe();
                    }
                    break;
                }
                default:
                    break;
            }

            if (!(select.equals("1") || select.equals("2"))) {
                break;
            }
        }

    }
}
