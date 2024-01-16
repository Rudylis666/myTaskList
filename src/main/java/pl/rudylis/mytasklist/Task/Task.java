package pl.rudylis.mytasklist.Task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Steps.Steps;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private LocalDate setDate;
    private LocalDate dueDate;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "taskStatus")
    private Status status;
    @OneToMany(mappedBy = "task")
    private Set<Steps> stepsSet;
}
