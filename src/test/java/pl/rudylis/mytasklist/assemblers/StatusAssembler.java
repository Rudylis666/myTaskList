package pl.rudylis.mytasklist.assemblers;

import pl.rudylis.mytasklist.Status.Status;

public class StatusAssembler {
    public static Status prepareStatusUndone(){
        return Status.builder()
                .idStatus(2L)
                .description("undone")
                .build();
    }

}
