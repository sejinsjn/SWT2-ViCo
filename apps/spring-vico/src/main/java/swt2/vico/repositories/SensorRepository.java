package swt2.vico.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import swt2.vico.entities.Raum;

import java.util.List;

@Repository
public interface SensorRepository extends MongoRepository<Raum, Integer> {

  List<Raum> findByEtage(int etage);

  Raum findByRaumNr(String raum);

  List<Raum> findAllByRaumNr(String raumNr);

  List<Raum> findByBesucherAnzahlGreaterThan(int anzahl);
}
