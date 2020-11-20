package emp.web.dto;

import emp.web.entity.Employee;
import emp.web.entity.SuperEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO{
    private int id;
    private String firstName;
    private String surname;
    private String relationship;
    private Employee empCode;
}
