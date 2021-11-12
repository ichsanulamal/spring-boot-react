package apap.tutorial.emsidi.restcontroller;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.PegawaiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PegawaiRestController {
    @Autowired
    private PegawaiRestService pegawaiRestService;

    @PostMapping(value = "/pegawai")
    private PegawaiModel createPegawai(@Valid @RequestBody PegawaiModel pegawai, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return pegawaiRestService.createPegawai(pegawai);
        }
    }

    @GetMapping(value = "/pegawai/{noPegawai}")
    private PegawaiModel retrievePegawai(@PathVariable("noPegawai") Long noPegawai) {
        try {
            return pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with No. " + String.valueOf(noPegawai) + " is Not Found."
            );
        }
    }

    @DeleteMapping(value = "/pegawai/{noPegawai}")
    private ResponseEntity deletePegawai(@PathVariable("noPegawai") Long noPegawai) {
        try {
            pegawaiRestService.deletePegawai(noPegawai);
            return ResponseEntity.ok("Pegawai with ID " + String.valueOf(noPegawai) + " Deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai) + " is Not Found!"
            );
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cabang is still open or has pegawai!"
            );
        }
    }

    @PutMapping(value = "/pegawai/{noPegawai}")
    private PegawaiModel updatePegawai(@PathVariable("noPegawai") Long noPegawai, @RequestBody PegawaiModel pegawai) {
        try {
            return pegawaiRestService.updatePegawai(noPegawai, pegawai);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with No. " + String.valueOf(noPegawai) + " is Not Found!"
            );
        }
    }

    @GetMapping(value = "/list-pegawai")
    private List<PegawaiModel> retriveListPegawai() {
        return pegawaiRestService.retrieveListPegawai();
    }

    @GetMapping(value = "/pegawai/umur/{noPegawai}")
    private PegawaiModel predictUmur(@PathVariable("noPegawai") Long noPegawai) {
        try {
            return pegawaiRestService.predictUmur(noPegawai);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with No. " + String.valueOf(noPegawai) + " is Not Found!"
            );
        }
    }

    @DeleteMapping(value = "/pegawai/delete/all")
    private ResponseEntity deleteAllPegawai() {
        List<PegawaiModel> listPegawai = pegawaiRestService.retrieveListPegawai();
        for (PegawaiModel pm:listPegawai) {
            System.out.println(pm.getNoPegawai());
            pegawaiRestService.deletePegawai(pm.getNoPegawai());
        }
        return ResponseEntity.ok("All pegawai deleted");
    }

//        PegawaiModel thisPegawai = pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
//        try {
//            URL url = new URL("https://api.agify.io/?name=" + thisPegawai.getNamaPegawai());
//            InputStreamReader reader = new InputStreamReader(url.openStream());
//            Map<String, Object> map = new Gson().fromJson(reader, Map.class);
//            thisPegawai.setUmur(((Double)(map.get("age"))).intValue());
//
//
//            return thisPegawai;
//        } catch (Exception ex) {
//            System.out.println(ex);
//            return thisPegawai;
//        }
//    }

}

