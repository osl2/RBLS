package steuerung;

import modell.SteuerungFassade;

public class WahrheitstabellenSteuerungen {

  private SteuerungFassade model;
  private TabellenPruefer tabellenPruefer;
  private int stufe;

  /**
   * Konstruktor f�r die WahrheitstabellenSteuerungen.
   * 
   * @param model die Fassade die von Befehlen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(SteuerungFassade model) {
    this.model = model;
    this.stufe = model.gibStufe();
    tabellenPruefer = new TabellenPruefer(model, stufe);
  }

  /**
   * l�st Befehl von der Pr�sentation auf und konstruiert das passenden
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
        new FormelEingeben(model, Integer.parseInt(parameter[0]));
        ueberpruefeTabelle(Integer.parseInt(parameter[0]), 0);
        break;
      case "FuelleTabelle":
        if ((stufe == 1 || stufe == 2 || stufe == 4)) {
          new FuelleTabelle(model);
        }
        break;
      case "SpalteEntfernen":
        new SpalteEntfernen(model, Integer.parseInt(parameter[0]));
        break;
      case "SpalteHinzufuegen":
        new SpalteHinzufuegen(model);
        break;
      case "ZelleAendern":
        new ZelleInBlauOrangeAendern(model, Integer.parseInt(parameter[0]),
            Integer.parseInt(parameter[1]) - 1);
        ueberpruefeTabelle(Integer.parseInt(parameter[0]), Integer.parseInt(parameter[1]));
        break;
      default:
        break;
    }
  }

  public int[] gibTip() {
    return tabellenPruefer.gibFehlerhafteZelle();
  }

  /**
   * �berpr�ft ob der Inhalt der Tabelle korrekt ist.
   * 
   * @return Korrektheit
   */
  private boolean ueberpruefeTabelle(int spalte, int zeile) {
    int[] koordinate = { spalte, zeile };
    switch (stufe) {
      case 1:
        return tabellenPruefer.ueberpuefeFaelle(koordinate).isEmpty();
      case 2:
      case 4:
        return tabellenPruefer.ueberpuefeFormeln();
      case 3:
        return tabellenPruefer.ueberpuefeWW(koordinate).isEmpty();
      default:
        return false;
    }
  }
}