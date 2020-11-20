package emp.web.business.custom.impl;

import emp.web.business.custom.EmployeeBO;
import emp.web.dto.EmployeeDTO;
import emp.web.entity.Member;
import emp.web.repository.EmployeeRepository;
import emp.web.entity.Employee;
import emp.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class EmployeeBOImpl implements EmployeeBO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        List<Employee> allEmployees = null;
        List<EmployeeDTO> EmployeeTMS = new ArrayList<>();
            allEmployees = employeeRepository.findAll();
        for (Employee employee : allEmployees) {
            EmployeeTMS.add(new EmployeeDTO(employee.getCode(), employee.getInitials(), employee.getFirstName(),employee.getSurname(),
                    employee.getAddress1(),employee.getAddress2(),employee.getDob(),employee.getStatus()));
        }
        return EmployeeTMS;
    }

    @Override
    public void saveEmployee(String code, String initials, String firstName, String surname, String address1, String address2, Date dob, String status) throws Exception {
        employeeRepository.save(new Employee(code, initials, firstName, surname, address1, address2, dob, status));
    }

    @Override
    public void updateEmployee(String code, String initials, String firstName, String surname, String address1, String address2, Date dob, String status) throws Exception {
        employeeRepository.save(new Employee(code, initials, firstName, surname, address1, address2, dob, status));
    }


    public void deleteEmployee(String code) throws Exception {
        employeeRepository.deleteById(code);
    }

    @Override
    public EmployeeDTO getEmployee(String code) throws Exception {
        return null;
    }

    @Override
    public boolean EmployeeExist(String code){
        try {
            employeeRepository.findById(code).get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
