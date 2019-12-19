package Controller;
import Model.DoubleLinkedList;
import Model.Node;
import Model.Person;
import Model.Priority;
import View.View;

import java.io.*;
import java.util.Date;
import java.util.Random;

/*
 * TARAS BOREYKO
 * ID 2017284
 *
 *
 *
 * */

public class Controller {

    View view;
    Model.DoubleLinkedList list;
    Model.Person person;

    public Controller(){

        view = new View();
        list = new DoubleLinkedList();

        //this method with try catch for fake Data if not need just comment it.
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
            case "6":
                updateData();
            default:
                System.out.println("Please choice one of the options");
                menu();
                break;
        }
    }
    /*
    * This method is for adding new person id and date are generated
    * All method quite simple with some validations
    * */
    public void add(){
        Person person;
        System.out.println("Please Enter First Name");
        String nameFirst = bufferR();
        if(!(checkeString(nameFirst))){
            System.out.println("Please enter valid Name");
            add();
        }
        System.out.println("Please Enter Last Name");
        String nameLast = bufferR();
        if(!(checkeString(nameLast))){
            System.out.println("Please enter valid Name");
            add();
        }
        System.out.println("Please Enter Passport Number THAT should contain 8 numbers or Alphabetic's");
        String passport = bufferR();
        if(!(checkePassport(passport))){
            System.out.println("Please enter valid Passport number");
            add();
        }
        //This part for Priority. User should input just number from 1 to 3 where priority will be assign to the number that are entered
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
            System.out.println("PLEASE ENTER NUMBER From OPTION");
            add();
        }
        //In the first line all inputs are passed to the person class constructor and after new person added to the list
        person = new Person(priority, nameFirst, nameLast, passport, new Date(System.currentTimeMillis()));
        list.insertNew(person);
        menu();
    }
    //This method takes input check if its number and pass number to the method in DoubleLinkedList where it lock for
    //the person with same ID and delete it
    public void delete(){
        System.out.println("Please Enter ID NUMBER WHICh TO DELETE");
        String input = bufferR();
        if(!(checkNumber(input))){
            System.out.println("Please Enter Number");
            delete();
        }
        int id = Integer.valueOf(input);
        list.deleteById(id);
        menu();
    }
//Method that calls method from DoubleLinkedList class to print all user List
    public void print(){
        list.outPrint();
        menu();
    }
//Method to delete some amount of user from back of the list
    public void deleteFromEnd(){
        System.out.println("Please Enter How Many would you like to delete");
        String input = bufferR();
        if(!(checkNumber(input))){
            System.out.println("Please Enter Number");
            deleteFromEnd();
        }
        //in this part I declare counter. And while loop which will call deleteLast method from DoubleLInkedList class
        // n times where n is input of the user
        int number = Integer.valueOf(input);
        int counter = 0;
        while(counter != number){
            list.deletLast();
            counter++;
        }
        menu();
    }

    //Method that calls method from DoubleLInkedList class that will give back position of the person with ID that was entered and his position in the queue
    public void position(){
        System.out.println("Please Enter ID of Person");
        String input = bufferR();
        if(!(checkNumber(input))){
            System.out.println("Please Enter Number");
            position();
        }
        int number = Integer.valueOf(input);
        list.checkById(number);
        menu();
    }

    /*
    * Method to update data of the person in the line, I used setters that was in the my person method to set new data
    * And method from DoubleLinkedList, updateById, pass me the node that I need to change by searching by ID;
    * */
    public void updateData(){
        System.out.println("Please Enter ID of Person");
        String input = bufferR();
        if(!(checkNumber(input))){
            System.out.println("Please Enter Number");
            updateData();
        }
        int number = Integer.valueOf(input);
        Node newNode = list.updateById(number);
        System.out.println("Please Enter the Number of option: " +
                "1) Change Name " +
                "2)Change Surname " +
                "3)Change Passport Number");
        String input2 = bufferR();
        switch (input2){
            case "1":
                System.out.println("Please Enter New Name");
                input2 = bufferR();
                newNode.getPerson().setFirstName(input2);
                if(!(checkeString(input2))){
                    System.out.println("Please enter valid Name");
                    updateData();
                }
                break;
            case "2":
                System.out.println("Please Enter New Surname");
                input2 = bufferR();
                if(!(checkeString(input2))){
                    System.out.println("Please enter valid Name");
                    updateData();
                }
                newNode.getPerson().setSecondName(input2);
                break;
            case "3":
                System.out.println("Please Enter New Passport Number");
                input2 = bufferR();
                if(!(checkePassport(input2))){
                    System.out.println("Please enter valid Passport number");
                    updateData();
                }
                newNode.getPerson().setPassportID(input2);
                break;
                default:
                    break;
        }
    }

    //Method to input Fake Data to the list
    public void fakeData() throws IOException {

        //reads from the file
        String fileName = "D:\\programing\\java\\DSA_SA\\src\\main\\java\\Controller\\names.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        //
        while((line = br.readLine()) != null){

            String[] newLine = line.split(" "); //splits the data that takes from one line by space and initialize in the array
            // create new person and passing name and surname, priority and passport number are generated randomly from the methods , date is current time
            Person person = new Person(getPriority(), newLine[0], newLine[1], getPassportID(), new Date(System.currentTimeMillis()));
            list.insertNew(person); //adding person to the list
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
///Validation Just for string, will be used for first and second name
    public boolean checkeString(String input){
        if(input.matches("[a-zA-Z]+")){
            return true;
        }else {
            return  false;
        }
    }
    // Validation for Passport
    public boolean checkePassport(String input){
        if(input.matches("[a-zA-Z0-9]+") && input.length() == 8){
            return true;
        }else {
            return  false;
        }
    }
    //Validation for numbers
    public boolean checkNumber(String input){
        if(input.matches("[0-9]+")){
            return true;
        }else{
            return  false;
        }
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


