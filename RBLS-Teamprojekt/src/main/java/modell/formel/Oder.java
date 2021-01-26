package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verkn�ft seinen linken und rechten Formelnachbarn mit einem logischen ODER beim Auzswerten.
 * Seine String Repr�sentation ist "o".
 * @author Flo
 *
 */
public class Oder extends BiKonnektor {

  /** Kostruktor. Setzt �bergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Oder(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) || rechts.auswerten(werte));
  }

}
