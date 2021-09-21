package apap.tutorial.kebunsafari.controller;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import apap.tutorial.kebunsafari.service.KebunSafariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            @RequestParam (value = "id", required = true) String idKebunSafari,
            @RequestParam (value = "nama", required = true) String namaKebunSafari,
            @RequestParam (value = "alamat", required = true) String alamat,
            @RequestParam (value = "noTelepon", required = true) String noTelepon,
            Model model
    ) {
        // Membuat Objek Kebun Safari Baru
        KebunSafariModel kebunSafari = new KebunSafariModel(idKebunSafari, namaKebunSafari, alamat, noTelepon);

        // Memanggil service addKebunSafari
        kebunSafariService.addKebunSafari(kebunSafari);

        // Menambahkan variable kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "add-kebun-safari";
    }

    @RequestMapping("/")
    public String listKebunSafari(Model model) {
        //Mendapatkan list seluruh objek Kebun Safari
        List<KebunSafariModel> listKebunSafari = kebunSafariService.getKebunSafariList();

        //Menambahkan list untuk dirender di Thymeleaf
        model.addAttribute("listKebunSafari", listKebunSafari);

        //Mereturn template html yang dipakai
        return "get-all-kebun-safari";
    }

    @RequestMapping("/kebun-safari")
    public String getKebunSafariById(@RequestParam(value = "id", required = true) String idKebunSafari, Model model) {
        //Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);
        if (kebunSafari == null) {
            return "gagal";
        }

        // Menambahkan objek untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "detail-kebun-safari";
    }

    @RequestMapping("/kebun-safari/view/{id}")
    public String getKebunSafariByIdPath(@PathVariable(value = "id") String idKebunSafari, Model model) {
        //Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);
        if (kebunSafari == null) {
            return "gagal";
        }

        // Menambahkan objek untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "detail-kebun-safari";
    }

    @RequestMapping("/kebun-safari/update/{id}")
    public String updateKebunSafari(
            @RequestParam (value = "noTelepon", required = true) String noTelepon,
            @PathVariable (value = "id") String id,
            Model model
    ) {
        // Mencari objek kebun berdasarkan id dan mengubah noTeleponnya
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(id);
        if (kebunSafari == null) {
            return "gagal";
        }
        kebunSafari.setNoTelepon(noTelepon);

        // Menambahkan variable kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "update-kebun-safari";
    }

    @RequestMapping("/kebun-safari/delete/{id}")
    public String addKebunSafari(
            @PathVariable (value = "id") String id,
            Model model
    ) {
        // Mencari objek kebun berdasarkan id dan menghapusnya
        List<KebunSafariModel> kebunSafari = kebunSafariService.getKebunSafariList();
        if(!kebunSafari.removeIf(obj -> obj.getIdKebunSafari().equals(id))) {
            return "gagal";
        }

        // Menambahkan variable kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        // Mereturn template html yang dipakai
        return "delete-kebun-safari";
    }


}
