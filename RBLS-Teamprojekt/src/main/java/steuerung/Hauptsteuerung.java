package steuerung;

public class Hauptsteuerung {
  public Hauptsteuerung() {
    // TODO Auto-generated constructor stub
  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte f�r die andere Teile
   * und st��t Initialisierung an
   */

  public void init() {
    Fensterverwaltung fv = new Fensterverwaltung(this);
    Fassade fs = new Fassade(this);
    fs.init();
    fv.init();
  }
}
