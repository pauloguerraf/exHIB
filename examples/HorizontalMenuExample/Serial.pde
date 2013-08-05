void startSerial() {
  if (usingSerial) {
    println(Serial.list());
    sPort = new Serial(this, Serial.list()[6], 115200);
  }
}

void serialEvent(Serial myPort) { 
  if (myPort.available() > 0) {
    String inString = myPort.readStringUntil('\n');
    if (inString != null) {
      inString = trim(inString);
      int val = int(inString);
      currentPosX = (int)map(val, 0, 1024, 0, 1024);
    }
  }
}

