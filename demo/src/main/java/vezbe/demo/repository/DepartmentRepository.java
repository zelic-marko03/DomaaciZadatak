package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezbe.demo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
