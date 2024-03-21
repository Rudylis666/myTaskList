package pl.rudylis.mytasklist.Task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.rudylis.mytasklist.Status.Status;
import pl.rudylis.mytasklist.Steps.Steps;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
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
    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private Set<Steps> stepsSet;
}
