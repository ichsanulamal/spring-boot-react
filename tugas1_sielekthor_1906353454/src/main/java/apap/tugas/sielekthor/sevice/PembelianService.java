package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import java.util.List;

public interface PembelianService {
    boolean addPembelian(PembelianModel barang);
    void updatePembelian(PembelianModel barang);
    void removePembelian(PembelianModel barang);
    List<PembelianModel> getPembelianList();
    List<PembelianModel> getPembelianListByMember(Long idMember);
    PembelianModel getPembelianById(Long id);
    List getCountPembelianByMember();


}

