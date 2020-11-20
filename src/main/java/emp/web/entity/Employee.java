package emp.web.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements SuperEntity {
    @Id
    private String code;
    private String initials;
    private String firstName;
    private String surname;
    private String address1;
    private String address2;
    private Date dob;
    private String status;

}
