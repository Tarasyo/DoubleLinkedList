package Model;

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
            switch(newNode.getPerson().getPriority()){
                case HIGH: lastHigh = newNode;
                break;
                case MEDIUM: lastMedium = newNode;
                break;
            }
        }else{
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

        if(newNode.getNext() == null){
            last = newNode;
        }else {
            newNode.getNext().setPrevious(newNode);
        }

        size++;
        lastHigh = newNode;

    }

    public void insertMedium(Node newNode){
        if(lastMedium == null && lastHigh == null){
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
            lastMedium = newNode;
        }else if(lastMedium == null){
            newNode.setNext(lastHigh.getNext());
            newNode.setPrevious(lastHigh);
            lastHigh.setNext(newNode);
        }else{
            newNode.setNext(lastMedium.getNext());
            newNode.setPrevious(lastMedium);
            lastMedium.setNext(newNode);
        }

        if (newNode.getNext() == null) {
            last = newNode;
        } else {
            newNode.getNext().setPrevious(newNode);
        }
        size++;
        lastMedium = newNode;

    }
    private void insertLow(Node newNode) {
        newNode.setPrevious(last);
        last.setNext(newNode);
        last = newNode;
        size++;

    }

    public Node deleteById(int id){
        Node current = first;
        while(current.getPerson().getPersonId() != id){
            current = current.getNext();
            if(current == null){
                return null;
            }
        }
        if(current == first){
            first = current.getNext();
        }else if(current == last){
            last = current.getPrevious();
            last.setNext(null);
            current.setPrevious(null);
        }else{
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        /*
        * In this 2 if statements checking if deleted had high or medium priority
        * if yes and previous node are same priority last node of this priority is changed if note it set to null
        * */
        if(current == lastMedium && current.getPrevious().getPerson().getPriority() == Priority.MEDIUM){
            lastMedium = current.getPrevious();
        }else{
            lastMedium = null;
        }
        if (current == lastHigh && current.getPrevious().getPerson().getPriority() == Priority.HIGH){
            lastHigh = current.getPrevious();
        }else{
            lastHigh = null;
        }
        size--;
        return current;
    }


}
