package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.sevice.BarangService;
import apap.tugas.sielekthor.sevice.MemberService;
import apap.tugas.sielekthor.sevice.PembelianBarangService;
import apap.tugas.sielekthor.sevice.PembelianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PembelianController {
    @Autowired
    PembelianService pembelianService;

    @Autowired
    PembelianBarangService pembelianBarangService;

    @Autowired
    MemberService memberService;

    @Autowired
    BarangService barangService;

    @GetMapping("/pembelian")
    private String listPembelian(Model model) {
        model.addAttribute("listPembelian", pembelianService.getPembelianList());
        return "pembelian/view-list-pembelian";
    }

    @GetMapping("/pembelian/{idPembelian}")
    private String detailPembelian(Model model,
                                @PathVariable(value = "idPembelian") Long idPembelian){

        model.addAttribute("pembelian", pembelianService.getPembelianById(idPembelian));
        return "pembelian/detail-pembelian";
    }

    @GetMapping("/pembelian/tambah")
    private String tambahPembelian(Model model){
        PembelianModel pembelian = new PembelianModel();
        List<PembelianBarangModel> listPembelianBarang = new ArrayList<>();

        pembelian.setListPembelianBarang(listPembelianBarang);
        pembelian.getListPembelianBarang().add(new PembelianBarangModel());

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarangExisting", barangService.getBarangList());
        model.addAttribute("listMember", memberService.getMemberList());

        return "pembelian/form-tambah-pembelian";

    }

    @PostMapping(value = "/pembelian/tambah", params = {"save"})
    public String addPembelianSubmit(@ModelAttribute PembelianModel pembelian,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        String status;
        if (pembelian.getListPembelianBarang() == null) {
            status = "isFailed";
        } else {
            try {
                status = pembelianService.addPembelian(pembelian) ? "isCompleted" : "isNotCompleted";
            } catch (javax.validation.ConstraintViolationException ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("falseQty", true);
                return "redirect:/pembelian/tambah";
            }
        }
        model.addAttribute("status", status);
        return "pembelian/tambah-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = {"addRow"})
    private String addRowFilmMultiple(@ModelAttribute PembelianModel pembelian, Model model) {
        if (pembelian.getListPembelianBarang() == null || pembelian.getListPembelianBarang().size() == 0) {
            pembelian.setListPembelianBarang(new ArrayList<>());
        }
        pembelian.getListPembelianBarang().add(new PembelianBarangModel());

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listMember", memberService.getMemberList());
        model.addAttribute("listBarangExisting", barangService.getBarangList());
        return "pembelian/form-tambah-pembelian";
    }

    @PostMapping(value = "/pembelian/tambah", params = {"deleteRow"})
    private String deleteRowFilmMultiple(@ModelAttribute PembelianModel pembelian, @RequestParam("deleteRow") Integer row,
                                         Model model) {
        Integer rowId = Integer.valueOf(row);
        pembelian.getListPembelianBarang().remove(rowId.intValue());

        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listBarangExisting", barangService.getBarangList());
        model.addAttribute("listMember", memberService.getMemberList());

        return "pembelian/form-tambah-pembelian";
    }

    @GetMapping("/pembelian/hapus/{idPembelian}")
    private String hapusPembelian(Model model,
                              @PathVariable(value = "idPembelian") Long idPembelian){
        PembelianModel pembelian = pembelianService.getPembelianById(idPembelian);
        model.addAttribute("pembelian", pembelian);
        return "pembelian/form-hapus-pembelian";
    }

    @PostMapping("/pembelian/hapus")
    private String hapusPembelianSubmit(Model model,
                                  @RequestParam Long idPembelian){
        PembelianModel pembelian = pembelianService.getPembelianById(idPembelian);
        model.addAttribute("pembelian", pembelian);
        pembelianService.removePembelian(pembelian);
        return "pembelian/hapus-pembelian";
    }
}
