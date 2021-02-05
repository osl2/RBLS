package steuerung;

import modell.SteuerungFassade;
import modell.formel.Formel;

public class FuelleTabelle extends WahrheitstabellenBefehl {

  private int zeilenAnz;
  private int spaltenAnz;
  private int anzAtomSpalten;

  public FuelleTabelle(SteuerungFassade model) {
    super(model);
  }

  @Override
  public void hohleDaten() {
    spaltenAnz = model.gibSpaltenAnz();
    zeilenAnz = model.gibZeilenAnz();
    anzAtomSpalten = model.gibAtomareAussage().size();
  }

  @Override
  public void setzeDaten() {
    Formel akFormel;
    boolean[] akFall;
    int[] koordinaten = new int[2];
    for (int spalte = anzAtomSpalten; spalte < spaltenAnz; spalte++) {
      akFormel = model.gibFormel(spalte);
      koordinaten[0] = spalte;
      for (int zeile = 1; zeile < zeilenAnz; zeile++) {
        akFall = model.gibZeileFall(zeile);
        koordinaten[1] = zeile;
        model.setzeZelleWW(koordinaten, akFormel.auswerten(akFall));
      }
    }
  }
}
