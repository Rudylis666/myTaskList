package pl.rudylis.mytasklist.Steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;
import pl.rudylis.mytasklist.Task.Task;
import pl.rudylis.mytasklist.Task.TaskRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepsService {
    private final StepsRepository stepsRepository;
    private final StatusRepository statusRepository;
    private final TaskRepository taskRepository;

    public Steps saveStep(Steps step, Long idTask){
        if(!isIdPresent(idTask)){
            throw new IllegalArgumentException("Nie ma takiego tasku");
        }
        if(isIdPresent(step.getIdStep())){
            throw new IllegalArgumentException("Już jest krok o takim id");
        }
        Optional<Task> task = taskRepository.findById(Math.toIntExact(idTask));
        if(task.isEmpty()){
            throw new IllegalArgumentException("Nie ma takiego tasku");
        }
        LocalDate dzis = LocalDate.now();
        step.setSetDate(dzis);
        Status status = statusRepository.getReferenceById(2);
        step.setStatus(status);
        return stepsRepository.save(step);
    }
    private boolean isIdPresent(Long id) {
        return id != null;
    }
}
