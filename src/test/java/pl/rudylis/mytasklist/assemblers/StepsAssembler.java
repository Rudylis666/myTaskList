package pl.rudylis.mytasklist.assemblers;

import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Steps.Steps;
import pl.rudylis.mytasklist.Task.Task;

import java.time.LocalDate;

import static pl.rudylis.mytasklist.assemblers.StatusAssembler.prepareStatusUndone;
import static pl.rudylis.mytasklist.assemblers.TaskAssembler.prepareTask;

public class StepsAssembler {
    public static Steps prepareStep(){
        Task task = prepareTask();
        Status status=prepareStatusUndone();
        return Steps.builder()
                .idStep(1L)
                .task(task)
                .status(status)
                .description("Pierwszy krok")
                .setDate(LocalDate.now())
                .deadline(LocalDate.of(2024,03,13))
                .build();
    }
}
