package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "barang")
public class BarangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 20)
    private Long id;

    @NotNull
    @Column(name="nama_barang", nullable = false)
    private String namaBarang;

    @NotNull
    @Min(0)
    @Column(name="stok", nullable = false)
    private Integer stok;

    @NotNull
    @Min(0)
    @Column(name="jumlah_garansi", nullable = false)
    private Integer jumlahGaransi;

    @NotNull
    @Column(name="deskripsi_barang", nullable = false)
    private String deskripsiBarang;

    @NotNull
    @Column(name="kode_barang", nullable = false, unique = true, length = 16)
    private String kodeBarang;

    @NotNull
    @Column(name="merk_barang", nullable = false)
    private String merkBarang;

    @NotNull
    @Min(1)
    @Column(name="harga_barang", nullable = false)
    private Integer hargaBarang;

    @OneToOne
    @JoinColumn(name = "id_tipe", referencedColumnName = "id_tipe", nullable = false)
    private TipeModel tipe;

    @OneToMany(mappedBy = "barang")
    List<PembelianBarangModel> listPembelianBarang;




}
