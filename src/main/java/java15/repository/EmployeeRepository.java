package java15.repository;

import java15.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findUserByEmailEqualsIgnoreCase(String email);

    Optional<Object> findByEmail(String email);
}
