package modell.raetsel;

import java.util.List;

/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zur�ck. 
 * Selbige k�nnen angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zur�ckgeliefert zu bekommen. 
 * Au�erdem ist der RInterpret f�r die Erstellung freier Raetsel(FR) zust�ndig
 * @author Flo
 *
 */
public class Raetselinterpret {

  public List<String> liesOrdner(int i) {
    //TODO
    return null;
  }
  
  /**Sucht die angegebene Textdatei des Reatsels und liest deren Daten aus, 
   * speichert sie zwischen und erstellt dann ein Raetselobjekt.
   * @param name Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String name) {
    int zeilen = 0;
    int spalten = 0;
    int atome = 0;
    //TODO
    //lesen der Textdatei, zwischenspeichern in lokaler Variable 
    //und dann mit Konstruktor Raetsel erstellen.
    
    return new Raetsel(zeilen, spalten, atome/*Parameter bei Ausimplementieren aktuallisieren*/);
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verf�gbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public void erstelleFR(List<String> atomA) {
    //TODO
  }
  
  
  
}
