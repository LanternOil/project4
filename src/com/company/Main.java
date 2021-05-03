package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.util.Collections.*;

public class Main {

    public static void main(String[] args) {
        //The program needs very few global variables, most are for utility.
        Scanner input = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        //These are responsible for the write to file running on shutdown
        ShutDownTask shutDownTask = new ShutDownTask();
        Runtime.getRuntime().addShutdownHook(shutDownTask);

        //updates the task list on startup.
        taskList = readFromFile(taskList);


        while (true) {
            menu();
            int userChoice = 0;


            userChoice = input.nextInt();
            input.nextLine();


            //this large switch case statement calls the different functions.

            switch (userChoice) {
                //Task Creation
                case 1:
                    System.out.println("Please give the task a name.");
                    String name = input.nextLine();
                    System.out.println("Please give the task a priority from 0-5, with 0 being the lowest.");
                    int prio = input.nextInt();
                    input.nextLine();
                    System.out.println("Please describe the task.");
                    String desc = input.nextLine();
                    addTask(taskList, name, prio, desc);
                    break;
                //Task Deletion
                case 2:
                    System.out.println("Enter the number of the task you would like to delete");
                    listTask(taskList);
                    int i = input.nextInt();
                    deleteTask(taskList, i);
                    break;
                //Update task
                case 3:
                    listTask(taskList);
                    System.out.println("Please enter the number of the task you would like to update");
                    i = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the new task description");
                    desc = input.nextLine();
                    updateTask(taskList, i, desc);
                    break;
                //List Tasks
                case 4:
                    listTask(taskList);
                    break;
                //List all tasks with a given priority
                case 5:
                    System.out.println("Enter the priority you would like to list");
                    i = input.nextInt();
                    input.nextLine();
                    listPriority(taskList, i);
                    break;
                //Sorts all the tasks
                case 6:
                    sortTask(taskList);
                    break;
                //Writes the task list to a json file
                case 7:
                    writeToFile(taskList);
                    break;
                //Reads and UPDATES the task list, using the json file
                case 8:
                    taskList = readFromFile(taskList);
                    break;
                //Shuts down the program, and writes the list automatically.
                case 0:
                    shutDownTask.setTaskList(taskList);
                    System.exit(0);
            }


        }

    }

    //Ensures that writelist runs on shutdown
    private static class ShutDownTask extends Thread {

        private ArrayList writeList;

        public void setTaskList(ArrayList writeList) {
            this.writeList = writeList;
        }

        @Override
        public void run() {
            System.out.println("Performing shutdown");
            try {
                Thread.sleep(500);
                writeToFile(writeList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void menu() {
        System.out.println("Please choose an option:");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List all tasks with a priority");
        System.out.println("(6) Sort the tasks");
        System.out.println("(7) Write the list to file");
        System.out.println("(8) Read the list from file");
        System.out.println("(0) Exit.");
    }

    //Writes the task list to a json file
    static void writeToFile(ArrayList tasks) {
        Gson gson = new Gson();
        String json = gson.toJson(tasks);

        //System.out.println(json);

        try {
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(tasks, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Converts the json file back to an arraylist of tasks.
    static ArrayList<Task> readFromFile(ArrayList<Task> tasks) {

        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        JsonElement list;

        try {
            list = parser.parse(new FileReader("data.json"));
            Type listType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            tasks = gson.fromJson(list, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    //Creates a task object and adds it to the array list
    static void addTask(ArrayList tasks, String title, int priority, String desc) {
        tasks.add(new Task(title, priority, desc));
    }

    //Deletes a task, based on its position in the array.
    static void deleteTask(ArrayList tasks, int index) {
        //This accounts for the difference between what is shown to the user, and what the actual index is.
        tasks.remove(index - 1);
    }

    //Lists all the tasks
    static void listTask(ArrayList tasks) {
        //Adds 1 to the index to be more user friendly
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ": " + tasks.get(i));
        }
    }

    //Sorts all the tasks alphabetically by priority
    static void sortTask(ArrayList<Task> tasks) {
        /*for (int i =0; i < tasks.size()+1; i++) {
            if(tasks.get(i).compareTo(tasks.get(i + 1)) < 0);
                swap(tasks, i, i+1);

            }
            //if no moves are necessary,advance.
            //If no moves are necessary all the way down, you're done.
         */
        Collections.sort(tasks);
        listTask(tasks);
    }

    //Changes certain properties of a task
    static void updateTask(ArrayList tasks, int index, String desc) {
        tasks.set(index - 1, desc);
    }

    //lists all tasks based on priority
    static void listPriority(ArrayList<Task> tasks, int userPrio) {
        for (Task item : tasks) {
            if (item.getPriority() == userPrio) {
                System.out.println(item);
            }
        }
    }

}

