package Model;

import java.util.Date;

public class Person {

    private static int insertCounter = 0;
    private Priority priority;
    private String firstName, secondName, passportID;
    private int personId;
    private Date dateOfArrivel;

    public Person(Priority priority, String firstName, String secondName, String passportID, Date dateOfArrivel) {

        this.priority = priority;
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportID = passportID;
        this.personId = insertCounter;
        this.dateOfArrivel = dateOfArrivel;
        this.insertCounter++;
    }

    public int getInsertCounter() {
        return insertCounter;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassportID() {
        return passportID;
    }

    public int getPersonId() {
        return personId;
    }

    public Date getDateOfArrivel() {
        return dateOfArrivel;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setDateOfArrivel(Date dateOfArrivel) {
        this.dateOfArrivel = dateOfArrivel;
    }

}
