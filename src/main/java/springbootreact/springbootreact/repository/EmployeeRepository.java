package springbootreact.springbootreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootreact.springbootreact.model.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
}
