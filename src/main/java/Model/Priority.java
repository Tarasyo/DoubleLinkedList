package Model;

public enum Priority {

    HIGH(0),
    MEDIUM(1),
    LOW(3);

    private int position;


    Priority(int position) {
            this.position = position;
    }
}
