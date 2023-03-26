package swt2.vico.proxy;

import swt2.vico.entities.Raum;

import java.util.LinkedList;
import java.util.List;

public class Etage implements GetEtageData{

  private List<Raum> etage = new LinkedList<>();

  public Etage(List<Raum> etagenListe){
    update(etagenListe);
  }

  @Override
  public void update(List<Raum> etagenListe){
    etage = etagenListe;
    System.out.println("Cache of " + etagenListe.get(0).getEtage() + " updated!");
  }

  @Override
  public List<Raum> getRaeume(){
    return etage;
  }
}
