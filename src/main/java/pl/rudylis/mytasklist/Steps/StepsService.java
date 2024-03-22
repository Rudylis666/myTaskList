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
        Task task = taskRepository.findById(Math.toIntExact(idTask)).orElseThrow(()-> new IllegalArgumentException("Nie ma takiego zadania!"));
        step.setTask(task);
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
        Task task = taskRepository.findById(Math.toIntExact(idTask)).orElseThrow(() -> new IllegalArgumentException("Nie ma takiego taskId!"));;
        Status status = statusRepository.findById(Math.toIntExact(idStatus)).orElseThrow(() -> new IllegalArgumentException("Nie ma takiego statusu!"));;
        step.setStatus(status);
        return stepsRepository.save(step);
    }
    public Steps getStep(Long idStep){
        if(!isIdPresent(idStep)){
            throw new IllegalArgumentException("Id == null");
        }
        Steps step = stepsRepository.findById(Math.toIntExact(idStep)).orElseThrow(() -> new IllegalArgumentException("Nie ma takiego stepId!"));
        return step;
    }
    public List<Steps> getAllTaskSteps(Long idTask){
        if(!isIdPresent(idTask)){
            throw new IllegalArgumentException("Id == null");
        }
        Task task = taskRepository.findById(Math.toIntExact(idTask)).orElseThrow(()->new IllegalArgumentException("Nie ma takiego stepId!"));
        return stepsRepository.findByTaskTaskId(idTask);

    }
    public void deleteStep(Long idStep){
        if(!isIdPresent(idStep)){
            throw new IllegalArgumentException("Id == null");
        }
        stepsRepository.findById(Math.toIntExact(idStep)).orElseThrow(()-> new IllegalArgumentException("Nie ma kroku o takim id"));
        stepsRepository.deleteById(Math.toIntExact(idStep));
    }
    private boolean isIdPresent(Long id) {
        return id != null;
    }
}
