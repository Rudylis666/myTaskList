package pl.rudylis.mytasklist.Steps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;
import pl.rudylis.mytasklist.Task.Task;
import pl.rudylis.mytasklist.Task.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.rudylis.mytasklist.assemblers.StatusAssembler.prepareStatusUndone;
import static pl.rudylis.mytasklist.assemblers.StepsAssembler.prepareStep;
import static pl.rudylis.mytasklist.assemblers.TaskAssembler.prepareTask;

@ExtendWith(MockitoExtension.class)
class StepsServiceTest {

    @Mock
    private StepsRepository stepsRepository;
    @Mock
    private StatusRepository statusRepository;
    @Mock
    private TaskRepository taskRepository;
    private StepsService underTest;
    @BeforeEach
    void setUp() {
        underTest = new StepsService(stepsRepository,statusRepository,taskRepository);
    }

    @Test
    void saveStep() {
        //given
        Steps step = prepareStep();
        step.setIdStep(null);
        Status status = prepareStatusUndone();
        Task task = prepareTask();
        //when
        when(taskRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(task));
        when(statusRepository.getReferenceById(any())).thenReturn(status);
        when(stepsRepository.save(any(Steps.class))).thenReturn(step);
        Steps result = underTest.saveStep(step,task.getTaskId());
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(step);
    }
    @Test
    void saveStepWithStepId(){
        //given
        Steps step = prepareStep();
        Long idTask=1L;
        //then
        assertThatThrownBy(()-> underTest.saveStep(step,idTask))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Już jest krok o takim id");

    }
    @Test
    void saveStepWithNullTaskId(){
        //given
        Steps step = prepareStep();
        Long idTask=null;
        //then
        assertThatThrownBy(()-> underTest.saveStep(step,idTask))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma takiego tasku");

    }
    @Test
    void saveStepWithNotRecognizedTaskId(){
        //given
        Steps step = prepareStep();
        step.setIdStep(null);
        Long idTask=1L;
        //then
        assertThatThrownBy(()-> underTest.saveStep(step,idTask))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma takiego tasku");
    }

    @Test
    void updateStatus(){
        //given
        Steps steps = prepareStep();
        steps.setDescription("Nowy opis");
        Status status = prepareStatusUndone();
        Task task = prepareTask();
        //when
        when(taskRepository.getReferenceById(any())).thenReturn(task);
        when(statusRepository.getReferenceById(any())).thenReturn(status);
        when(stepsRepository.save(any(Steps.class))).thenReturn(steps);
        //then
        Steps result = underTest.updateStep(steps,task.getTaskId(),status.getIdStatus());
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(steps);
        assertThat(result.getDescription()).isEqualTo("Nowy opis");
    }
    @Test
    void updateStepWhenTaskIdIsNull(){
        //given
        Steps steps = prepareStep();
        steps.setDescription("Nowy opis");
        Status status = prepareStatusUndone();
        Task task = prepareTask();
        task.setTaskId(null);
        //then
        assertThatThrownBy(()-> underTest.updateStep(steps,task.getTaskId(),status.getIdStatus()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Id == null");
    }
    @Test
    void getStep(){
        //given
        Steps step = prepareStep();
        Long stepId= step.getIdStep();
        //when
        when(stepsRepository.getReferenceById(any())).thenReturn(step);
        Steps result = underTest.getStep(stepId);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(step);
    }
    @Test
    void getStepWhenIdIsNull(){
        //given
        Long stepId= null;
        //then
        assertThatThrownBy(()-> underTest.getStep(stepId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Id == null");
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
        when(taskRepository.getReferenceById(any())).thenReturn(task);
        when(stepsRepository.findByTaskTaskId(any())).thenReturn(steps);
        List<Steps> result = underTest.getAllTaskSteps(taskId);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(steps);
    }
    @Test
    void getAllTaskStepsWhenIdIsNull(){
        //given
        Long taskId=null;
        //then
        assertThatThrownBy(()-> underTest.getAllTaskSteps(taskId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Id == null");

    }
}