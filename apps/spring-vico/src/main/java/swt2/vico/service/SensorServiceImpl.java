package swt2.vico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swt2.vico.entities.Raum;
import swt2.vico.proxy.EtagenManager;
import swt2.vico.repositories.SensorRepository;

import java.util.List;

@Service
public class SensorServiceImpl implements ISensorService {

  @Autowired
  private SensorRepository sensorRepository;

  private EtagenManager etagenManager = new EtagenManager();

  @Override
  public List<Raum> saveAll(List<Raum> raumList){
    return sensorRepository.saveAll(raumList);
  }
  @Override
  public Raum addBesucher(Raum raum) {
    return sensorRepository.save(raum);
  }

  @Override
  public List<Raum> findAll() {
    return sensorRepository.findAll();
  }

  @Override
  public List<Raum> findByEtage(int etage) {
      return sensorRepository.findByEtage(etage);
  }

  @Override
  public Raum findByRaumNr(String raum) { return sensorRepository.findByRaumNr(raum); }

  @Override
  public List<Raum> getRaeumeFromEtage(int etageNr) {
      return etagenManager.getRaumListeFromEtage(etageNr);
  }

  @Override
  public void updateCache(int etageNr) {
      etagenManager.updateRaeumeFromEtage(etageNr);
  }

  @Override
  public void createEtagenManager(){
    etagenManager.createEtagenManager(sensorRepository);
  }
}
