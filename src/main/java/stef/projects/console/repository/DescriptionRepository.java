package stef.projects.console.repository;

import java.sql.SQLException;
import java.util.List;

public interface DescriptionRepository<T> {
    public List<T> findByDescription(String... strings) throws SQLException;
}
