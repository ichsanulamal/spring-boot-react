package apap.tugas.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tipe")
public class TipeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipe", length = 20)
    private Long id;

    @NotNull
    @Column(name="nama_tipe", nullable = false)
    private String namaTipe;

    @NotNull
    @Column(name="deskripsi_tipe", nullable = false)
    private String deskripsiTipe;
}
