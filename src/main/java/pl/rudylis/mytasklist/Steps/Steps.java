package pl.rudylis.mytasklist.Steps;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Task.Task;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
