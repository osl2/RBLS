package modelltests;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import praesentation.Fensterverwaltung;

public class Testinterpret extends Raetselinterpret{
  
  public Testinterpret(Fensterverwaltung fv) {
    super();
    // TODO Auto-generated constructor stub
  }

  public static final String PATH = "/src/main/resources/Raetsel";
  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein Raetsel gefunden";
  public static final String FREIES_RAETSEL_NAME = "Freies Raetsel";
  public static final int NUMBER_OF_ROWS = 8;
  
  private Fensterverwaltung fv;
  
  
  private int calcRow(List<String> atome) {
    return (int) Math.pow(2, atome.size()) + 1;
  }
  
  public List<String> liesOrdner(int stufe) {
    List<String> output = new ArrayList<String>();
    output.add("Raetsel1");
    output.add("Raetsel2");
    output.add("Raetseldummy");
    return output;
  }
  
  public Raetsel liesRaetsel(String titel) {
    return new Raetseldummy(null, 0, null, null, null, 0, null, null);
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verfuegbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public Raetsel erstelleFR(List<String> atomA) {
    return new Raetsel(
        FREIES_RAETSEL_NAME,
        0, 
        atomA, 
        null, 
        null, 
        0, 
        null, 
        new ArrayList<String>());
  }
}
