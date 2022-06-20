package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;

import java.util.List;

public interface PembelianBarangService {
    void addPembelianBarang(PembelianBarangModel pembelianBarang);
    void updatePembelianBarang(PembelianBarangModel pembelianBarang);
    void removePembelianBarang(PembelianBarangModel pembelianBarang);
    List<PembelianBarangModel> getPembelianBarangList();
    List<PembelianBarangModel> getPembelianBarangListSorted();
    List<PembelianBarangModel> getPembelianBarangListByPembelian(Long idPembelian);
    PembelianBarangModel getPembelianBarangById(Long id);

}

