package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verkn�ft seinen linken und rechten Formelnachbarn mit einem logischen UND beim Auzswerten.
 * Seine String Repr�sentation ist "u".
 * @author Flo
 *
 */
public class Und extends BiKonnektor {

  /** Kostruktor. Setzt �bergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Und(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "u";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) && rechts.auswerten(werte));
  }
  
}
