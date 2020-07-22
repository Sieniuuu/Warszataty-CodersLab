package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static pl.coderslab.Stringtasks.tasks;

public class TaskManager {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String fileName = "/home/artur/kurs/Warsztat-CodersLab/pl.coderslab/tasks.csv";
        Path path = Paths.get("/home/artur/kurs/Warsztat-CodersLab/pl.coderslab/tasks.csv");

        String chosenOption = "";
        String[] toShow = tasks(fileName);

        //System.out.println(Arrays.toString(toShow));

        while (true) {
            System.out.println(pl.coderslab.ConsoleColors.BLUE);
            System.out.println("Please select an option: " + pl.coderslab.ConsoleColors.RESET);
            System.out.println("add");
            System.out.println("list");
            System.out.println("remove");
            System.out.println("exit");
            chosenOption = scanner.nextLine();
            if (chosenOption.equals("exit")) {
                System.out.println(pl.coderslab.ConsoleColors.RED + "Bye, bye.");
                break;
            }
            switch (chosenOption) {
                case "add":
                    try {
                        FileWriter fileWriter = new FileWriter(fileName, true);
                        System.out.println("Please add task description");
                        fileWriter.append(scanner.nextLine() + " ");
                        System.out.println("Please add task due date");
                        fileWriter.append(scanner.nextLine() + " ");
                        System.out.println("Is your task is important: true/false");
                        fileWriter.append(scanner.nextLine() + "\n");
                        fileWriter.close();
                    } catch (IOException ex) {
                        System.out.println("Błąd zapisu do pliku.");
                    }
                    break;
                case "remove":
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Please select number to remove");
                    String[] remove = new String[0];
                    try {
                        while (!scanner2.hasNextInt()) {
                            scanner2.nextLine();
                            System.out.println("Incorrect argument passed. Please give number from 0 to " + (toShow.length - 1));
                        }
                        String chosenindex = scanner2.nextLine();
                        int index = Integer.parseInt(chosenindex);
                        try {
                            while (index < 0 || index > toShow.length) {
                                System.out.println("Incorrect argument passed. Please give number from 0 to " + (toShow.length - 1));
                                while (!scanner2.hasNextInt()) {
                                    scanner2.nextLine();
                                    System.out.println("Incorrect argument passed. Please give number from 0 to " + (toShow.length - 1));
                                }
                                index = scanner2.nextInt();
                            }
                            remove = Arrays.copyOf(toShow, toShow.length);
                            remove = ArrayUtils.remove(remove, index);
                            for (int i = 0; i < remove.length; i++) {
                                System.out.println(i + ": " + remove[i]);
                            }
                            toShow = remove;
                            System.out.println("Number succesful removed");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Incorrect argument passed. Please give number from 0 to " + (toShow.length - 1));
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect argument passed. Please give number from 0 to " + (toShow.length - 1));
                    }
                    try {
                        Files.write(path, Arrays.asList(remove));
                    } catch (IOException ex) {
                        System.out.println("Błąd zapisu do pliku.");
                    }
                    break;
                case "list":
                    toShow = tasks(fileName);
                    for (int i = 0; i < toShow.length; i++) {
                        System.out.println(i + ": " + toShow[i]);
                    }
                    break;
                default:
                    System.out.println("Please select a correct option.");
                    break;
            } // koniec switch
        }// koniec while
    } // koniec main
} // koniec class
