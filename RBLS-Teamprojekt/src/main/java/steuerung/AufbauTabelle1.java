package steuerung;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Formel;

public class AufbauTabelle1 extends AufbauTabelle {

  private List<String> noetigeFormelnS;
  private int atomSpaltenAnz;

  /**
   * Kostruktor f�r AufbauTabelle1 der die Ausf�hrung des Befehls anst��t.
   * 
   * @param model die SteuerungFassade auf die bei der Ausf�hrung zugegriffen
   *              wird.
   */
  public AufbauTabelle1(SteuerungFassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    noetigeFormelnS = model.gibNoetigeFormel();
    atomSpaltenAnz = model.gibSpaltenAnz();
  }

  @Override
  public void setzeDaten() {
    List<Formel> noetigeFormelnF = new ArrayList<Formel>();

    for (int i = 0; i < noetigeFormelnF.size(); i++) {
      noetigeFormelnF.add(FormelParser.pars(noetigeFormelnS.get(i), model));
    }
    for (int i = 0; i < noetigeFormelnF.size(); i++) {
      model.spalteHinzufuegen();
      model.setzeFormel(noetigeFormelnF.get(i), i + atomSpaltenAnz);
    }

  }
}
