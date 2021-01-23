package steuerung;

import modell.SteuerungFassade;

public abstract class WahrheitstabellenBefehl {
  SteuerungFassade model;

  /**
   * Konstruktor f�r WahrheitstabellenBefehl.
   * 
   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public WahrheitstabellenBefehl(SteuerungFassade model) {
    this.model = model;
  }

  /**
   * hohlt alle Daten die f�r den Befehl ben�tigt werden von der Fassade.
   */
  public void hohleDaten() {

  }

  /**
   * Stezt in der Fassade alle Daten die der Befehl ver�ndert hat.
   */
  public void setzeDaten() {

  }
}
