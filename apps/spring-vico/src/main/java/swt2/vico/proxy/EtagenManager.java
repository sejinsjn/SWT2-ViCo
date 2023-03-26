package swt2.vico.proxy;

import org.springframework.stereotype.Component;
import swt2.vico.entities.Raum;
import swt2.vico.repositories.SensorRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class EtagenManager {

  private SensorRepository sensorRepository;
  private List<GetEtageData> etagenListe = new ArrayList<>();

  public void createEtagenManager(SensorRepository sensorRepository){
    this.sensorRepository = sensorRepository;
    GetEtageData etage0 = new EtageProxy(sensorRepository.findByEtage(0));
    GetEtageData etage1 = new EtageProxy(sensorRepository.findByEtage(1));
    GetEtageData etage2 = new EtageProxy(sensorRepository.findByEtage(2));
    GetEtageData etage3 = new EtageProxy(sensorRepository.findByEtage(3));
    etagenListe.add(etage0);
    etagenListe.add(etage1);
    etagenListe.add(etage2);
    etagenListe.add(etage3);
  }
  public List<Raum> getRaumListeFromEtage(int etageNr) {
    return etagenListe.get(etageNr).getRaeume();
  }

  public void updateRaeumeFromEtage(int etageNr){
    etagenListe.get(etageNr).update(sensorRepository.findByEtage(etageNr));
  }
}
