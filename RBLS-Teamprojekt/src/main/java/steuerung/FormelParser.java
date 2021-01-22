package steuerung;

import java.util.ArrayList;
import java.util.List;

import modell.formel.*;

public class FormelParser {

  List<String> klammerAusdruecke = new ArrayList<String>();

  /**
   * Baut eine Formel die als String vorliegt in eine Baumstruktur um.
   * 
   * @param formelS die gegebene Formel als String
   * @return Das Wurzelelement der Formel
   */
  public Formel pars(String formelS) {
    String[] formleSplit;
    Formel formelF = null;
    klammerAusdrueckeErsetzen(formelS);
    formleSplit = formelS.split("f", 2);
    if (formleSplit.length > 1) {
      Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]));
      Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]));
      formelF = new Implikation(rechts, links);
    } else {
      formleSplit = formelS.split("x", 2);
      if (formleSplit.length > 1) {
        Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]));
        Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]));
        formelF = new Oder(rechts, links);
      } else {
        formleSplit = formelS.split("o", 2);
        if (formleSplit.length > 1) {
          Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]));
          Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]));
          formelF = new Oder(rechts, links);
        } else {
          formleSplit = formelS.split("u", 2);
          if (formleSplit.length > 1) {
            Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]));
            Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]));
            formelF = new Und(rechts, links);
          } else {
            formleSplit = formelS.split("n", 1);
            if (formleSplit.length > 1) {
              Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]));
              formelF = new Nicht(rechts);
            }
          }
        }
      }
    }
    return formelF;
  }

  /**
   * Ersetzt Klammerausdr�cke.
   * 
   * @param formelS Formel in der Klammernasudr�cke durch k(Index) ersetzt werden.
   * @return Formel mit Ersetzungen
   */
  private String klammerAusdrueckeErsetzen(String formelS) {
    int i = 0;
    while (formelS.matches("...\\(...\\)...")) {
      i++;
      klammerAusdruecke.add((formelS.substring(formelS.indexOf("("), formelS.indexOf(")"))));
      formelS.replaceFirst("\\(...\\)", "k" + i);
    }
    return formelS;
  }

  /**
   * Stellt Klammerausdr�cke wieder her.
   * 
   * @param formelS Formel in der k(Index) durch Klammerausdr�cke zur�ck ersetzt
   *                werden.
   * @return Formel mit R�ckersetzungen.
   */
  private String klammerAusdrueckeWiederherstellen(String formelS) {
    int i = 0;
    while (formelS.matches("k.")) {
      i++;
      formelS.replaceFirst("k.", klammerAusdruecke.get(i));
    }
    return formelS;
  }
}
