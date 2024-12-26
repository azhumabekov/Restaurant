package java15.repository;

import java15.models.Employee;
import java15.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findUserByEmailEqualsIgnoreCase(String email);

    Optional<Object> findByEmail(String email);

    List<Employee> findByRestaurant(Restaurant restaurant);

    List<Employee> findEmployeeById(Long id);
}
