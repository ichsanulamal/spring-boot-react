package apap.tugas.sielekthor.sevice;

import apap.tugas.sielekthor.model.PembelianBarangModel;
import apap.tugas.sielekthor.model.PembelianModel;
import apap.tugas.sielekthor.repository.BarangDb;
import apap.tugas.sielekthor.repository.PembelianDb;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService {
    @Autowired
    PembelianDb pembelianDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    PembelianBarangService pembelianBarangService;

    @Override
    public boolean addPembelian(PembelianModel pembelian) {
        boolean isCompleted = true;
        int total = 0;
        for (PembelianBarangModel pb: pembelian.getListPembelianBarang()) {
            if (pb.getBarang().getStok() - pb.getQuantity() >= 0) {
                pb.getBarang().setStok(pb.getBarang().getStok() - pb.getQuantity());
                barangDb.save(pb.getBarang());
                total += pb.getQuantity() * pb.getBarang().getHargaBarang();

                pb.setPembelian(pembelian);
                pb.setTanggalGaransi(LocalDate.now().plusDays(pb.getBarang().getJumlahGaransi()));
                pembelianBarangService.addPembelianBarang(pb);
            } else {
                isCompleted = false;
            }
        }
        pembelian.setTotal(total);
        pembelian.setTanggalPembelian(LocalDateTime.now());
        pembelian.setNoInvoice(getRandomInvoice(pembelian));
        pembelianDb.save(pembelian);
        return isCompleted;
    }

    private String getRandomInvoice(PembelianModel pembelian) {
        String invoice = "";
        // 1 karakter pertama member
        invoice += ((int) Character.toLowerCase(pembelian.getMember().getNamaMember().charAt(0)) - 96) % 10;
        // 1 karakter terakhir admin
        invoice += Character.toUpperCase(pembelian.getNamaAdmin().charAt(pembelian.getNamaAdmin().length()-1));
        // 4 karakter tanggal pembelian
        invoice += pembelian.getTanggalPembelian().getDayOfMonth() + "" + pembelian.getTanggalPembelian().getMonthValue();
        // 2
        invoice += pembelian.getIsCash() ? "02":"01";
        // 3 karakter
        invoice += (pembelian.getTanggalPembelian().getDayOfMonth() + pembelian.getTanggalPembelian().getMonthValue())*5;
        // 2 karakter
        invoice += RandomStringUtils.randomAlphabetic(2).toUpperCase();
        return invoice;
    }

    @Override
    public void updatePembelian(PembelianModel pembelian) {
        pembelianDb.save(pembelian);
    }

    @Override
    public void removePembelian(PembelianModel pembelian) {
        for (PembelianBarangModel pb: pembelian.getListPembelianBarang()) {
            pb.getBarang().setStok(pb.getBarang().getStok() + pb.getQuantity());
            barangDb.save(pb.getBarang());
        }
        pembelianDb.delete(pembelian);
    }

    @Override
    public List<PembelianModel> getPembelianList() {
        return pembelianDb.findAll();
    }

    @Override
    public PembelianModel getPembelianById(Long id) {
        Optional<PembelianModel> pembelian = pembelianDb.findById(id);
        return pembelian.orElse(null);
    }

    @Override
    public List<PembelianModel> getPembelianListByMember(Long idMember) {
        return pembelianDb.findByMember_Id(idMember);
    }

    @Override
    public List getCountPembelianByMember() {
        return pembelianDb.CountPembelianByMember();
    }

}
