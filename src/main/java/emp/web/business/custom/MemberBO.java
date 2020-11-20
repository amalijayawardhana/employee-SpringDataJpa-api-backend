package emp.web.business.custom;

import emp.web.business.SuperBO;
import emp.web.dto.MemberDTO;
import emp.web.entity.Employee;

import java.util.List;

public interface MemberBO extends SuperBO {
        List<MemberDTO> getAllMembers() throws Exception;
        void saveMember(int id, String firstName, String surname, String relationship, Employee empcode) throws Exception;
        void updateMember(int id,String firstName, String surname, String relationship,Employee empcode) throws Exception;
        void deleteMember(int id) throws Exception;
        MemberDTO getMember(int id) throws Exception;
        boolean MemberExist(int id);

    }
