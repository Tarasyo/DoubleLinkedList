package Model;

/*
 * TARAS BOREYKO
 * ID 2017284
 *
 *
 *
 * */
import Controller.Controller;

public class DoubleLinkedList {

    private Node first;
    private Node last;
    private Node lastHigh;
    private Node lastMedium;
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * Adding a new node in the list
    * */
    public void insertNew(Person person){
        Node newNode = new Node(person);
        if(isEmpty()){
            first = last = newNode;
            size++;
            //If new node have some priority need to change the lastof this priority
            switch(newNode.getPerson().getPriority()){
                case HIGH: lastHigh = newNode;
                break;
                case MEDIUM: lastMedium = newNode;
                break;
            }
        }else{
            //If in the list have alredy some nodes will check priority and add with the method of this priority
            switch(newNode.getPerson().getPriority()){
                case HIGH: insertHigh(newNode);
                    break;
                case MEDIUM: insertMedium(newNode);
                    break;
                default: insertLow(newNode);
            }
        }



    }

    /*
    * Method for inserting high priority nodes
    *
    * */
    public void insertHigh(Node newNode){
        //if the list dont have high priority will insert on the beginning of the list
        if(lastHigh == null){
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        //if it has another high priority nodes
        }else{
            newNode.setPrevious(lastHigh);
            newNode.setNext(lastHigh.getNext());
            lastHigh.setNext(newNode);

            }
        //This if statement check if new node next is null need to initilize it to the last
        if(newNode.getNext() == null){
            last = newNode;
        }else {
            newNode.getNext().setPrevious(newNode);
        }

        size++;
        lastHigh = newNode;

    }
/*
*
* In general all the same with medium priority but it has additional if statment to check if it has High priority on front
*
* */
    public void insertMedium(Node newNode){
        //if dont have high or medium go on the front
        if(lastMedium == null && lastHigh == null){
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
            lastMedium = newNode;
            //if just dont have medium go after last high
        }else if(lastMedium == null){
            newNode.setNext(lastHigh.getNext());
            newNode.setPrevious(lastHigh);
            lastHigh.setNext(newNode);
            //in anothe case will take position of last Medium priority
        }else{
            newNode.setNext(lastMedium.getNext());
            newNode.setPrevious(lastMedium);
            lastMedium.setNext(newNode);
        }
            //this part is the same like in high priority to check if it's last or not
        if (newNode.getNext() == null) {
            last = newNode;
        } else {
            newNode.getNext().setPrevious(newNode);
        }
        size++;
        lastMedium = newNode;

    }
    //Insert low priority always go to the end
    private void insertLow(Node newNode) {
        newNode.setPrevious(last);
        last.setNext(newNode);
        last = newNode;
        size++;

    }
    //Method in future will be used to look with while loop for id of node and out print this node info with number in which he is in the queue
    public void checkById(int id) {
        Node current = first;
        int counter = 1;
        while(current.getPerson().getPersonId() != id){
            current = current.getNext();
            counter++;
            if(current == null){
                System.out.println("Sorry no such ID");

            }
        }
        System.out.println("THE PERSON IS ON POSITION: "+counter+", ID: "+current.getPerson().getPersonId()+
                ", Priority: "+current.getPerson().getPriority()+
                ", First Name: "+current.getPerson().getFirstName()+
                ", Last Name: "+current.getPerson().getSecondName()+
                ", Passport: "+current.getPerson().getPassportID()+
                ", Date of Arrive: "+current.getPerson().getDateOfArrivel()
        );
    }
    //This method same like checkById but here will return the node with this ID.
    public Node updateById(int id) {
        Node current = first;
        while(current.getPerson().getPersonId() != id){
            current = current.getNext();
            if(current == null){
                System.out.println("Sorry no such ID");
                return null;
            }
        }
        return current;
    }
    //in general this method woks like checkByID but in second half it change the reference of neighbour nodes and in this way
    // if the reference to this node lost it go to garbage collector
    public Node deleteById(int id){
        Node current = first;
        while(current.getPerson().getPersonId() != id){
            current = current.getNext();
            if(current == null){
                return null;
            }
        }
        //if it's first or last node need to refer the first or last of the list
        if(current == first){
            first = current.getNext();
        }else if(current == last){
            last = current.getPrevious();
            last.setNext(null);
            current.setPrevious(null);
            //another way just change neighbour nodes reference
        }else{
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        checkPriority(current);
        size--;
        return current;
    }
        //delete last beter to keep in temporary variable to change reference of previous node and then to initialize the previous to the like last
    public Node deletLast(){
        Node temp = last;
        if(first.getNext() == null){
            first = null;
        }else{
            last.getPrevious().setNext(null);
        }
        checkPriority(last);
        last = last.getPrevious();
        size--;
        return temp;
    }

    /*
     * In this 2 if statements checking if deleted had high or medium priority
     * if yes and previous node are same priority last node of this priority is changed if note it set to null
     * */
    public void checkPriority(Node check){

        if(check == lastMedium && check.getPrevious().getPerson().getPriority() == Priority.MEDIUM){
            lastMedium = check.getPrevious();
        }else{
            lastMedium = null;
        }
        if (check == lastHigh && check.getPrevious().getPerson().getPriority() == Priority.HIGH){
            lastHigh = check.getPrevious();
        }else{
            lastHigh = null;
        }
    }

    //just out print all list
    public void outPrint(){
        Node current = first;
        while(current != null){
            System.out.println("ID: "+current.getPerson().getPersonId()+
                            ", Priority: "+current.getPerson().getPriority()+
                            ", First Name: "+current.getPerson().getFirstName()+
                            ", Last Name: "+current.getPerson().getSecondName()+
                            ", Passport: "+current.getPerson().getPassportID()+
                            ", Date of Arrive: "+current.getPerson().getDateOfArrivel()
                    );
            System.out.println("--------------------------------------------------------");
            current = current.getNext();
        }
    }


}
