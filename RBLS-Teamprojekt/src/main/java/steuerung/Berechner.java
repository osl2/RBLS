package steuerung;

public class Berechner {
  /**
   * berechnet alle m�glichen F�lle die m�glich sind.
   * 
   * @param anzAtom     Anzahl der Atome die wahr oder falsch sein k�nnen
   * @param faelle      Das Array in dem alle F�lle einzeln als Array gespeichert
   *                    sind
   * @param aktuellePos Die aktuelle Position im Array.
   * @return die Liste an F�llen.
   */
  public static boolean[][] faelleBerechnen(int anzAtom, boolean[][] faelle, int aktuellePos) {
    int anzFall = (int) Math.pow(2, anzAtom);
    for (int i = aktuellePos; i < aktuellePos + anzFall / 2; i++) {
      faelle[i][(anzAtom - 1)] = true;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos);
    }
    for (int i = aktuellePos + anzFall / 2; i < aktuellePos + anzFall; i++) {
      faelle[i][(anzAtom - 1)] = false;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos + anzFall / 2);
    }
    return faelle;
  }
}
