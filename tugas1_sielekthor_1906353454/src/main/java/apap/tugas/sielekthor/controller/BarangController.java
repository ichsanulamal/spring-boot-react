package apap.tugas.sielekthor.controller;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.sevice.BarangService;
import apap.tugas.sielekthor.sevice.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BarangController {
    @Autowired
    BarangService barangService;

    @Autowired
    TipeService tipeService;

    @GetMapping("/barang")
    private String listBarang(Model model) {
            model.addAttribute("listBarang", barangService.getBarangList());
            return "barang/view-list-barang";
    }

    @GetMapping("/barang/cari")
    private String cariDaftarBarang(Model model) {
        List<TipeModel> listTipe = tipeService.getTipeList();
        model.addAttribute("listTipe", listTipe);
        return "barang/cari-barang";
    }

    @GetMapping("/barang/cari/stok")
    private String cariDaftarBarangResult(Model model,
                                          @RequestParam Long tipeBarang,
                                          @RequestParam(defaultValue = "1") boolean isAvailable) {
        List<TipeModel> listTipe = tipeService.getTipeList();
        model.addAttribute("listTipe", listTipe);

        List<BarangModel> listBarang = barangService.getBarangListByTipeAndStok(tipeBarang, isAvailable);
        model.addAttribute("listBarang", listBarang);

        return "barang/cari-barang";
    }

    @GetMapping("/barang/{idBarang}")
    private String detailBarang(Model model,
                                @PathVariable(value = "idBarang") Long id){
        model.addAttribute("barang", barangService.getBarangById(id));
        return "barang/detail-barang";
    }

    @GetMapping("/barang/tambah")
    private String tambahBarang(Model model){
        BarangModel barang = new BarangModel();
        List<TipeModel> listTipe = tipeService.getTipeList();

        model.addAttribute("barang", barang);
        model.addAttribute("listTipe", listTipe);
        return "barang/form-tambah-barang";
    }

    @PostMapping("/barang/tambah")
    public String tambahBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        // check barang masuk
        try {
            barangService.addBarang(barang);
        } catch(Exception ex) {
            redirectAttributes.addFlashAttribute("falseQty", true);
            return "redirect:/barang/tambah";
        }

        model.addAttribute("kodeBarang", barang.getKodeBarang());
        return "barang/tambah-barang";
    }

    @GetMapping("/barang/ubah/{idBarang}")
    private String ubahBarang(Model model,
                              @PathVariable(value = "idBarang") Long idBarang){
        BarangModel barang = barangService.getBarangById(idBarang);
        List<TipeModel> listTipe = tipeService.getTipeList();

        model.addAttribute("listTipe", listTipe);
        model.addAttribute("barang", barang);
        return "barang/form-ubah-barang";
    }

    @PostMapping("/barang/ubah")
    private String ubahBarang(Model model,
                              @ModelAttribute BarangModel barang,
                              RedirectAttributes redirectAttributes
                              ){
        try {
            barangService.updateBarang(barang);
        } catch(Exception ex) {
            redirectAttributes.addFlashAttribute("falseQty", true);
            return "redirect:/barang/ubah/" + barang.getId();
        }
        model.addAttribute("kode", barang.getKodeBarang());
        return "barang/ubah-barang";
    }

    @GetMapping("/barang/hapus")
    private String hapusBarang(Model model,
                              @RequestParam Long idBarang,
                               RedirectAttributes redirectAttributes){
        BarangModel barang = barangService.getBarangById(idBarang);
        try {
            barangService.removeBarang(barang);
        } catch(Exception ex) { // SQLIntegrityConstraintViolationException
            redirectAttributes.addFlashAttribute("isFailDeleted", 1);
            return "redirect:/barang";
        }

        model.addAttribute("kode", barang.getKodeBarang());
        return "barang/hapus-barang";
    }

    @PostMapping("/barang/hapus")
    public String hapusBarangSubmit(
            @ModelAttribute BarangModel barang
    ){
        barangService.removeBarang(barang);
        return "hapus-barang";
    }
}
