package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Stringtasks {

    public static void main(String[] args) {

        String fileName = "/home/artur/kurs/Warsztat-CodersLab/pl.coderslab/tasks.csv";

        System.out.println(Arrays.toString(tasks(fileName)));
    }


    public static String[] tasks(String fileName) {

        File file = new File(fileName);

        String line = "";
        String[] toDo = new String[0];
        int i = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                toDo = Arrays.copyOf(toDo, toDo.length + 1);
                toDo[i] = scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return toDo;
    }
}


