package stufe0;


import java.util.ArrayList;
import java.util.List;
import modell.formel.Atom;

/**
 * Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei dem Rest des
 * Programms zur Verfuegung zu stellen. Es wird daher vom Raetselinterpreten
 * erstellt und der Fassade uebergeben.

 * @author Flo
 *
 */
public class RaetselStufe0 {

  protected String raetselText;
  protected int stufe;
  protected List<Atom> atom;
  protected String[] spaltenueberschriften;
  protected String[] loesungen;
  protected List<String> jaNeinWahrFalsch;
  protected int spaltenAnz;
  protected int zeilenAnz;
  protected int loesung;
  protected String name;
  
  protected List<String> aussagen;

  /**
   * Konstruktor für ein Raetselobjekt.

   * @param name Name
   * @param stufe Stufe
   * @param atom Atom
   * @param raetselText Fragestellung
   * @param antwortMoeglichkeiten Moegliche Antworten
   * @param loesung richtige Loesung
   * @param antworttext Antowrttext
   * @param formeln Formeln
   */
  public RaetselStufe0(String raetselName, int stufe, List<String> aussagen, String raetselText, String[] loesungen,
      int loesung, String spaltenueberschriften, List<String> jaNeinWahrFalsch) {
    
    this.aussagen = aussagen;
    
    this.spaltenAnz = 2; 
    this.zeilenAnz = aussagen.size()+1;
    
    this.stufe = stufe;
    stringToAtomList(aussagen);
    this.loesung = 0; // redundant
    this.raetselText = raetselText;
    this.jaNeinWahrFalsch = jaNeinWahrFalsch;
    this.spaltenueberschriften = spaltenueberschriften.split("§"); // loeschen
    this.name = raetselName; 
    this.loesungen = loesungen;
   
   
  }

  private void stringToAtomList(List<String> atomS) {
    atom = new ArrayList<Atom>();
    for (int i = 0; i < atomS.size(); i++) {
      atom.add(new Atom(atomS.get(i), i));
      //  aussagen.add(atomS.get(i));
    }
  }

  public String gibRaetselText() {
    return raetselText;
  }

  public int gibStufe() {
    return stufe;
  }

  public String gibName() {
    return this.name;
  }

  /**
   * Wandelt die Liste der Atome in eine Liste der entsprechenden Namen der Atome
   * um und gibt diese zurueck.

   * @return Liste der Atomnamen.
   */
  public List<String> gibAtomNamen() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.atom.size(); i++) {
      temp.add(atom.get(i).gibStringRep());
    }
    return temp;
  }

  /**
   * Gibt die atmomaren Aussagen des Raetsels zurueck.

   * @return Die atomaren Aussagen als Liste.
   */
  public List<String> gibAtomareAussage() {
    List<String> output = new ArrayList<String>();
    for (Atom temp : atom) {
      output.add(temp.gibStringRep());
    }
    return output;
  }

  public String[] gibSpaltenueberschriften() {
    return spaltenueberschriften;
  }

  public String[] gibLoesungen() {
    return loesungen;
  }

  public String gibLoesung() {
    return this.loesungen[loesung - 1];
  }

  /**
   * Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der
   * Formeln um und gibt diese zurueck.

   * @return Liste der benotigten Formelnamen, die zur Loesung des Raetsels
   *         benoetigt werden.
   */
  public List<String> gibJaNeinWahrFalsch() {
    return jaNeinWahrFalsch;
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

  public List<Atom> gibAtome() {
    return atom;
  }
  
  public String gibAussage(int i) {
    return aussagen.get(i);
  }
  
  public int gibAnzahlAussagen() {
    return aussagen.size();
  }
}
