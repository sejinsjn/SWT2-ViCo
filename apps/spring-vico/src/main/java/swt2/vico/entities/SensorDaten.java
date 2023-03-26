package swt2.vico.entities;

public class SensorDaten {

  private int besucherAnzahl;

  public int getBesucherAnzahl() {
    return besucherAnzahl;
  }

  public void setBesucherAnzahl(int besucherAnzahl) {
    this.besucherAnzahl = besucherAnzahl;
  }

  @Override
  public String toString() { return "SensorDaten{" + "besucherAnzahl=" + besucherAnzahl + '}'; }
}
