package pl.rudylis.mytasklist.Steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;
import pl.rudylis.mytasklist.Task.Task;
import pl.rudylis.mytasklist.Task.TaskRepository;

import java.time.LocalDate;
import java.util.List;
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
    public Steps updateStep(Steps step, Long idTask, Long idStatus){
        if(!isIdPresent(idTask)||!isIdPresent(step.getIdStep())||!isIdPresent(idStatus)){
            throw new IllegalArgumentException("Id == null");
        }
        Task task = taskRepository.getReferenceById(Math.toIntExact(idTask));
        Status status = statusRepository.getReferenceById(Math.toIntExact(idStatus));
        step.setStatus(status);
        return stepsRepository.save(step);
    }
    public Steps getStep(Long idStep){
        if(!isIdPresent(idStep)){
            throw new IllegalArgumentException("Id == null");
        }
        return stepsRepository.getReferenceById(Math.toIntExact(idStep));
    }
    public List<Steps> getAllTaskSteps(Long idTask){
        if(!isIdPresent(idTask)){
            throw new IllegalArgumentException("Id == null");
        }
        Task task = taskRepository.getReferenceById(Math.toIntExact(idTask));
        return stepsRepository.findByTaskTaskId(idTask);

    }
    private boolean isIdPresent(Long id) {
        return id != null;
    }
}
