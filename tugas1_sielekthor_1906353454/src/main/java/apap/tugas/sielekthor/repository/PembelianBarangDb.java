package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PembelianBarangDb extends JpaRepository<PembelianBarangModel, Long> {
    Optional<PembelianBarangModel> findById(Long id);

    List<PembelianBarangModel> findPembelianBarangModelByBarang_Id(Long idPembelian);

    List<PembelianBarangModel> findAll();
}
