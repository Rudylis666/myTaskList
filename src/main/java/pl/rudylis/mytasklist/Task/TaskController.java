package pl.rudylis.mytasklist.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping(path = "/saveTask")
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
        return new ResponseEntity<>(
                taskService.saveTask(task),
                HttpStatus.CREATED
        );
    }
}
