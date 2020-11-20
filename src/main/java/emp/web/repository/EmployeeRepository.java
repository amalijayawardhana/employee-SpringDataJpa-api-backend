package emp.web.repository;

import emp.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Employee getFirstLastCustomerIdByOrderByIdDesc() throws SQLException;
}
