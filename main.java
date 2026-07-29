
package service;

import model.Priority;
import model.Task;
import service.TaskManager;
import java.time.LocalDate;

public class Main 
{
    public static void main(String[] args) 
  {
        System.out.println("=== STARTING MINI-JIRA CLI ===");

        TaskManager manager = new TaskManager();
        FileHandler fileHandler = new FileHandler("task.csv");

        List<Task> existingTasks = fileHandler.loadTasks();
        manager.importLoadedTasks(existingTasks);

        MenuConsole menu = new MenuConsole(manager);
        menu.start();

        System.out.println("\n[Shutdown] Sichere Daten auf Festplatte...");
        fileHandler.saveTasks(manager.getAllTasks());
        System.out.println("=== MINI-JIRA ERFOLGREICH BEENDET ===");

        System.out.println("\n[System] Erstelle Jira-Tickets im RAM...");
        manager.createTask
        (
            "Repository einrichten", 
            "Git-Repository erstellen und die README.md hinzufügen.", 
            Priority.HIGH, 
            LocalDate.now().plusDays(1)
        );

        manager.createTask
        (
            "Datenmodell implementieren", 
            "Klassen für Task und Priority-Enum in Java schreiben.", 
            Priority.MEDIUM, 
            LocalDate.now().plusDays(3)
        );

        System.out.println("\n=== AKTUELLE TICKETS ===");
        for (Task task : manager.getAllTasks()) 
        {
            System.out.println(task);
            System.out.println("------------------------------------------------");
        }

        System.out.println("\n[System] Bearbeite Ticket ID 1: Setze Status auf DONE...");
        manager.completeTask(1);

        System.out.println("\n=== AKTUELLE TICKETS NACH UPDATE ===");
        for (Task task : manager.getAllTasks()) 
        {
            System.out.println(task);
            System.out.println("------------------------------------------------");
        }
    }
}
