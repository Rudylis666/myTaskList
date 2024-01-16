package pl.rudylis.mytasklist.Task;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
    @Test
    void updateStatus(){
        //given
        Task task = prepareTask();
        Long statusId = 1L;
        //when
        when(taskService.updateStatus(any(Task.class),any(Long.class))).thenReturn(task);
        ResponseEntity<Task> result = underTest.updateStatus(task,statusId);
        //then
        assertThat(result.getBody()).isEqualTo(task);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getTask(){
        //given
        Task task = prepareTask();
        Long id = task.getTaskId();
        //when
        when(taskService.getTask(any(Long.class))).thenReturn(task);
        ResponseEntity<Task> result = underTest.getTask(id);
        //then
        assertThat(result.getBody()).isEqualTo(task);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getAllTasks(){
        //given
        Task task1 = prepareTask();
        Task task2 = prepareTask();
        task2.setTaskId(2L);
        List<Task> lista= new ArrayList<>();
        lista.add(task1);
        lista.add(task2);
        //when
        when(taskService.getAllTasks()).thenReturn(lista);
        ResponseEntity<List<Task>> result = underTest.getAllTasks();
        //then
        assertThat(result.getBody()).isEqualTo(lista);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void deleteTask(){
        Long id= 1L;
        underTest.deleteTask(id);
        verify(taskService).deleteTask(id);
    }

}
