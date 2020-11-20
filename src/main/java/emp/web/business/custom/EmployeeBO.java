package emp.web.business.custom;

import emp.web.business.SuperBO;
import emp.web.dto.EmployeeDTO;

import java.sql.Date;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDTO> getAllEmployees() throws Exception;
    void saveEmployee(String code, String initials, String firstName, String surname, String address1, String address2, Date dob, String status) throws Exception;
    void updateEmployee(String code, String initials, String firstName, String surname, String address1, String address2, Date dob, String status) throws Exception;
    void deleteEmployee(String code) throws Exception;
    EmployeeDTO getEmployee(String code) throws Exception;
    boolean EmployeeExist(String code);
}
