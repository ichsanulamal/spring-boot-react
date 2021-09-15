package apap.tutorial.kebunsafari.service;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import java.util.List;

public interface KebunSafariService {

    void addKebunSafari(KebunSafariModel kebunSafari);

    List<KebunSafariModel> getKebunSafariList();

    KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari);
}


