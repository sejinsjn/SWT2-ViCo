package swt2.vico.mqttserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swt2.vico.entities.Raum;
import swt2.vico.entities.SensorDaten;
import swt2.vico.service.ISensorService;

@Component
public class  MessageHandler implements MqttCallback{

  @Autowired
  private ISensorService sensorService;
  private String json;
  MqttClient client;

  /**
   * Versucht eine Verbindung zum MQTT-Server zu erstellen. Falls Verbindung erfolgreich
   * wird zu einem vorgegebenen Topic subsribed
   */
  public MessageHandler() {
    String topicSWT = "zaehler/#";
    String URI1 = "tcp://ttv.no-ip.org:9708";
    String URI2 = "tcp://ttv.no-ip.org:1883";
    String Local = "tcp://127.0.0.1:1883";
    connectToMQTT(URI1, topicSWT);
    if(!client.isConnected())
      connectToMQTT(URI2, topicSWT);
    else if(!client.isConnected())
      connectToMQTT(Local, topicSWT);
    else if(!client.isConnected())
      System.out.println("No MQTT-Server available!");
  }

  public void connectionLost(Throwable cause) {
      System.out.println("Connection to MQTT broker lost!");
      System.err.println(cause);
  }

  /**
   * Bei jeder angekommenen Message wird überprüft ob Daten valide sind und falls
   * es so ist wird der Raum aus Message mit der neuen BesucherAnzahl geupdated.
   *
   * @param topic name of the topic on the message was published to
   * @param mqttMessage the actual message.
   * @throws Exception
   */
  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    String[] EtageRaum = topic.split("/");
    Raum raum = sensorService.findByRaumNr(EtageRaum[1]);

    this.json = new String(mqttMessage.getPayload());
    String besucherAnzahl = this.json.substring( this.json.indexOf(":")+1).replace("}", "").replace(" ", "");

    updateRaum(besucherAnzahl, raum);
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
  }

  /**
   * Erstellt einen neuen Client und versucht eine Verbindung aufzubauen. Falls erfolgreich
   * wird der übergebene Topic subscribed.
   *
   * @param URI MQTT-Server URI
   * @param topic Topic
   */
  private void connectToMQTT(String URI, String topic){
    try{
      client = new MqttClient(URI, MqttClient.generateClientId());
      client.setCallback( this );
      client.connect();
      client.subscribe(topic);
      System.out.println("Connected to " + URI);
    } catch (MqttException ex) {
      System.err.println(ex);
    }
  }

  /**
   * Überprüfung ob übergebener String valide ist. Falls es so ist wird die JSON mit dem
   * SensorDaten-Objekt gemapped und in der Datenbank geupdated.
   *
   * @param besucherAnzahl
   * @param raum
   * @throws Exception
   */
  private void updateRaum(String besucherAnzahl, Raum raum) throws Exception {
    SensorDaten daten;
    if( !besucherAnzahl.matches("[0-9]+")) {
      System.out.println( "Falsches Format der besucherAnzahl: " + besucherAnzahl);
      return;
    }else{
      daten = new ObjectMapper().readValue(this.json, SensorDaten.class);
      if( raum == null || daten.getBesucherAnzahl() > raum.getMaxAnzahl()){
        System.out.println( "Maximale Besucheranzahl wurde erreicht oder Raum existiert nicht: " + besucherAnzahl);
        return;
      }
    }

    if( daten != null) {
      raum.setBesucherAnzahl(daten.getBesucherAnzahl());
      sensorService.addBesucher(raum);
      System.out.println(raum.toString());
      sensorService.updateCache(raum.getEtage());
    }
  }
}
