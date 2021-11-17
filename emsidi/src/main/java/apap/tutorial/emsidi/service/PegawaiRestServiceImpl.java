package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.PegawaiDb;
import apap.tutorial.emsidi.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PegawaiRestServiceImpl implements PegawaiRestService {
    private final WebClient webClient;

    public PegawaiRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.predictUmurUrl).build();
    }

    @Autowired
    private PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel createPegawai(PegawaiModel pegawai) {
        return pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> retrieveListPegawai() {
        return pegawaiDb.findAll();
    }

    @Override
    public PegawaiModel getPegawaiByNoPegawai(Long noPegawai) {
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);

        if (pegawai.isPresent()) {
            return pegawai.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate) {
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        pegawai.setNamaPegawai(pegawaiUpdate.getNamaPegawai());
        pegawai.setJenisKelamin(pegawaiUpdate.getJenisKelamin());

        return pegawaiDb.save(pegawai);
    }

    @Override
    public void deletePegawai(Long noPegawai) {
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        pegawaiDb.delete(pegawai);
    }

    @Override
    public PegawaiModel predictUmur(Long noPegawai) {
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);

        if (pegawai.isPresent()) {
            PegawaiModel thisPegawai = pegawai.get();
            Flux<Map> userAgeMap = this.webClient.get()
                    .uri("/?name=" + thisPegawai.getNamaPegawai())
                    .retrieve()
                    .bodyToFlux(Map.class);
            List<Map> output = userAgeMap.collectList().block();

            int age = (int)(output.get(0).get("age"));
            thisPegawai.setUmur(age);
            pegawaiDb.save(thisPegawai);

            return thisPegawai;
        } else {
            throw new NoSuchElementException();
        }
    }

    // sumur :
    // https://fullstackdeveloper.guru/2020/06/15/how-to-use-spring-webclient-to-invoke-rest-services-reactively-and-non-reactively/

}
