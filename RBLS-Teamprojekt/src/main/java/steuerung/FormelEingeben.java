package steuerung;

import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;

public class FormelEingeben extends WahrheitstabellenBefehl {

  private int[] zellenKoordinaten;
  private String alteFromel;
  private List<Atom> atomareAussagen;

  /**
   * Der Konstruktor, f�r die Befehl, der den Befehl auch direkt ausf�hrt.
   * 
   * @param model             die Fassade auf die der Befehl zugreift.
   * @param zellenKoordinaten die Koordinaten der Zelle dessen Formel ge�ndert
   *                          werden soll.
   */
  public FormelEingeben(SteuerungFassade model, int[] zellenKoordinaten) {
    super(model);
    this.zellenKoordinaten = zellenKoordinaten;
    hohleDaten();
    setzeDaten();
  }

  /**
   * hohlt die alte Formel und die atomaren Aussagen von der Fasssade.
   */
  public void hohleDaten() {
    alteFromel = model.gibFormelText(zellenKoordinaten[0]);
    atomareAussagen = model.gibAtomareAussage();
  }

  /**
   * Ruf das Dialogfenster auf, in dem man die Formel editieren kann, und �bertr�gt
   * die neue Formel, �ber die Fassade, zum Modell.
   */
  public void setzeDaten() {
    FormelEditor fe = new FormelEditor(atomareAussagen);
    String neueFormel = fe.gibNeueFormel(alteFromel);
    Formel neueFormelF = FormelParser.pars(neueFormel, model);
    model.setzeFormel(neueFormelF, zellenKoordinaten[0]);
  }
}
