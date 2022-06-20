package apap.tugas.sielekthor.sevice;
import apap.tugas.sielekthor.model.TipeModel;

import java.util.List;

public interface TipeService {
    void addTipe(TipeModel tipe);
    void removeTipe(TipeModel tipe);
    List<TipeModel> getTipeList();

}
