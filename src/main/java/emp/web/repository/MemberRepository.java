package emp.web.repository;

import emp.web.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface MemberRepository extends JpaRepository<Member,String> {
}
