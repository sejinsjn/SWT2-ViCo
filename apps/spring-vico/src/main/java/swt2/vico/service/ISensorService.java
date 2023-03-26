package swt2.vico.service;

import swt2.vico.entities.Raum;

import java.util.List;

public interface ISensorService {

  List<Raum> saveAll(List<Raum> raumList);

  Raum addBesucher(Raum raum);

  List<Raum> findAll();

  List<Raum> findByEtage(int etage);

  Raum findByRaumNr(String raum);

  List <Raum> getRaeumeFromEtage(int etageNr);

  void updateCache(int etageNr);

  public void createEtagenManager();
}
