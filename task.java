
package modell;

import java.time.LocalDate;

public class task
  {
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(int id, String title, String description, Priority priority, LocalDate dueDate) 
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = false; 
    }
    
    public int getId() { return id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() 
    {
        String status = isCompleted ? "[DONE]" : "[OPEN]";
        return String.format("%s ID: %d | %s | Prio: %s | Fällig bis: %s\nBeschreibung: %s", 
                status, id, title, priority, dueDate, description);
    }
}
