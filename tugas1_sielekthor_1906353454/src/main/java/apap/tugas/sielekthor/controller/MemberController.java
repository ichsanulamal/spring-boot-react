package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.sevice.MemberService;
import apap.tugas.sielekthor.sevice.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    PembelianService pembelianService;

    @GetMapping("/member")
    private String listMember(Model model) {
        model.addAttribute("listMember", memberService.getMemberList());
        return "member/view-list-member";
    }

    @GetMapping("/member/tambah")
    private String tambahMember(Model model){
        return "member/form-tambah-member";
    }

    @PostMapping("/member/tambah")
    private String tambahMemberSubmit(Model model, @ModelAttribute MemberModel member) {
        memberService.addMember(member);
        model.addAttribute("member", member.getNamaMember());
        return "member/tambah-member";
    }

    @GetMapping("/member/ubah/{idMember}")
    private String ubahMember(Model model,
                              @PathVariable(value = "idMember") Long idMember){
        MemberModel member = memberService.getMemberById(idMember);
        model.addAttribute("member", member);
        return "member/form-ubah-member";
    }

    @PostMapping("/member/ubah")
    private String ubahMemberSubmit(Model model, @ModelAttribute MemberModel member) {
        memberService.updateMember(member);
        model.addAttribute("member", member);
        model.addAttribute("listPembelian", pembelianService.getPembelianListByMember(member.getId()));

        return "member/ubah-member";
    }
}
