package steuerung;

import modell.SteuerungFassade;

public class WahrheitstabellenSteuerungen {
  /**
   * Konstruktor f�r die WahrheitstabellenSteuerungen.
   * 
   * @param modell die Fassade die in alle Befehelen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(SteuerungFassade modell) {
    // TODO Auto-generated constructor stub
  }

  /**
   * l�st Befehl von der Pr�sentation auf Konstruiert passenden Befehl und st��t
   * damit die Ausf�hrung an.
   * 
   * @param befehl der auszuf�hrende Befehl
   */
  public void befehl(String befehl) {
    ueberpruefeTabelle();
  }

  /**
   * �berpr�ft ob der Inhalt der Tabelle korrekt ist.
   * 
   * @return Korrektheit
   */
  private boolean ueberpruefeTabelle() {
    return false;
  }
}