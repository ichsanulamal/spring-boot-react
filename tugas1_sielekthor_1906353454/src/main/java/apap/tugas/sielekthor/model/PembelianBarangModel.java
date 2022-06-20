package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pembelian_barang")
public class PembelianBarangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 20)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_barang")
    private BarangModel barang;

    @ManyToOne
    @JoinColumn(name = "id_pembelian")
    private PembelianModel pembelian;

    @Column(name="tanggal_garansi")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tanggalGaransi;

    @Column(name="quantity")
    @Min(1)
    private Integer quantity;
}

