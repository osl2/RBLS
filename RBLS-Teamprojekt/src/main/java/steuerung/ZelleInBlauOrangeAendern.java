package steuerung;

import modell.SteuerungFassade;

public class ZelleInBlauOrangeAendern extends WahrheitstabellenBefehl {
  private int[] zellenKoordinaten;
  private boolean zelleWW;

  /**
   * �ndert den Wahrheitswert in einer Wahrheitswert Zelle.
   * 
   * @param model  die SteuerungFassade auf der der Befehl ausgef�hrt wird.
   * @param spalte die Spalte in der der Wert ge�ndert wird
   * @param zeile  die Zeile in der der Wert ge�ndert wird
   */
  public ZelleInBlauOrangeAendern(SteuerungFassade model, int spalte, int zeile) {
    super(model);
    zellenKoordinaten[0] = zeile;
    zellenKoordinaten[1] = spalte;
  }

  @Override
  public void hohleDaten() {
    zelleWW = model.gibZelleWW(zellenKoordinaten);
  }

  @Override
  public void setzeDaten() {
    model.setzeZelleWW(zellenKoordinaten, !zelleWW);
  }
}
