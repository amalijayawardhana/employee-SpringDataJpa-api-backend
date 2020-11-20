package emp.web.dto;


import emp.web.entity.SuperEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private String code;
    private String initials;
    private String firstName;
    private String surname;
    private String address1;
    private String address2;
    private Date dob;
    private String status;

}
