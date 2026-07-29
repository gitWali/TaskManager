
import model.Priority;
import service.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuConsole {
    private final TaskManager taskManager;
    private final Scanner scanner;

    public MenuConsole(TaskManager taskManager) 
    {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() 
  {
        boolean running = true;

        while (running) 
        {
            System.out.println("\n===== MINI-JIRA MAIN MENU =====");
            System.out.println("1. Alle Tickets anzeigen");
            System.out.println("2. Neues Ticket erstellen");
            System.out.println("3. Ticket auf DONE setzen");
            System.out.println("4. Programm beenden & speichern");
            System.out.print("Wähle eine Option (1-4): ");

            String input = scanner.nextLine();

            switch (input) 
            {
                case "1":
                    showAllTasks();
                    break;
                case "2":
                    createNewTask();
                    break;
                case "3":
                    markTaskAsDone();
                    break;
                case "4":
                    System.out.println("Beende Anwendung...");
                    running = false;
                    break;
                default:
                    System.out.println("[Validierung] Ungültige Auswahl! Bitte eine Zahl von 1 bis 4 eingeben.");
            }
        }
    }

    private void showAllTasks() 
  {
        System.out.println("\n--- JIRA TICKETS ---");
        if (taskManager.getAllTasks().isEmpty()) 
        {
            System.out.println("Keine Tickets vorhanden.");
            return;
        }
        taskManager.getAllTasks().forEach(task -> 
        {
            System.out.println(task);
            System.out.println("------------------------------------------------");
        });
    }

    private void createNewTask() 
     {
        System.out.println("\n--- NEUES TICKET ERSTELLEN ---");
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Beschreibung: ");
        String desc = scanner.nextLine();

        Priority priority = null;
        while (priority == null) 
        {
            System.out.print("Priorität (LOW, MEDIUM, HIGH): ");
            try 
              {
                priority = Priority.valueOf(scanner.nextLine().toUpperCase());
            } 
            catch (IllegalArgumentException e) 
              {
                System.out.println("[Validierung] Ungültige Priorität! Bitte LOW, MEDIUM oder HIGH eingeben.");
            }
        }

        LocalDate dueDate = null;
        while (dueDate == null) 
        {
            System.out.print("Fälligkeitsdatum (Format: JFFF-MM-TT, z.B. 2026-12-31): ");
            try 
              {
                dueDate = LocalDate.parse(scanner.nextLine());
            } 
            catch (DateTimeParseException e) 
              {
                System.out.println("[Validierung] Falsches Datumsformat! Bitte exakt JJJJ-MM-TT nutzen.");
            }
        }
        taskManager.createTask(title, desc, priority, dueDate);
        System.out.println("[Erfolg] Ticket wurde erfolgreich angelegt!");
    }

    private void markTaskAsDone() 
  {
        System.out.println("\n--- TICKET ALS DONE MARKIEREN ---");
        System.out.print("Gib die ID des Tickets ein: ");
        try 
          {
            int id = Integer.parseInt(scanner.nextLine());
            boolean success = taskManager.completeTask(id);
            if (success) 
            {
                System.out.println("[Erfolg] Ticket " + id + " ist nun erledigt (DONE).");
            } 
            else 
            {
                System.out.println("[Fehler] Ticket mit ID " + id + " wurde nicht gefunden.");
            }
        } 
        catch (NumberFormatException e) 
          {
            System.out.println("[Validierung] Ungültige Eingabe! Bitte eine gültige Ganzzahl (ID) eingeben.");
        }
    }
}
