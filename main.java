
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

        System.out.println("\n[System] Erstelle Jira-Tickets im RAM...");
        manager.createTask(
            "Repository einrichten", 
            "Git-Repository erstellen und die README.md hinzufügen.", 
            Priority.HIGH, 
            LocalDate.now().plusDays(1)
        );

        manager.createTask(
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
