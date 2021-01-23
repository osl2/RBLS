package steuerung;

public class Hauptsteuerung {
  public Hauptsteuerung() {
   
  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte f�r die andere Teile
   * und st��t Initialisierung an
   */

  public void init() {
    modell.SteuerungFassade sf = new modell.SteuerungFassade(this);
    modell.PraesentationFassade pf = new modell.PraesentationFassade(); 
    praesentation.Fensterverwaltung fv = new praesentation.Fensterverwaltung(this, pf);
    sf.init();
    fv.init();
  }
}