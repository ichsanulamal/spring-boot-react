package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarangDb extends JpaRepository<BarangModel, Long> {
    Optional<BarangModel> findById(Long id);
    List<BarangModel> findAll();

    @Query(value = "SELECT * FROM barang WHERE id_tipe = :tipe AND stok > 0", nativeQuery = true)
    List<BarangModel> findByTipeIdAndAndStokAvailable(@Param("tipe") Long tipe);

    @Query(value = "SELECT * FROM barang WHERE id_tipe = :tipe AND stok <= 0", nativeQuery = true)
    List<BarangModel> findByTipeIdAndAndStokIsNotAvailable(@Param("tipe") Long tipe);
}
