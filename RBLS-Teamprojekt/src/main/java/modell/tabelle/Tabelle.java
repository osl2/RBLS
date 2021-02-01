package modell.tabelle;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Formel;

/**
 * Diese Klasse enth�lt alle Zellenobjekte und h�lt diese in einer 2dimensionalen Liste. 
 * Die Zellen werden �ber die Tabelle angesprochen, verwaltet und abgefragt.
 * Die Zeilen- und Spaltenanzahl ist abhaengig vom Raetsel. Sie enthaelt au�erdem Zellenobjekte vom
 * speziellen Typ Wahrheitswert- oder Formelzelle.
 * @author janne
 *
 */
public class Tabelle {
  
  private List<List<Zelle>> tabelle;
  private int zeile;
  private int spalte;
  private int atomAnz;
  
  /**
   * Konstruktor f�r die Tabelle eines Raetels. Konstruiert anhand der uebergebenen Parameter.
   * @param zeilenAnz Entspricht der Zeilenanzahl, die fuer das Raetsel benoetigt werden.
   * @param spaltenAnz Entspricht der benoetigten Spaltenanzahl des Raetsels.
   * @param atomAnz Anzahl der Atomaren Aussagen.
   */
  public Tabelle(int zeilenAnz, int spaltenAnz, int atomAnz) {
    this.zeile = zeilenAnz;
    this.spalte = spaltenAnz;
    this.atomAnz = atomAnz;
    tabelle = new ArrayList<List<Zelle>>();
    for (int i = 0; i < zeilenAnz; i++) {
      this.spalteHinzufuegen();
    }
  }
  
  public void setzeZelle(int[] z, boolean ww) {
    gibZ(z).setzeZelle(ww);
  }
  
  public void setzeFormel(Formel f, int spalte) {
    int[] temp = {0, spalte};
    this.gibZ(temp).setzeZelle(f);
  }
  
  /** Gibt der Fassade den WW der angepingten Zelle zur�ck.
   * @param z Zellenposition 
   * @return WW
   */
  public boolean gibZellenWert(int[] z) {
    if (z[0] > 0) {
      Wahrheitswertzelle temp = (Wahrheitswertzelle) this.gibZ(z);
      return temp.gibZustand();
    }
    return false;
    // In Testphase exception einf�gen?
  }
  
  /**
   * Gibt die String-Repraesentation von Wahrheitswertzelle oder Formelzelle wieder.
   * @param i Array der Laenge 2, welches die Zelle definiert.
   * @return String-Repraesentation der ausgewaehlten Zelle.
   */
  public String gibZelle(int[] i) {
    return gibZ(i).toString();
  }
  
  /** Sucht das Zellenobjekt im 2dimensionalen Listenfeld.
   * @param i Array der gr��e 2, gibt Zeile und Spalte der Zellenposition an.
   * @return Zellenobjekt.
   */
  private Zelle gibZ(int[] i) {
    return this.tabelle.get(i[0]).get(i[1]);
  }
  
  /** Liefert der Fassade die gew�hlte Aussagenlogische Formel der angegebenen Zelle.
   * @param z Position der Zelle
   * @return Formel der angegeben Zelle.
   */
  public Formel gibAussagenlogischeFormel(int[] z) {
    if (z[0] == 0) {
      Formelzelle temp = (Formelzelle) gibZ(z);
      return temp.gibZustand();
    }
    return null;
  }
  
  public int gibSpaltenAnz() {
    return this.spalte;
  }
  
  public int gibZeilenAnz() {
    return this.zeile;
  }
  
  /** Erlaubt es der Fassade eine neue Spalte mit einer neuen Formel zur Tabelle hinzuzuf�gen.
   * 
   */
  public void spalteHinzufuegen() {
    List<Zelle> temp = new ArrayList<Zelle>();
    temp.add(new Formelzelle());
    for (int h = 1; h < this.zeile; h++) {
      temp.add(new Wahrheitswertzelle());
    }
    tabelle.add(temp);
  }
  
  /** Erlaubt das gezielte L�schen von Spalten.
   * @param spalte zu l�schende Spalte.
   */
  public void spalteEntfernen(int spalte) {
    if ((this.spalte - 1) > 1 && spalte != this.atomAnz - 1) { 
      this.tabelle.remove(spalte);
    } 
  }
  
  /** Gibt die Stringrepr�sentation der genannten Zelle an.
   * @param z Zelle
   * @return String
   */
  public String gibAtomareAussage(int[] z) {
    if (z[0] > 0) {
      Wahrheitswertzelle temp = (Wahrheitswertzelle) this.gibZ(z);
      return temp.toString();
    }
    return null;
  }
  
  /** Ermittelt die Wahrheitswertbelegung einer kompletten Zeile.
   * @param z gew�nschte Zeilennummer
   * @return Booleanarray mit Wahrheitswerten.
   */
  public boolean[] gibZeileFall(int z) {
    boolean[] out = new boolean[this.atomAnz];
    for (int i = 0; i < this.atomAnz; i++) {
      int[] pos = {z,i};
      Wahrheitswertzelle temp = (Wahrheitswertzelle) this.gibZ(pos);
      out[i] = temp.gibZustand();
    }
    return out;
  }
  
  /*unn�tig, da in oberer Methode schon enthalten
   * public Formel gibFormel(int s) {
    if (z[0] == 0) {
      Formelzelle temp = (Formelzelle) gibZ(z);
      return temp.gibZustand();
    }
    return null;
  }
  */
  
  
  /** Gibt die Formel als Text wieder.
   * @param s Spalte, in der die Formel zu finden ist.
   * @return String der Formel.
   */
  public String gibFormelText(int s) {
    int[] temp = {0, s};
    return this.gibAussagenlogischeFormel(temp).gibStringRep();
  }

}
