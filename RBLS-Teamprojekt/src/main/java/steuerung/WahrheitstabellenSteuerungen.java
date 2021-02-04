package steuerung;

import modell.SteuerungFassade;

public class WahrheitstabellenSteuerungen {
  /**
   * Konstruktor f�r die WahrheitstabellenSteuerungen.
   * 
   * @param modell die Fassade die von Befehlen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(SteuerungFassade modell) {
    // TODO Auto-generated constructor stub
  }

  /**
   * l�st Befehl von der Pr�sentation auf und konstruiert das passenden
   * Befehls-Objekt und st��t damit dessen Ausf�hrung an.
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