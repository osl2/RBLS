package modell.raetsel;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;

/**Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei 
 * dem Rest des Programms zur Verf�gung zu stellen.
 * Es wird daher vom Raetselinterpreten erstellt und der Fassade �bergeben.
 * @author Flo
 *
 */
public class Raetsel {
  
  private String raetselText;
  private int stufe;
  private List<Atom> atom;
  private String antworttext;
  private List<String> antworten;
  private List<Formel> formeln;
  private int spaltenAnz;
  private int zeilenAnz;
  private String loesung;

  
  /**Konstruktor, dem es dem Raetselinterpret erm�glicht, das Raetselobjekt zu erschaffen.
   * @param zeilenAnz
   * @param spaltenAnz
   * @param stufe
   * @param atom
   * @param raetselText
   * @param loesung
   */
  public Raetsel(int zeilenAnz, int spaltenAnz, int stufe, List<Atom> atom, String raetselText, String[] antwortM�glichkeiten, String loesung, List<Formel> formeln) {
    this.spaltenAnz = spaltenAnz;
    this.zeilenAnz = zeilenAnz;
    this.stufe = stufe;
    this.atom = atom;
    this.loesung = loesung;
    this.raetselText = raetselText;
    this.formeln = formeln;
  }
  
  public String gibRaetselText() {
    return raetselText;
  }
  
  public int gibStufe() {
    return stufe;
  }
  
  /**Wandelt die Liste der Atome in eine Liste der entsprechenden Namen der Atome um 
   * und gibt diese zur�ck.
   * @return Liste der Atomnamen.
   */
  public List<String> gibAtomNamen() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.atom.size(); i++) {
      temp.add(atom.get(i).gibStringRep());
    }
    return temp;
  }
  
  public List<Atom> gibAtomareAussage() {
    return atom;
  }
  
  public String gibAntworttext() {
    return antworttext;
  }
  
  public List<String> gibAntwort() {
    return antworten;
  }
  
  public String gibLoesung() {
    return this.loesung;
  }
  
  /**Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der Formeln um 
   * und gibt diese zur�ck.
   * @return Liste der benb�tigten Formelnamen, die zur L�sung des Raetsels ben�tigt werden.
   */
  public List<String> gibFormeln() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.formeln.size(); i++) {
      temp.add(formeln.get(i).gibStringRep());
    }
    return temp;
  }
  
  public int gibSpaltenAnz() {
    return this.spaltenAnz;
  }
  
  public int gibZeilenAnz() {
    return this.zeilenAnz;
  }
  
  public int gibAtomAnz() {
    return atom.size();
  }
}
