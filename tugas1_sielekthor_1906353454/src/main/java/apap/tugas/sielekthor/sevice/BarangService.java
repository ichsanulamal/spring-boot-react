package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.BarangModel;
import java.util.List;

public interface BarangService {
    void addBarang(BarangModel barang);
    void updateBarang(BarangModel barang);
    void removeBarang(BarangModel barang);
    List<BarangModel> getBarangList();
    List<BarangModel> getBarangListSorted();
    List<BarangModel> getBarangListByTipeAndStok(Long tipeID, boolean isAvailable);
    BarangModel getBarangById(Long id);
//    BarangModel getBarangByKodeBarang(String kodeBarang);
}

