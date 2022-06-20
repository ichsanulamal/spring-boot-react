package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public void addBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public void updateBarang(BarangModel barang) {
        barangDb.save(barang);
    }

    @Override
    public void removeBarang(BarangModel barang) {
        barangDb.delete(barang);
    }

    @Override
    public List<BarangModel> getBarangList() {
        return barangDb.findAll();
    }

    @Override
    public List<BarangModel> getBarangListSorted() {
        return null;
    }

    @Override
    public BarangModel getBarangById(Long id) {
        Optional<BarangModel> barang = barangDb.findById(id);
        if (barang.isPresent()) {
            return barang.get();
        }
        return null;
    }

    @Override
    public List<BarangModel> getBarangListByTipeAndStok(Long tipeID, boolean isAvailable) {
        if (isAvailable) {
            return barangDb.findByTipeIdAndAndStokAvailable(tipeID);
        }
        return barangDb.findByTipeIdAndAndStokIsNotAvailable(tipeID);
    }
}
