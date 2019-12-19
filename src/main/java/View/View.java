package View;

import java.util.ArrayList;

/*
* Just simple view with options that are stored in Array LIst
*
* */
public class View {

    private ArrayList menu;

    public View() {

        System.out.println("-------------MENU---------------");

        menu = new ArrayList<>();

        menu.add("1. ADD person");
        menu.add("2. DELETE person by ID");
        menu.add("3. SEARCH person");
        menu.add("4. DELETE number of person from the end");
        menu.add("5. SEE whole LINE");
        menu.add("6. UPDATE Person");
    }
    public ArrayList getMenu() {
        return menu;
    }
}
