package apap.tutorial.kebunsafari.service;

import apap.tutorial.kebunsafari.model.KebunSafariModel;

import java.util.List;

public interface KebunSafariService {
    // Method untuk menambahkan KebunSafari baru
    void addKebunSafari(KebunSafariModel kebunSafari);

    // Method untuk mendapatkan seluruh daftar KebunSafari
    List<KebunSafariModel> getKebunSafariList();

    // Method untuk mendapatkan data sebuah KebunSafari berdasarkan ID yang dimiliki
    KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari);

    // Method untuk mendapatkan data sebuah KebunSafari berdasarkan ID yang dimiliki
    KebunSafariModel updateNoTeleponKebunSafariByIdKebunSafari(String idKebunSafari, String noTelepon);

    // Method untuk menghapus kebun safari
    boolean deleteKebunSafari(String idKebunSafari);
}
