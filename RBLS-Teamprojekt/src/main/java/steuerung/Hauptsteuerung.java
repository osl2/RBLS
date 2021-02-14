package steuerung;

public class Hauptsteuerung {

  private modell.Fassade sf;
  private praesentation.Fensterverwaltung fv;
  private WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte f�r Modell und
   * Pr�sentation und st��t deren Initialisierung an.
   */

  public void init() {
    sf = new modell.Fassade();
    sf.init();
    fv = new praesentation.Fensterverwaltung(this, sf);
    fv.init();
  }

  /**
   * erstellt die WahrheitstabellenSteuerungen die f�r das R�tselfenster.
   * 
   */
  public WahrheitstabellenSteuerungen raetselFensterInit() {
    ws = new WahrheitstabellenSteuerungen(sf);
    ws.befehl("AufbauTabelle");
    return ws;
  }

  /**
   * beendet das R�tsel und setzt das R�tsel auf gel�st.
   */
  public void raetselGeloest() {
    sf.fuehreSicherungAus();
  }

  /**
   * st��t die Erstellung der Sicherungsdatei an und beendet das Programm.
   */
  public void beenden() {
    System.exit(0);
  }
}