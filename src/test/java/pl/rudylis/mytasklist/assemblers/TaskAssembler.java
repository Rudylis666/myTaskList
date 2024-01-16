package pl.rudylis.mytasklist.assemblers;

import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Task.Task;

import java.time.LocalDate;

import static pl.rudylis.mytasklist.assemblers.StatusAssembler.prepareStatusUndone;

public class TaskAssembler {
    public static Task prepareTask(){
        Status status=prepareStatusUndone();
        return Task.builder()
                .taskId(1L)
                .setDate(LocalDate.now())
                .dueDate(LocalDate.of(2024,12,24))
                .title("Make a code")
                .description("Make a compiling programe in Java")
                .status(status)
                .build();
    }
}
