package apap.tugas.sielekthor.repository;

import apap.tugas.sielekthor.model.BarangModel;
import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.model.PembelianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PembelianDb extends JpaRepository<PembelianModel, Long> {
    Optional<PembelianModel> findById(Long id);
    List<PembelianModel> findAll();
    List<PembelianModel> findByMember_Id(Long memberId);

    @Query(value = "SELECT m.nama_member, m.tanggal_lahir, m.tanggal_pendaftaran, m.jenis_kelamin, count(p.id_member) " +
            "FROM member m JOIN pembelian p ON p.id_member=m.id " +
            "GROUP BY p.id_member " +
            "ORDER BY count(p.id_member) DESC", nativeQuery = true)
    List CountPembelianByMember();
}
