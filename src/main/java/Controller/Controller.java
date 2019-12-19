package Controller;
import Model.DoubleLinkedList;
import Model.Person;
import Model.Priority;
import View.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;


public class Controller {

    View view;
    Model.DoubleLinkedList list;
    Model.Person person;

    public Controller(){

        view = new View();
        list = new DoubleLinkedList();
        fakeData();
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

    public void fakeData(){

        Person person1 = new Person(Priority.HIGH, "Taras", "Boreyko", "UA77771", new Date(System.currentTimeMillis()));
        Person person2 = new Person(Priority.LOW, "Olga", "Kisilova", "RU77772", new Date(System.currentTimeMillis()));
        Person person3 = new Person(Priority.HIGH, "Fellipe", "Mountovani", "ITA7773", new Date(System.currentTimeMillis()));
        Person person4 = new Person(Priority.MEDIUM, "Coue", "Coue", "BR7778", new Date(System.currentTimeMillis()));

        list.insertNew(person1);
        list.insertNew(person2);
        list.insertNew(person3);
        list.insertNew(person4);
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


