package swt2.vico.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document(collection = "Raum")
public class Raum implements Serializable {

  @Id
  private String id;
  private String raumNr;
  private String name;
  private int etage;
  private int besucherAnzahl;
  private int maxAnzahl;

  public String getRaumNr() {
    return raumNr;
  }

  public void setRaumNr(String raumNr) {
    this.raumNr = raumNr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getEtage() {
    return etage;
  }

  public void setEtage(int etage) {
    this.etage = etage;
  }

  public int getBesucherAnzahl() {
    return besucherAnzahl;
  }

  public void setBesucherAnzahl(int besucherAnzahl) {
    this.besucherAnzahl = besucherAnzahl;
  }

  public int getMaxAnzahl() {
    return maxAnzahl;
  }

  public void setMaxAnzahl(int maxAnzahl) {
    this.maxAnzahl = maxAnzahl;
  }

  @Override
  public String toString() { return "Raum{raumNr='" + raumNr + '\'' + ", etage=" + etage + ", besucherAnzahl=" + besucherAnzahl + ", maxAnzahl=" + maxAnzahl + '}'; }
}
