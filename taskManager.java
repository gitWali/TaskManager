

import service.DatabaseHandler;
import service.TaskManager;

public class Main {
    public static void main(String[] args) 
    {
        System.out.println("=== BOOTING MINI-JIRA WITH SQL DATABASE ===");

        DatabaseHandler dbHandler = new DatabaseHandler();

        TaskManager manager = new TaskManager(dbHandler);

        MenuConsole menu = new MenuConsole(manager);
        menu.start();

        System.out.println("=== MINI-JIRA ERFOLGREICH BEENDET ===");
    }
}
