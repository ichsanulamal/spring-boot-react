package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.MemberModel;
import java.util.List;

public interface MemberService {
    void addMember(MemberModel member);
    void updateMember(MemberModel member);
    void removeMember(MemberModel member);
    List<MemberModel> getMemberList();
    List<MemberModel> getMemberListSorted();
    MemberModel getMemberById(Long id);
//    MemberModel getMemberByKodeMember(String kodeMember);
}

