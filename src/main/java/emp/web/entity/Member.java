package emp.web.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member implements SuperEntity {
    @Id
    private int id;
    private String firstName;
    private String surname;
    private String relationship;
    @ManyToOne
    @JoinColumn(name = "empCode", referencedColumnName = "code", nullable=false)
    private Employee employee;
}
