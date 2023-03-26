package swt2.vico.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swt2.vico.entities.Raum;
import swt2.vico.service.ISensorService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoDB {

  /**
   * Initialisiert die Datenbank mit allen Räumen die nicht in der Datenbank vorhanden sind.
   *
   * @param sensorService
   * @return
   */
  @Bean
  CommandLineRunner initDatabase(ISensorService sensorService) {
    List<Raum> raeumeDBList = sensorService.findAll();
    List<Raum> raeume = getMissingRaeume(raeumeDBList);

    return args -> {
      sensorService.saveAll(raeume);
      sensorService.createEtagenManager();
    };
  }

  /**
   * Liest die einzelnen Zeilen aus der CSV-Datei und teilt diese nach dem regex in einzelne Elemente auf.
   * Diese wiederum werden in ein Array gespeichert. Das Array wird dann als List in die geschachtelte List
   * gespeichert und ausgegeben.
   *
   * @param filename String
   * @param regex String
   * @return List<List<String>>
   */
  private List<List<String>> readCSV(String filename, String regex){
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(regex);
        records.add(Arrays.asList(values));
      }
      return records;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Liest die CSV-Datei ein und vergleicht die Liste der Objekte mit der Liste die als Parameter
   * übergeben wurde. Es wird überprüft welche Raum-Objekte vorhanden sind und welche nicht. Die die nicht
   * vorhanden sind werden in eine Liste abgespeichert und ausgegeben
   *
   * @param raeumeDBList List<Raum>
   * @return List<Raum>
   */
  private List<Raum> getMissingRaeume(List<Raum> raeumeDBList) {
    List<Raum> raeume = new ArrayList<>();
    for (List<String> list : readCSV("Raumliste.csv", ";")) {
      boolean bFoundEntry = false;

      Raum raum = buildRaum(list.get(0), list.get(1), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)), Integer.parseInt(list.get(4)));

      for (Raum r : raeumeDBList)
        if (raum.getRaumNr().equals(r.getRaumNr())) {
          bFoundEntry = true;
          break;
        }

      if (!bFoundEntry)
        raeume.add(raum);
    }
    return raeume;
  }

  public Raum buildRaum(String raumNr, String name, int etage, int besucherAnzahl, int maxAnzahl){
    Raum raum = new Raum();

    raum.setRaumNr(raumNr);
    raum.setName(name);
    raum.setEtage(etage);
    raum.setBesucherAnzahl(besucherAnzahl);
    raum.setMaxAnzahl(maxAnzahl);

    return raum;
  }
}
