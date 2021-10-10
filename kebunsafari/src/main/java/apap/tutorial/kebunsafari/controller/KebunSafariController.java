package apap.tutorial.kebunsafari.controller;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import apap.tutorial.kebunsafari.service.KebunSafariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class KebunSafariController {
    @Autowired
    private KebunSafariService kebunSafariService;

    @RequestMapping("/kebun-safari/add")
    public String addKebunSafari(
            @RequestParam (value="id", required = true) String idKebunSafari,
            @RequestParam (value="nama", required = true) String namaKebunSafari,
            @RequestParam (value="alamat", required = true) String alamat,
            @RequestParam (value="noTelepon", required = true) String noTelepon,
            Model model
    ){
        // Membuat Objek Kebun Safari baru
        KebunSafariModel kebunSafari = new KebunSafariModel(idKebunSafari, namaKebunSafari, alamat, noTelepon);

        // Memanggil service addKebunSafari
        kebunSafariService.addKebunSafari(kebunSafari);

        // Menambahkan variabel kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "add-kebun-safari";
    }

    @RequestMapping("/")
    public String listKebunSafari(Model model){
        // Mendapatkan list seluruh objek Kebun Safari
        List<KebunSafariModel> listKebunSafari = kebunSafariService.getKebunSafariList();

        // Menambahkan list untuk dirender di Thymeleaf
        model.addAttribute("listKebunSafari", listKebunSafari);

        // Mereturn template html yang dipakai
        return "get-all-kebun-safari";
    }

    @RequestMapping("/kebun-safari")
    public String getKebunSafariById(@RequestParam(value="id", required = true) String idKebunSafari, Model model){
        try{
            // Mendapatkan Objek Kebun Safari sesuai dengan ID
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

            // Tampilkan error apabila tidak terdapat Kebun Safari
            if(kebunSafari == null) throw new Exception("Kebun Safari Tidak Ditemukan");

            // Menambahkan objek untuk dirender di Thymeleaf
            model.addAttribute("kebunSafari", kebunSafari);

            // Mereturn template html yang dipakai
            return "detail-kebun-safari";
        } catch (Exception e) {
            // Mereturn template html lain apabila terdapat error
            return "kebun-tidak-ditemukan";
        }
    }

    @GetMapping(value = "/kebun-safari/view/{idKebunSafari}")
    public String getKebunSafariByIdPath(@PathVariable String idKebunSafari, Model model){
        try{
            // Mendapatkan Objek Kebun Safari sesuai dengan ID
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

            // Tampilkan error apabila tidak terdapat Kebun Safari
            if(kebunSafari == null) throw new Exception("Kebun Safari Tidak Ditemukan");

            // Menambahkan objek untuk dirender di Thymeleaf
            model.addAttribute("kebunSafari", kebunSafari);

            // Mereturn template html yang dipakai
            return "detail-kebun-safari";
        } catch (Exception e) {
            // Mereturn template html lain apabila terdapat error
            return "kebun-tidak-ditemukan";
        }
    }

    @GetMapping(value = "/kebun-safari/update/{idKebunSafari}")
    public String updateKebunSafariNoTelepon(@PathVariable String idKebunSafari, @RequestParam(value = "noTelepon", required = true) String noTelepon, Model model){
        try{
            // Update Objek Kebun Safari
            KebunSafariModel kebunSafari = kebunSafariService.updateNoTeleponKebunSafariByIdKebunSafari(idKebunSafari, noTelepon);

            // Tampilkan error apabila tidak terdapat Kebun Safari
            if(kebunSafari == null) throw new Exception("Kebun Safari Tidak Ditemukan");

            // Menambahkan objek untuk dirender di Thymeleaf
            model.addAttribute("kebunSafari", kebunSafari);

            // Mereturn template html yang dipakai
            return "update-kebun-safari";
        } catch (Exception e) {
            // Mereturn template html lain apabila terdapat error
            return "kebun-tidak-ditemukan";
        }
    }

    @GetMapping(value = "/kebun-safari/delete/{idKebunSafari}")
    public String deleteKebunSafariByIdKebunSafari(@PathVariable String idKebunSafari, Model model){
        try{
            // Update Objek Kebun Safari
            boolean success = kebunSafariService.deleteKebunSafari(idKebunSafari);

            // Tampilkan error apabila tidak terdapat Kebun Safari
            if(!success) throw new Exception("Penghapusan Kebun Safari Gagal");

            // Menambahkan objek untuk dirender di Thymeleaf
            model.addAttribute("idKebunSafari", idKebunSafari);

            // Mereturn template html yang dipakai
            return "delete-kebun-safari";
        } catch (Exception e) {
            // Mereturn template html lain apabila terdapat error
            return "kebun-tidak-ditemukan";
        }
    }
}
