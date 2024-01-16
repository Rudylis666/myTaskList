package pl.rudylis.mytasklist.Task;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    public Task saveTask(Task task){
        if(isIdPresent(task.getTaskId())){
            throw new IllegalArgumentException("Jest już takie id wydarzenia");
        }
        LocalDate dzis = LocalDate.now();
        task.setSetDate(dzis);
        Status status = statusRepository.getReferenceById(2);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    private boolean isIdPresent(Long id) {
        return id != null;
    }

}
