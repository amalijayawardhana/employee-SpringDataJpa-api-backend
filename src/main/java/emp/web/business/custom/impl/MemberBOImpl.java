package emp.web.business.custom.impl;

import emp.web.business.custom.MemberBO;
import emp.web.dto.MemberDTO;
import emp.web.repository.MemberRepository;
import emp.web.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class MemberBOImpl implements MemberBO {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDTO> getAllMembers() throws Exception {

        List<Member> allMembers = null;
        List<MemberDTO> Members = new ArrayList<>();
        allMembers = MemberRepository.findAll();
        for (Member member : allMembers) {
            
        }
        return Members;
    }


    }
}
