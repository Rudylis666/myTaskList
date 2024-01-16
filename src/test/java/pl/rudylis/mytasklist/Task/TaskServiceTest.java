package pl.rudylis.mytasklist.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static pl.rudylis.mytasklist.assemblers.StatusAssembler.prepareStatusUndone;
import static pl.rudylis.mytasklist.assemblers.TaskAssembler.prepareTask;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private StatusRepository statusRepository;
    private TaskService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TaskService(taskRepository,statusRepository);
    }
    @Test
    void saveTask(){
        //given
        Task task = prepareTask();
        task.setTaskId(null);
        Status status = prepareStatusUndone();
        //when
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(statusRepository.getReferenceById(any())).thenReturn(status);
        Task result = underTest.saveTask(task,status.getIdStatus());
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(task);

    }
    @Test
    void saveTaskWhenIdExists(){
        //given
        Task task = prepareTask();
        Status status = prepareStatusUndone();
        //then
        assertThatThrownBy(()-> underTest.saveTask(task,status.getIdStatus()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Jest już takie id tasku");

    }

    @Test
    void updateStatus(){
        Task task = prepareTask();
        Long statusId = 2L;
        Status status = prepareStatusUndone();

        //when
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(statusRepository.getReferenceById(any())).thenReturn(status);
        Task result = underTest.updateStatus(task,statusId);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(task);
    }
    @Test
    void updateStatusWithNullId(){
        Task task = prepareTask();
        task.setTaskId(null);
        Long statusId = 2L;
        //then
        assertThatThrownBy(()-> underTest.updateStatus(task,statusId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma takiego tasku!");

    }

    @Test
    void getTask(){
        Task task = prepareTask();
        Long id = task.getTaskId();
        //when
        when(taskRepository.getReferenceById(any())).thenReturn(task);
        Task result = underTest.getTask(id);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(task);
    }
    @Test
    void getTaskWithNoId(){
        Long id = null;
        //then
        assertThatThrownBy(()-> underTest.getTask(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma takiego tasku!");

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
        when(taskRepository.findAll()).thenReturn(lista);
        List<Task> result = underTest.getAllTasks();
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(lista);
    }

    @Test
    void deleteTask(){
        //given
        Task task = prepareTask();
        //when
        when(taskRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(task));
        underTest.deleteTask(task.getTaskId());
        //then
        verify(taskRepository,times(1)).deleteById(Math.toIntExact(task.getTaskId()));
    }
    @Test
    void deleteTaskWhenIdIsNull(){
        //given
        Task task = prepareTask();
        Long id = null;
        task.setTaskId(null);
        assertThatThrownBy(()-> underTest.deleteTask(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma takiego tasku!");
    }
    @Test
    void deleteTaskWithNoExistingId(){
        //given
        Task task = prepareTask();
        assertThatThrownBy(()-> underTest.deleteTask(task.getTaskId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Nie ma tasku o takim id");
    }


}
