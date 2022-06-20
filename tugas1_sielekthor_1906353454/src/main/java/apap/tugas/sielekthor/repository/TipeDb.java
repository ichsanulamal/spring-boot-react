package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {
    Optional<TipeModel> findById(Long id);
    List<TipeModel> findAll();
}
