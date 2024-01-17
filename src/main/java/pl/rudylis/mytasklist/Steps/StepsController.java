package pl.rudylis.mytasklist.Steps;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/step/")
@RequiredArgsConstructor
public class StepsController {
    private final StepsService stepsService;
    @PostMapping(path = "{id_task}")
    public ResponseEntity<Steps> saveStep(@RequestBody  Steps step,@PathVariable Long id_task){
        return new ResponseEntity<>(
                stepsService.saveStep(step,id_task),
                HttpStatus.CREATED
        );
    }
    @PutMapping(path="{id_task}/{id_status}")
    public ResponseEntity<Steps> updateStep(@RequestBody Steps step,@PathVariable Long id_task, @PathVariable Long id_status){
        return new ResponseEntity<>(
                stepsService.updateStep(step,id_task,id_status),
                HttpStatus.OK
        );
    }
    @GetMapping(path = "get/{idStep}")
    public ResponseEntity<Steps> getStep(@PathVariable Long idStep){
        return new ResponseEntity<>(
                stepsService.getStep(idStep),
                HttpStatus.OK
        );
    }

}
