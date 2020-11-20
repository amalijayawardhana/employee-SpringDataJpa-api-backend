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
            Members.add(new MemberDTO(member.getCode(), member.getDescription(), member.getUnitPrice(), member.getQtyOnHand()));
        }
        return Members;
    }

    public void saveMember(String code, String description, double unitPrice, int qtyOnHand) throws Exception {
        MemberRepository.save(new Member(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public void updateMember(String code, String description, double unitPrice, int qtyOnHand) throws Exception {
        MemberRepository.save(new Member(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public void deleteMember(String code) throws Exception {
        MemberRepository.deleteById(code);
    }

    @Override
    public MemberDTO getMember(String id) throws Exception {
        Member member = MemberRepository.findById(id).get();
        return new MemberDTO(member.getCode(), member.getDescription(), member.getUnitPrice(), member.getQtyOnHand());
    }

    @Override
    public boolean MemberExist(String id) {
        try {
            MemberRepository.findById(id).get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional(readOnly = true)
    public String generateNewMemberId() throws SQLException {
        String lastMemberId = MemberRepository.getFirstLastMemberCodeByOrderByCodeDesc().getCode();
        int lastNumber = Integer.parseInt(lastMemberId.substring(1, 4));
        if (lastNumber == 0) {
            lastNumber++;
            return "I001";
        } else if (lastNumber < 9) {
            lastNumber++;
            return "I00" + lastNumber;
        } else if (lastNumber < 99) {
            lastNumber++;
            return "I0" + lastNumber;
        } else {
            lastNumber++;
            return "I" + lastNumber;
        }
    }
}
