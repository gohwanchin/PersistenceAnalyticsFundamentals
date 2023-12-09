package vttp2022.paf.assessment.server.repositories;

import static vttp2022.paf.assessment.server.repositories.Queries.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.server.models.Task;

@Repository
public class TaskRepository {
    @Autowired
    private JdbcTemplate template;

    public boolean insertTask(Task task) {
        int added = template.update(SQL_INSERT_TASK, task.getDescription(), task.getPriority(), task.getDueDate(),
                task.getUserId());
        return added > 0;
    }
}
