package pl.rudylis.mytasklist.Task;

import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.rudylis.mytasklist.assemblers.TaskAssembler.prepareTask;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskService taskService;
    private TaskController underTest;

    @BeforeEach
    void setUp() {
        underTest= new TaskController(taskService);
    }
    @Test
    void saveTask(){
        //given
        Task task = prepareTask();
        Long statusId = 1L;
        //when
        when(taskService.saveTask(any(Task.class),any(Long.class))).thenReturn(task);
        ResponseEntity<Task> result = underTest.saveTask(task,statusId);
        //then
        assertThat(result.getBody()).isEqualTo(task);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
