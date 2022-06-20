package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PegawaiRestService {
    PegawaiModel createPegawai(PegawaiModel pegawai);
    List<PegawaiModel> retrieveListPegawai();
    PegawaiModel getPegawaiByNoPegawai(Long noPegawai);
    PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate);
    void deletePegawai(Long noPegawai);
    PegawaiModel predictUmur(Long noPegawai);

}
