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
    praesentation.Fensterverwaltung fv = new praesentation.Fensterverwaltung(this);
    modell.Fassade fs = new modell.Fassade(this);
    fs.init();
    fv.init();
  }
}
