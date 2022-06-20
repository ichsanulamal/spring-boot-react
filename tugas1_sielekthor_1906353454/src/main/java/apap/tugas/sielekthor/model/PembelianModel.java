package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pembelian")
public class PembelianModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 20)
    private Long id;

    @NotNull
    @Min(1)
    @Column(name = "total", nullable = false)
    private Integer total;

    @NotNull
    @Column(name="tanggal_pembelian", nullable = false)
    private LocalDateTime tanggalPembelian;

    @NotNull
    @Column(name="nama_admin")
    private String namaAdmin;

    @NotNull
    @Column(name="no_invoice", nullable = false, unique = true)
    private String noInvoice;

    @NotNull
    @Column(name="is_cash", nullable = false)
    private Boolean isCash;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private MemberModel member;

    @OneToMany(mappedBy = "pembelian", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    List<PembelianBarangModel> listPembelianBarang;
}
