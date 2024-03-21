package pl.rudylis.mytasklist.Task;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    public Task saveTask(Task task, Long idStatus){
        if(isIdPresent(task.getTaskId())){
            throw new IllegalArgumentException("Jest już takie id tasku");
        }
        LocalDate dzis = LocalDate.now();
        task.setSetDate(dzis);
        Status status = statusRepository.findById(Math.toIntExact(idStatus)).orElseThrow(() -> new IllegalArgumentException("Nie ma takiego Status ID!"));
        task.setStatus(status);
        return taskRepository.save(task);
    }
    public Task updateStatus(Task task, Long idStatus){
        if(!isIdPresent(task.getTaskId())){
            throw new IllegalArgumentException("Nie ma takiego tasku!");
        }
        Task existingTask = taskRepository.findById(Math.toIntExact(task.getTaskId()))
                .orElseThrow(() -> new IllegalArgumentException("Nie ma takiego tasku!"));
        Status status = statusRepository.findById(Math.toIntExact(idStatus)).orElseThrow(() -> new IllegalArgumentException("Nie ma takiego Status ID!"));
        existingTask.setStatus(status);
        return taskRepository.save(existingTask);
    }

    public Task getTask(Long id){
        if(!isIdPresent(id)){
            throw new IllegalArgumentException("Nie ma takiego tasku!");
        }
        return taskRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new IllegalArgumentException("Nie ma takiego tasku!"));
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public void deleteTask(Long id){
        if(!isIdPresent(id)){
            throw new IllegalArgumentException("Nie ma takiego tasku!");
        }
        taskRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new IllegalArgumentException("Nie ma tasku o takim id"));
        taskRepository.deleteById(Math.toIntExact(id));

    }

    private boolean isIdPresent(Long id) {
        return id != null;
    }

}
