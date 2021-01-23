package steuerung;

public class Hauptsteuerung {

  modell.SteuerungFassade sf;
  modell.PraesentationFassade pf;
  praesentation.Fensterverwaltung fv;
  WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte f�r die andere Teile
   * und st��t Initialisierung an
   */

  public void init() {
    sf = new modell.SteuerungFassade(this);
    pf = new modell.PraesentationFassade();
    fv = new praesentation.Fensterverwaltung(this, pf);
    sf.init();
    fv.init();
  }

  /**
   * erstellt Objekte die f�r das R�tselfenster n�tig sind.
   * 
   * @param raetselname der Name des R�tsel das gestartet werden soll.
   */
  public void raetselFensterInit(String raetselname) {
    sf.setzeRaetsel(raetselname);
    ws = new WahrheitstabellenSteuerungen(sf);
    ws.befehl("AufbauTabelle");
  }

  /**
   * beendet das R�tsel und setst das R�tsel auf gel�st.
   */
  public void raetselGeloest() {
    // ToDo
  }

  /**
   * st��t die Erstellung der Sicherungsdatei und beendet das Programm.
   */
  public void beenden() {
    // ToDo
  }
}