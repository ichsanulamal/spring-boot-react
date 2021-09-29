package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.emsidi.service.CabangService;

import java.time.LocalTime;

@Controller
public class PegawaiController {
    @Qualifier("pegawaiServiceImpl")
    @Autowired
    PegawaiService pegawaiService;

    @Qualifier("cabangServiceImpl")
    @Autowired
    CabangService cabangService;

    @GetMapping("/pegawai/add/{noCabang}")
    public String addPegawaiForm(@PathVariable Long noCabang, Model model) {
        PegawaiModel pegawai = new PegawaiModel();
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        pegawai.setCabang(cabang);
        model.addAttribute("noCabang", noCabang);
        model.addAttribute("pegawai", pegawai);
        return "form-add-pegawai";
    }

    @PostMapping("/pegawai/add")
    public String addPegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ) {
        pegawaiService.addPegawai(pegawai);
        model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
        model.addAttribute("namaPegawai", pegawai.getNamaPegawai());
        return "add-pegawai";
    }

    @GetMapping("/pegawai/update/{noPegawai}")
    public String updatePegawaiForm(@PathVariable Long noPegawai, Model model) {
        PegawaiModel pegawai = pegawaiService.getPegawaiByNoPegawai(noPegawai);
        if (pegawai != null) {
            if (pegawaiService.isEditValid(pegawai)) {
                model.addAttribute("pegawai", pegawai);
                model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
                return "form-update-pegawai";
            }
        }
        return "gagal";
    }

    @PostMapping("/pegawai/update")
    public String updatePegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            @RequestParam(value = "noCabang") Long noCabang,
            Model model
    ) {
        pegawai.setCabang(cabangService.getCabangByNoCabang(noCabang));
        pegawaiService.updatePegawai(pegawai);
        model.addAttribute("namaPegawai", pegawai.getNamaPegawai());
        model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
        return "update-pegawai";
    }

    @GetMapping("/pegawai/delete/{noPegawai}")
    public String deletePegawaiForm(
            @PathVariable Long noPegawai,
            Model model
    ) {
        PegawaiModel pegawai = pegawaiService.getPegawaiByNoPegawai(noPegawai);
        if (pegawai != null) {
            if (pegawaiService.isEditValid(pegawai)) {
                model.addAttribute("pegawai", pegawai);
                model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
                return "form-delete-pegawai";
            }
        }
        return "gagal";

    }

    @PostMapping("/pegawai/delete")
    public String deletePegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ) {
        model.addAttribute("noPegawai", pegawai.getNoPegawai());
        pegawaiService.deletePegawai(pegawai);
        return "delete-pegawai";
    }

}
