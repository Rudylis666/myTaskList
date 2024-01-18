package pl.rudylis.mytasklist.Steps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.rudylis.mytasklist.Task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.rudylis.mytasklist.assemblers.StepsAssembler.prepareStep;
import static pl.rudylis.mytasklist.assemblers.TaskAssembler.prepareTask;

@ExtendWith(MockitoExtension.class)
class StepsControllerTest {

    @Mock
    private StepsService stepsService;
    private StepsController underTest;
    @BeforeEach
    void setUp() {
        underTest= new StepsController(stepsService);
    }

    @Test
    void saveStep() {
        //given
        Steps step = prepareStep();
        Long idTask=1L;
        //when
        when(stepsService.saveStep(any(Steps.class), any(Long.class))).thenReturn(step);
        ResponseEntity<Steps> result = underTest.saveStep(step,idTask);
        //then
        assertThat(result.getBody()).isEqualTo(step);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    @Test
    void updateStep(){
        //given
        Steps step = prepareStep();
        Long idTask=1L;
        Long idStatus=1L;
        //when
        when(stepsService.updateStep(any(Steps.class),any(Long.class),any(Long.class))).thenReturn(step);
        ResponseEntity<Steps> result = underTest.updateStep(step, idTask,idStatus);
        //then
        assertThat(result.getBody()).isEqualTo(step);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getStep(){
        //given
        Steps steps = prepareStep();
        Long stepId = steps.getIdStep();
        //when
        when(stepsService.getStep(any())).thenReturn(steps);
        ResponseEntity<Steps> result = underTest.getStep(stepId);
        //then
        assertThat(result.getBody()).isEqualTo(steps);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getAllTaskSteps(){
        //given
        Steps step1 = prepareStep();
        Steps step2 = prepareStep();
        step2.setIdStep(2L);
        List<Steps> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        Task task = prepareTask();
        Long taskId=task.getTaskId();
        //when
        when(stepsService.getAllTaskSteps(taskId)).thenReturn(steps);
        ResponseEntity<List<Steps>> result = underTest.getAllTaskSteps(taskId);
        //then
        assertThat(result.getBody()).isEqualTo(steps);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    void deleteStep(){
        //given
        Long id=1L;
        //then
        underTest.deleteStep(id);
        verify(stepsService).deleteStep(id);

    }
}