package pl.rudylis.mytasklist.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping(path = "/saveTask/{id_status}")
    public ResponseEntity<Task> saveTask(@RequestBody Task task, @PathVariable Long id_status){
        return new ResponseEntity<>(
                taskService.saveTask(task,id_status),
                HttpStatus.CREATED
        );
    }
    @PutMapping(path = "/updateStatus/{id_status}")
    public ResponseEntity<Task> updateStatus(@RequestBody Task task, @PathVariable Long id_status){
        return new ResponseEntity<>(
                taskService.updateStatus(task,id_status),
                HttpStatus.OK
        );
    }
    @GetMapping(path = "/getTask/{id_task}")
    public ResponseEntity<Task> getTask(@PathVariable Long id_task){
        return new ResponseEntity<>(
                taskService.getTask(id_task),
                HttpStatus.OK
        );
    }
    @GetMapping(path = "/getAll/")
    public ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<>(
                taskService.getAllTasks(),
                HttpStatus.OK
        );
    }
    @DeleteMapping(path = "/delete/{id_task}")
    public void deleteTask(@PathVariable Long id_task) {
        taskService.deleteTask(id_task);
    }

    }
