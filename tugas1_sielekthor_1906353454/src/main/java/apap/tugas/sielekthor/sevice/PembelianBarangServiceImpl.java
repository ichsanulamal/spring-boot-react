package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.PembelianBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PembelianBarangServiceImpl implements PembelianBarangService {
    @Autowired
    PembelianBarangDb pembelianBarangDb;

    @Override
    public void addPembelianBarang(PembelianBarangModel pembelianBarang) {
        pembelianBarangDb.save(pembelianBarang);
    }

    @Override
    public void updatePembelianBarang(PembelianBarangModel pembelianBarang) {
        pembelianBarangDb.save(pembelianBarang);
    }

    @Override
    public void removePembelianBarang(PembelianBarangModel pembelianBarang) {
        pembelianBarangDb.delete(pembelianBarang);
    }

    @Override
    public List<PembelianBarangModel> getPembelianBarangList() {
        return pembelianBarangDb.findAll();
    }

    @Override
    public List<PembelianBarangModel> getPembelianBarangListSorted() {
        return null;
    }

    @Override
    public PembelianBarangModel getPembelianBarangById(Long id) {
        Optional<PembelianBarangModel> pembelianBarang = pembelianBarangDb.findById(id);
        if (pembelianBarang.isPresent()) {
            return pembelianBarang.get();
        }
        return null;
    }
    @Override
    public List<PembelianBarangModel> getPembelianBarangListByPembelian(Long idPembelian) {
        return pembelianBarangDb.findPembelianBarangModelByBarang_Id(idPembelian);
    }

}