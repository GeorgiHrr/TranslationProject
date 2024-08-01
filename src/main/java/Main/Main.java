package Main;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            List<String> lines = new ArrayList<String>();

            File file = new File("C:\\Users\\Georgi\\IdeaProjects\\testin\\src\\main\\java\\Main\\xample.txt");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }

            Tokenization tokenization = new Tokenization(lines);
            Table table = new Table();
            Parser parser = new Parser(tokenization, table);

        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }


//        System.out.println();
//        System.out.println("Content of List<String> lines:");
//        for (String string : lines) {
//            System.out.println(string);
//        }

    }
}