package swt2.vico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import swt2.vico.entities.Raum;
import swt2.vico.service.ISensorService;

import java.util.List;

@RestController
public class RaumController {

  @Autowired
  ISensorService sensorService;

  @GetMapping("/raum")
  List<Raum> all() { return sensorService.findAll(); }

  @GetMapping("/etage/{etageNr}")
  List<Raum> findByEtage(@PathVariable int etageNr) {
    return sensorService.getRaeumeFromEtage(etageNr);
  }
}
