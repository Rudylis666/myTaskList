package pl.rudylis.mytasklist.Steps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.rudylis.mytasklist.Status.Status;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.rudylis.mytasklist.assemblers.StatusAssembler.prepareStatusUndone;
import static pl.rudylis.mytasklist.assemblers.StepsAssembler.prepareStep;

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
}