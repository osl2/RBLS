package steuerung;

import praesentation.Einstellungen;

public class Hauptsteuerung {

  private modell.Fassade sf;
  private praesentation.Fensterverwaltung fv;
  private WahrheitstabellenSteuerungen ws;
  private praesentation.Einstellungen einstellungen;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte fuer Modell und
   * Praesentation und stoesst deren Initialisierung an.
   */

  public void init() {
    sf = new modell.Fassade();
    sf.init();
    Einstellungen einstellungen = new Einstellungen(sf.getMemento().gibFarbeZurueck());
    this.einstellungen = einstellungen;
    sf.setEinstellungen(this.einstellungen);
    this.fv = new praesentation.Fensterverwaltung(this, sf, einstellungen);
    this.fv.init();
    
  }

  /**
   * erstellt die WahrheitstabellenSteuerungen die fuer das Raetselfenster.
   * 
   */
  public WahrheitstabellenSteuerungen raetselFensterInit() {
    ws = new WahrheitstabellenSteuerungen(sf, fv);
    ws.befehl("AufbauTabelle");
    return ws;
  }

  /**
   * beendet das Raetsel und setzt das Raetsel auf geloest.
   */
  public void raetselGeloest() {
    sf.fuehreSicherungAus();
  }

  /**
   * stoesst die Erstellung der Sicherungsdatei an und beendet das Programm.
   */
  public void beenden() {
    System.exit(0);
  }
}