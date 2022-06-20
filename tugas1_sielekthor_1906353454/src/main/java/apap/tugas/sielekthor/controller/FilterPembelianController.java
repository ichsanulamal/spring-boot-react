package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.sevice.MemberService;
import apap.tugas.sielekthor.sevice.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilterPembelianController {
    @Autowired
    MemberService memberService;

    @Autowired
    PembelianService pembelianService;

    @GetMapping("/filter-pembelian")
    private String filterPembelian(Model model) {
        return "filter-pembelian/list-menu-filter";
    }

    @GetMapping("/cari/pembelian")
    private String cariDaftarPembelian(Model model,
                                       @RequestParam(defaultValue = "0") Long idMember,
                                       @RequestParam(defaultValue = "2") int isCicil){

        List<MemberModel> listMember = memberService.getMemberList();
        model.addAttribute("listMember", listMember);

        MemberModel member = memberService.getMemberById(idMember);

        if (member != null) {
            List<PembelianModel> listPembelian = member.getListPembelian();
            if (isCicil == 1) {
                listPembelian.removeIf(pb -> pb.getIsCash());
            } else if (isCicil == 0) {
                listPembelian.removeIf(pb -> !pb.getIsCash());
            }
            model.addAttribute("listPembelian", listPembelian);
        }

        return "filter-pembelian/cari-pembelian";
    }

    // Bonus
    @GetMapping("bonus/cari/member/paling-banyak")
    private String bonus(Model model){
        // query manual
        List queryList = pembelianService.getCountPembelianByMember();
        model.addAttribute("queryList", queryList);
        return "filter-pembelian/bonus";
    }
}
