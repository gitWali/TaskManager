
package service;

import model.Priority;
import model.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager 
{
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1; 
  
    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) 
  {
        Task newTask = new Task(nextId++, title, description, priority, dueDate);
        tasks.add(newTask);
        return newTask;
    }
  
    public List<Task> getAllTasks() 
    {
        return new ArrayList<>(tasks);
    }

    public boolean completeTask(int id) 
  {
        Task task = findById(id);
        if (task != null) 
        {
            task.setCompleted(true);
            return true;
        }
        return false;
    }

    public Task findById(int id) 
  {
        for (Task task : tasks) 
        {
            if (task.getId() == id) 
            {
                return task;
            }
        }
        return null;
    }
}

public void importLoadedTasks(List<Task> loadedTasks) 
{
    this.tasks.clear();
    this.tasks.addAll(loadedTasks);

    int maxId = 0;
    for (Task t : loadedTasks) 
    {
        if (t.getId() > maxId) 
        {
            maxId = t.getId();
        }
    }
    this.nextId = maxId + 1;
}

