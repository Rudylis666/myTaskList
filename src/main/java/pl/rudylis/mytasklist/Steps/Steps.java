package pl.rudylis.mytasklist.Steps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Task.Task;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "steps")
public class Steps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStep;
    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;
    private String description;
    private LocalDate setDate;
    private LocalDate deadline;

}
