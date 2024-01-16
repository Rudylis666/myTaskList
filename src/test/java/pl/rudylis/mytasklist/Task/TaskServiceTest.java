package pl.rudylis.mytasklist.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Status.StatusRepository;

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


}
