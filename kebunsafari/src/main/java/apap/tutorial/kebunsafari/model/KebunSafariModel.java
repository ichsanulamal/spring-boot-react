package apap.tutorial.kebunsafari.model;

public class KebunSafariModel {
    private String idKebunSafari;
    private String namaKebunSafari;
    private String alamat;
    private String noTelepon;

    public KebunSafariModel(String idKebunSafari, String namaKebunSafari, String alamat, String noTelepon) {
        this.idKebunSafari = idKebunSafari;
        this.namaKebunSafari = namaKebunSafari;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public String getIdKebunSafari() {
        return idKebunSafari;
    }

    public void setIdKebunSafari(String idKebunSafari) {
        this.idKebunSafari = idKebunSafari;
    }

    public String getNamaKebunSafari() {
        return namaKebunSafari;
    }

    public void setNamaKebunSafari(String namaKebunSafari) {
        this.namaKebunSafari = namaKebunSafari;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
}
