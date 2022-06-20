package apap.tutorial.kebunsafari.service;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class KebunSafariInMemoryService implements KebunSafariService {
    private List<KebunSafariModel> listKebunSafari;

    // Constructor
    public KebunSafariInMemoryService(){
        listKebunSafari = new ArrayList<>();
    }

    @Override
    public void addKebunSafari(KebunSafariModel kebunSafari) {
        listKebunSafari.add(kebunSafari);
    }

    @Override
    public List<KebunSafariModel> getKebunSafariList() {
        return listKebunSafari;
    }

    @Override
    public KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari) {
        for (KebunSafariModel kebunSafari : listKebunSafari) {
            if (kebunSafari.getIdKebunSafari().equals(idKebunSafari)) {
                return kebunSafari;
            }
        }
        return null;
    }

    @Override
    public KebunSafariModel updateNoTeleponKebunSafariByIdKebunSafari(String idKebunSafari, String noTelepon) {
        for (KebunSafariModel kebunSafari : listKebunSafari) {
            if (kebunSafari.getIdKebunSafari().equals(idKebunSafari)) {
                kebunSafari.setNoTelepon(noTelepon);
                return kebunSafari;
            }
        }
        return null;
    }

    @Override
    public boolean deleteKebunSafari(String idKebunSafari) {
        for (KebunSafariModel kebunSafari : listKebunSafari) {
            if (kebunSafari.getIdKebunSafari().equals(idKebunSafari)) {
                listKebunSafari.remove(kebunSafari);
                return true;
            }
        }
        return false;
    }
}
