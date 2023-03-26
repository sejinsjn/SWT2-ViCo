package swt2.vico.proxy;

import swt2.vico.entities.Raum;

import java.util.LinkedList;
import java.util.List;

public class EtageProxy implements GetEtageData{

  private List<Raum> etageCache = new LinkedList<>();
  GetEtageData etage = null;

  public EtageProxy(List<Raum> etagenListe){
    etage = new Etage(etagenListe);
    etageCache = etage.getRaeume();
  }

  @Override
  public void update(List<Raum> etagenListe) {
      etage.update(etagenListe);
      etageCache = etage.getRaeume();
  }

  @Override
  public List<Raum> getRaeume() {
    System.out.println("Cache " + etageCache.get(0).getEtage() + " used!");
    return etageCache;
  }
}
