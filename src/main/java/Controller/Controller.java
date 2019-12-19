package Controller;
import Model.DoubleLinkedList;
import Model.Person;
import Model.Priority;
import View.View;

import java.io.*;
import java.util.Date;
import java.util.Random;


public class Controller {

    View view;
    Model.DoubleLinkedList list;
    Model.Person person;

    public Controller(){

        view = new View();
        list = new DoubleLinkedList();
        try {
            fakeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu();

    }
    public void menu() {
        String input;
        for (int i = 0; i < view.getMenu().size(); i++) {
            System.out.println(String.valueOf(view.getMenu().get(i)));
        }
        System.out.println("Please Select one of the options");
        input = bufferR();
        switch (input) {
            case "1":
                add();
                break;
            case "2":
                delete();
                break;
            case "3":
                position();
                break;
            case "4":
                deleteFromEnd();
                break;
            case "5":
                print();
                break;
            default:
                System.out.println("Please choice one of the options");
                menu();
                break;
        }
    }
    public void add(){
        System.out.println("Please Enter First Name");
        String nameFirst = bufferR();
        System.out.println("Please Enter Last Name");
        String nameLast = bufferR();
        System.out.println("Please Enter Passport Number");
        String passport = bufferR();
        System.out.println("Please INPUT NUMBER of Priority THAT YOU NEED: 1)HIGH  2)MEDIUM  3)LOW");
        String input = bufferR();
        Priority priority = null;
        if(Integer.valueOf(input) == 1) {
           priority = Priority.HIGH;
        }else if(Integer.valueOf(input) == 2){
           priority = Priority.MEDIUM;
        }else if(Integer.valueOf(input) == 3){
            priority = Priority.LOW;
        }else{
            System.out.println("PLEASE ENTER NUMBER OF OPTION");
            add();
        }
        person = new Person(priority, nameFirst, nameLast, passport, new Date(System.currentTimeMillis()));
        list.insertNew(person);
        menu();
    }

    public void delete(){
        System.out.println("Please Enter ID NUMBER WHICh TO DELETE");
        String input = bufferR();
        int id = Integer.valueOf(input);
        list.deleteById(id);
        menu();
    }

    public void print(){
        list.outPrint();
        menu();
    }

    public void deleteFromEnd(){
        System.out.println("Please Enter How Many would you like to delete");
        String input = bufferR();
        int number = Integer.valueOf(input);
        int counter = 0;
        while(counter != number){
            list.deletLast();
            counter++;
        }
        menu();
    }
    public void position(){
        System.out.println("Please Enter ID of Person");
        String input = bufferR();
        int number = Integer.valueOf(input);
        list.checkById(number);
    }

    public void fakeData() throws IOException {


        String fileName = "D:\\programing\\java\\DSA_SA\\src\\main\\java\\Controller\\names.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            //process the line
            String[] newLine = line.split(" ");
            Person person = new Person(getPriority(), newLine[0], newLine[1], getPassportID(), new Date(System.currentTimeMillis()));
            list.insertNew(person);
        }

    }

    /*
    * This part of code generates random passport number will be used to create fake data
    * I find this pice of code from https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    * */
    public static String getPassportID()
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
/*
* Method for random priority will be used to create fake data
*
* */
    public Priority getPriority() {
        Random rand = new Random();

        // Generate random integers in range 0 to 2
        int randInt = rand.nextInt(3);
        Priority priority;
        switch (randInt) {
            case 0:
                priority =  Priority.HIGH;
            break;
            case 1:
                priority = Priority.MEDIUM;
            break;
            case 2:
                priority = Priority.LOW;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + randInt);
        }
        return priority;
    }


    public String  bufferR() {
        String input = "";
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            input = br.readLine();

        }catch(Exception e) { System.out.println("Error reading input");}
        return input;
    }

}


