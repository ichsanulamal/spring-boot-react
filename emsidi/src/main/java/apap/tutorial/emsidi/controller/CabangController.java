package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class CabangController {

    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/cabang/add")
    public String addCabangForm(Model model) {
        List<MenuModel> listMenu = new ArrayList<>();
        listMenu.add(new MenuModel());

        CabangModel cabang = new CabangModel();
        cabang.setListMenu(listMenu);

        model.addAttribute("cabang", cabang);
        model.addAttribute("listMenu", menuService.getListMenu());
        return "form-add-cabang";
    }

    @PostMapping(value = "/cabang/add")
    public String addCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        try { // valid form
            cabangService.addCabang(cabang);
            model.addAttribute("noCabang", cabang.getNoCabang());
            return "add-cabang";
        } catch(Exception e) { //unvalid form
            model.addAttribute("cabang", cabang);
            model.addAttribute("listMenu", menuService.getListMenu());
            return "form-add-cabang";
        }
    }

    @PostMapping(value = "/cabang/add", params="tambahMenu")
    public String tambahMenu(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        try {
            cabang.getListMenu().add(new MenuModel());
        } catch(Exception e) {
            List<MenuModel> m = new ArrayList<>();
            m.add(new MenuModel());
            cabang.setListMenu(m);
        }
        model.addAttribute("cabang", cabang);
        model.addAttribute("listMenu", menuService.getListMenu());
        return "form-add-cabang";
    }

    @PostMapping(value = "/cabang/add", params="hapusMenu")
    public String hapusMenu(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        if (cabang.getListMenu().size() > 0) {
            cabang.getListMenu().remove(0);
        }
        model.addAttribute("cabang", cabang);
        model.addAttribute("listMenu", menuService.getListMenu());
        return "form-add-cabang";
    }

    @GetMapping("/cabang/viewall")
    public String listCabang(Model model) {
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang", listCabang);
        
        return "viewall-cabang";
    }

    // Latihan 1: Terurut berdasarkan nama cabang
    @GetMapping("/cabang/viewAll")
    public String listCabangTerurut(Model model) {
        List<CabangModel> listCabang = cabangService.getCabangListSorted();
        model.addAttribute("listCabang", listCabang);
        
        return "viewall-cabang";
    }


    @GetMapping("/cabang/view")
    public String viewDetailCabang(
            @RequestParam(value = "noCabang") Long noCabang,
            Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        if (cabang == null) return "error/404";

        List<PegawaiModel> listPegawai = cabang.getListPegawai();
        List<MenuModel> listMenu = cabang.getListMenu();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("listMenu", listMenu);

        return "view-cabang";
    }

    @GetMapping("/cabang/update/{noCabang}")
    public String updateCabangForm(
            @PathVariable Long noCabang,
            Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        model.addAttribute("cabang", cabang);
        return "form-update-cabang";
    }

    @PostMapping("/cabang/update")
    public String updateCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        cabangService.updateCabang(cabang);
        model.addAttribute("noCabang", cabang.getNoCabang());
        return "update-cabang";
    }

    // Latihan 4: Delete Cabang saat tidak memiliki pegawai dan sedang tutup
    @GetMapping("/cabang/delete/{noCabang}")
    public String deleteCabang(@PathVariable Long noCabang, Model model) {
        LocalTime now = LocalTime.now();
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        if ((now.isBefore(cabang.getWaktuBuka()) || now.isAfter(cabang.getWaktuTutup()))
                && cabang.getListPegawai().isEmpty()) {
            cabangService.removeCabang(cabang);
            model.addAttribute("noCabang", cabang.getNoCabang());
            return "delete-cabang";
        }
        return "error-cannot-delete";
    }

}
