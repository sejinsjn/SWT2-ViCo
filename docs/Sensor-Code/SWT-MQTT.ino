#include <ESP8266WiFi.h>
#include <PubSubClient.h>

const char* ssid = "Jonas Mi T9 Pro";
const char* password = "a1b2c3d4";
const char* mqttServer = "ttv.no-ip.org";
const int mqttPort = 9708;
// const char* mqttUser = "YourMqttUser";
// const char* mqttPassword = "YourMqttUserPassword";

const int buttonUpPin = 4;
const int buttonDownPin = 5;
const int ledPin = 2;

int people = 0;

int upState = LOW;
int downState = LOW;

int buttonUpState;
int buttonDownState;
int lastButtonUpState = LOW;
int lastButtonDownState = LOW;

// the following variables are unsigned longs because the time, measured in
// milliseconds, will quickly become a bigger number than can be stored in an
// int.
unsigned long lastDebounceTime = 0;  // the last time the output pin was toggled
unsigned long debounceDelay =
    50;  // the debounce time; increase if the output flickers

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  pinMode(buttonUpPin, INPUT_PULLUP);
  pinMode(buttonDownPin, INPUT_PULLUP);
  pinMode(ledPin, OUTPUT);

  digitalWrite(ledPin, HIGH);

  Serial.begin(115200);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");

  client.setServer(mqttServer, mqttPort);
  client.setCallback(callback);

  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");
    if (client.connect("ESP8266Client")) {
      Serial.println("connected");
      digitalWrite(ledPin, LOW);
    } else {
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(2000);
    }
  }

  // client.publish("esp/test", "Hello from ESP8266");
  // client.subscribe("esp/test");

  // client.publish("zaehler/A.E.01", "{{\" besucherAnzahl: 10 \" + }}");
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived in topic: ");
  Serial.println(topic);

  Serial.print("Message:");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
  Serial.println("-----------------------");
}

void loop() {
  client.loop();
  int readingUp = digitalRead(buttonUpPin);
  int readingDown = digitalRead(buttonDownPin);

  if (readingUp != lastButtonUpState) {
    lastDebounceTime = millis();
  }
  if (readingDown != lastButtonDownState) {
    lastDebounceTime = millis();
  }

  if ((millis() - lastDebounceTime) > debounceDelay) {
    if (readingUp != buttonUpState) {
      buttonUpState = readingUp;
      if (buttonUpState == HIGH) {
        upState = !upState;
      }
    }
    if (readingDown != buttonDownState) {
      buttonDownState = readingDown;
      if (buttonDownState == HIGH) {
        downState = !downState;
      }
    }
  }

  if (upState || downState) {
    if (upState) {
      people++;
      upState = LOW;
    } else {
      people--;
      downState = LOW;
    }
    Serial.print("besucherAnzahl: ");
    Serial.println(people);
    int msgLen = 0;
    msgLen += String("{\"besucherAnzahl\" : ").length();
    msgLen += String(people).length();
    msgLen += String("}").length();

    client.beginPublish("zaehler/A.E.01", msgLen, false);
    client.print("{\"besucherAnzahl\" : ");
    client.print(people);
    client.print("}");

    client.endPublish();
  }

  lastButtonUpState = readingUp;
  lastButtonDownState = readingDown;
}
