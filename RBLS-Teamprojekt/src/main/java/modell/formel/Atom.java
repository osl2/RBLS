package modell.formel;

public class Atom extends Formel {

  private String aussage;
  private String rep;
  private int nummer;
    
  /** Konstruktor. Setzt nur seine Werte, die er �bergeben bekommt.
   * @param aussage Entspricht dem vollen Namen des Atoms (zB Herbert).
   * @param repraesentation Gro�buchstabe, der das Atom in der Formel repr�sentiert.
   * @param nummer Position im Werte-Array.
   */
  public Atom(String aussage, String repraesentation, int nummer) {
    this.aussage = aussage;
    this.rep = repraesentation;
    this.nummer = nummer;
  }

  public String getAussage() {
    return aussage;
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    return werte[nummer];
  }

  @Override
  public String gibStringRep() {
    return rep;
  }
  

}
