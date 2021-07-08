package steuerung;

import modell.Fassade;
import praesentation.Fensterverwaltung;

public class WahrheitstabellenSteuerungen {

  private Fassade model;
  private TabellenPruefer tabellenPruefer;
  private int stufe;
  private boolean tabelleVoll;
  private Fensterverwaltung fv;

  /**
   * Konstruktor f�r die WahrheitstabellenSteuerungen.
   * 
   * @param model die Fassade die von Befehlen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(Fassade model, Fensterverwaltung fv) {
    this.fv = fv;
    this.model = model;
    this.stufe = model.gibStufe();
    tabellenPruefer = new TabellenPruefer(model, stufe);
    tabelleVoll = false;
  }

  /**
   * L�st Befehl von der Pr�sentation auf und konstruiert das passenden
   * Befehls-Objekt und st��t damit dessen Ausf�hrung an.
   * 
   * @param befehl der auszuf�hrende Befehl
   */
  public void befehl(String befehl) {
    String[] split = befehl.split("\\(");
    String befehlsname = split[0];
    String[] parameter = null;
    if (split.length > 1) {
      split[1] = split[1].substring(0, split[1].length() - 1);
      parameter = split[1].split("\\,");
    }
    switch (befehlsname) {
      case "AufbauTabelle":
        switch (stufe) {
          case 1:
            new AufbauTabelle1(model);
            break;
          case 2:
          case 4:
            new AufbauTabelle24(model);
            break;
          case 3:
            new AufbauTabelle3(model);
            break;
          default:
            break;
        }
        break;
      case "FormelEingeben":
        if (Integer.parseInt(parameter[0]) >= model.gibAtomareAussage().size()
            && (stufe == 2 || stufe == 4)) {
          new FormelEingeben(model, Integer.parseInt(parameter[0]), fv);
        }
        break;
      case "FuelleTabelle":
        if ((stufe == 1 || stufe == 2 || stufe == 4) && tabellenPruefer.tabelleFuellenErlaubt()) {
          new FuelleTabelle(model);
          tabelleVoll = true;
        }
        break;
      case "SpalteEntfernen":
        new SpalteEntfernen(model, Integer.parseInt(parameter[0]));
        break;
      case "SpalteHinzufuegen":
        new SpalteHinzufuegen(model);
        break;
      case "ZelleAendern":
        if (stufe == 1 && Integer.parseInt(parameter[1]) < model.gibAtomareAussage().size()) {
          new ZelleInBlauOrangeAendern(model, Integer.parseInt(parameter[0]),
              Integer.parseInt(parameter[1]));
        }
        if (stufe == 3 && Integer.parseInt(parameter[1]) >= model.gibAtomareAussage().size()) {
          new ZelleInBlauOrangeAendern(model, Integer.parseInt(parameter[0]),
              Integer.parseInt(parameter[1]));
        }
        break;
      default:
        break;
    }
  }

  /**
   * gibt zurück ob die Tabelle gefüllt ist.
   * 
   * @return
   */
  public boolean gibTabelleVoll() {
    if (stufe == 3) {
      return gibTip() == null;
    }
    return tabelleVoll;
  }

  public int[] gibTip() {
    return tabellenPruefer.gibFehlerhafteZelle();
  }
}