package pl.rudylis.mytasklist.Steps;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepsRepository extends JpaRepository<Steps,Integer> {
    List<Steps> findByTaskTaskId(Long taskId);
}
