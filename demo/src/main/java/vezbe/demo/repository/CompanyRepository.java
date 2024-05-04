package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezbe.demo.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
