package steuerung;

import java.util.List;
import modell.formel.Atom;
import praesentation.FormelAnsicht;

public class FormelEditor {
  private List<Atom> atomareAussagenA;
  private String formelAlt;
  private String formel;

  /**
   * Konstruktor f�r den FormelEditor.
   * 
   * @param atomareAussagen die atomaren Aussagen die in den aussagenlogischen
   *                        Formel vorkommen kommen.
   */
  public FormelEditor(List<Atom> atomareAussagen) {
    this.atomareAussagenA = atomareAussagen;
    formel = "";
  }

  /**
   * �bergibt eine Formel zu der die FormelAnsicht, zur Bearbeitung, ge�ffnet
   * wird. Sobald die Formel best�tigt oder abgeleht wurden wird die Formel zur�ck
   * gegeben.
   * 
   * @param formelAlt die Formel die bearbeitet werden soll und zur�ck gegeben
   *                  wird falls abgebrochen wird.
   * @return die neue Formel.
   */
  public String gibNeueFormel(String formelAlt) {
    this.formelAlt = formelAlt;
    FormelAnsicht ansicht = new FormelAnsicht(atomZuString(atomareAussagenA), this);
    ansicht.getFormel();
    return this.formel;
  }

  /**
   * Wandelt die Atom-Liste in ein String-Liste um, sodass sie von der
   * FormelAnsicht verarbeitet werden kann.
   * 
   * @param atomareAussageA die Aussagen die als Atom Liste �bergeben werden
   * @return die Aussagen der Atome als String-Liste.
   */
  private String[] atomZuString(List<Atom> atomareAussageA) {
    String[] atomareAussageS = new String[atomareAussageA.size()];
    for (int i = 0; i < atomareAussageA.size(); i++) {
      atomareAussageS[i] = (atomareAussageA.get(i).getAussage());
    }
    return atomareAussageS;
  }

  /**
   * setzt ein weiteres Zeichen an das Ende der aktuellen Formel.
   * 
   * @param zeichen das zu setzende Zeichen
   */
  public void setzeZeichen(char zeichen) {
    formel = formel + zeichen;
  }

  /**
   * entfernt das letzte Zeichen der aktuellen Formel.
   */
  public void entferneletzesZeichen() {
    if (formel.length() > 0) {
      formel = formel.substring(0, formel.length() - 1);
    }
  }

  /**
   * setzt die Formel zur�ck.
   */
  public void brecheAb() {
    formel = formelAlt;
  }

  /**
   * gibt zur�ck ob diese Formel g�ltig ist, das hei�t, das keine Klammern mehr
   * offen ist und die Formel nicht mit einen Konnektor endet.
   */
  public boolean bestaetige() {
    char letzerCh = formel.charAt(formel.length() - 1);
    if (!klammerOffen() || (letzerCh == '0' || letzerCh == '1' || letzerCh == '2' || letzerCh == '3'
        || letzerCh == ')')) {
      return true;
    }
    return false;
  }

  /**
   * gibt zur�ck ob das n�chste Zeichen erlaubt ist.
   * 
   * @param naechsterCh das n�chste Zeichen
   * @return ob es erlaubt ist
   */
  public boolean zeichenErlaubt(char naechsterCh) {
    if (formel.equals("")) {
      return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
          || naechsterCh == '2' || naechsterCh == '3';
    }
    switch (formel.charAt(formel.length() - 1)) {
      case '0':
      case '1':
      case '2':
      case '3':
      case ')':
        return naechsterCh == 'o' || naechsterCh == 'u' || naechsterCh == 'x' || naechsterCh == 'f'
            || klammerOffen() && naechsterCh == ')';
      case 'o':
      case 'u':
      case 'x':
      case 'f':
      case 'n':
      case '(':
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3';
      default:
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3';
    }

  }

  /**
   * gibt zur�ck ob es in der aktuelle Formel noch offene Klammern gibt.
   * 
   * @return ob es noch offene Klammern gibt
   */
  private boolean klammerOffen() {
    int offene = 0;
    for (int i = 0; i < formel.length(); i++) {
      if (formel.charAt(i) == '(') {
        offene = offene + 1;
      }
      if (formel.charAt(i) == ')') {
        offene = offene - 1;
      }
    }
    if (offene == 0) {
      return false;
    }
    return true;
  }
}
