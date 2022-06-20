package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.MemberDb;
import apap.tugas.sielekthor.repository.PembelianDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDb memberDb;

    @Autowired
    PembelianDb pembelianDb;

    @Override
    public void addMember(MemberModel member) {
        memberDb.save(member);
    }

    @Override
    public void updateMember(MemberModel member) {
        memberDb.save(member);
        for (PembelianModel p: pembelianDb.findByMember_Id(member.getId())) {
            p.setNoInvoice(((int) Character.toLowerCase(member.getNamaMember().charAt(0)) - 96) % 10
                    + p.getNoInvoice().substring(1));
            pembelianDb.save(p);
        }
    }

    @Override
    public void removeMember(MemberModel member) {
        memberDb.delete(member);
    }

    @Override
    public List<MemberModel> getMemberList() {
        return memberDb.findAll();
    }

    @Override
    public List<MemberModel> getMemberListSorted() {
        return null;
    }

    @Override
    public MemberModel getMemberById(Long id) {
        Optional<MemberModel> member = memberDb.findById(id);
        if (member.isPresent()) {
            return member.get();
        }
        return null;
    }


}
