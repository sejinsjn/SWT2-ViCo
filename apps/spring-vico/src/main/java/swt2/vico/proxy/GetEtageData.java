package swt2.vico.proxy;

import swt2.vico.entities.Raum;

import java.util.List;

public interface GetEtageData {

  void update(List<Raum> etagenListe);
  List<Raum> getRaeume();
}
