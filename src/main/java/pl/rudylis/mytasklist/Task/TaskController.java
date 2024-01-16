package pl.rudylis.mytasklist.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
