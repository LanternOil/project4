package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.util.Collections.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();


        while (true){

            menu();
            int userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice){
                //Task Creation
                case 1:
                    System.out.println("Please give the task a name.");
                    String name = input.nextLine();
                    System.out.println("Please give the task a priority from 0-5, with 0 being the lowest.");
                    int prio = input.nextInt();
                    input.nextLine();
                    System.out.println("Please describe the task.");
                    String desc = input.nextLine();
                    addTask(taskList,name,prio,desc);
                    break;
                //Task Deletion
                case 2:
                    System.out.println("Enter the number of the task you would like to delete");
                    listTask(taskList);
                    int i = input.nextInt();
                    deleteTask(taskList,i);
                    break;
                //Update task
                case 3:
                    listTask(taskList);
                    System.out.println("Please enter the number of the task you would like to update");
                    i = input.nextInt();
                    input.nextLine();
                    System.out.println("Please enter the new task description");
                    desc = input.nextLine();
                    updateTask(taskList,i,desc);
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
                case 6:
                    sortTask(taskList);
                    break;
                case 0: System.exit(0);
            }
        }

    }

    public static void menu(){
        System.out.println("Please choose an option:");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List all tasks with a priority");
        System.out.println("(6) Sort the tasks");
        System.out.println("(0) Exit.");
    }

    static void addTask(ArrayList tasks, String title, int priority, String desc){
        tasks.add(new Task(title,priority,desc));
    }

    static void deleteTask(ArrayList tasks, int index){
        tasks.remove(index-1);
    }

    static void listTask(ArrayList tasks){
        //test

        for (int i =0; i < tasks.size(); i++){
            System.out.println(i+1 + ": " + tasks.get(i));
        }
    }

    static void sortTask(ArrayList<Task> tasks){
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


    static void updateTask(ArrayList tasks, int index, String desc){
        tasks.set(index-1, desc);
    }

    static void listPriority(ArrayList<Task> tasks, int userPrio) {
        for (Task item : tasks) {
            if (item.getPriority() == userPrio) {
                System.out.println(item);
            }
        }
    }

}

