package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService {

    @Autowired
    CabangDb cabangDb;

    @Override
    public void addCabang(CabangModel cabang) {
        cabangDb.save(cabang);
    }

    @Override
    public void updateCabang(CabangModel cabang) {
        cabangDb.save(cabang);
    }

    @Override
    public List<CabangModel> getCabangList() {
        return cabangDb.findAll();
    }

    @Override
    public List<CabangModel> getCabangListSorted() {
        return cabangDb.findAllByOrderByNamaCabang();
    }

    @Override
    public CabangModel getCabangByNoCabang(Long noCabang) {
        Optional<CabangModel> cabang = cabangDb.findByNoCabang(noCabang);
        if (cabang.isPresent()) {
            return cabang.get();
        }
        return null;
    }

    @Override
    public void removeCabang(CabangModel cabang) {
        cabangDb.delete(cabang);
    }

}
