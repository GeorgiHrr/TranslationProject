import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        String lineNew;
        while (input.hasNextLine()) {
            lineNew = input.nextLine();
            if (lineNew.isEmpty()) {
                break;
            }
            //System.out.println(lineNew);
            lines.add(lineNew);
        }

        Tokenization tokenization = new Tokenization(lines);
        Parser parser = new Parser(tokenization);











//        System.out.println();
//        System.out.println("Content of List<String> lines:");
//        for (String string : lines) {
//            System.out.println(string);
//        }



        //It reads each character in the line. https://www.geeksforgeeks.org/iterate-over-the-characters-of-a-string-in-java/
//        for (int i = 0; i < lines.get(0).length(); i++) {
//            // Print current character
//            System.out.print(lines.get(0).charAt(i) + " ");
//        }
    }
}