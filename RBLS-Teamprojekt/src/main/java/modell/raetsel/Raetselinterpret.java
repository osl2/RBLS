package modell.raetsel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import praesentation.Fensterverwaltung;


/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zurueck.
 * Selbige koennen angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zurueckgeliefert zu bekommen. 
 * Au�erdem ist der RInterpret fuer die Erstellung freier Raetsel(FR) zustaendig.

 * @author Flo
 *
 */
public class Raetselinterpret {

  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein Raetsel gefunden";
  public static final String FREIES_RAETSEL_NAME = "Freies Raetsel";
  public static final int NUMBER_OF_ROWS = 8;
  
  public Raetselinterpret() {

  }
  
  private String[] extrahiere(String input) {
    String[] output = input.split("##");
    return output;
  }
  
  private List<String> exAtome(String input) {
    String[] sep = input.split(",");
    List<String> output = new ArrayList<String>();
    for (String temp : sep) {
      output.add(killSpace(temp));
    }
    return output;
  }
  
  private List<File> gibRaetselausOrdner(int stufe) {
    List<File> output = new ArrayList<File>();
    File path = null;
    switch (stufe) {
      case 0: path = new File("Resources/Raetsel/Stufe 0");
      break;
      case 1: path = new File("Resources/Raetsel/Stufe 1");
      break;
      case 2: path = new File("Resources/Raetsel/Stufe 2");
      break;
      case 3: path = new File("Resources/Raetsel/Stufe 3");
      break;
      case 4: path = new File("Resources/Raetsel/Stufe 4");
      break;
      default: path = null;
    }
    File[] files = path.listFiles();
   
    if (files != null) { 
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          //TODO
        } else {
          output.add(files[i]);
        }
      }
    }
    
    return output;
  }
  
  /**Listet alle verfuegbaren Raetselnamen auf, die im Ordner 
   * src,main,resources,Raetsel unter der jeweiligen Stufe hinterlegt sind.

   * @param stufe Raetselstufe, nach deren Raetsel gesucht wird.
   * @return Liste aller Raetselnamen, die die genannte Stufe erfuellen.
   */
  public List<String> liesOrdner(int stufe) {
    List<String> output = new ArrayList<String>();
    for (File temp : gibRaetselausOrdner(stufe)) {
      String[] name = temp.getName().split(".txt");
      output.add(name[0]);
    }
    return output;
  }
  
  private File findeRaetsel(String titel) {
    
    for (int i = 0; i < 5; i++) {                       // Hier muss die Anzahl der Level angepasst werden!!!!!!!!!!!!
      List<File> ordner = gibRaetselausOrdner(i);
      for (File raetsel : ordner) {
        if (raetsel.getName().equals(titel)) {
          return raetsel;
        }
      }
    }
    return null;
  }
  
  /**Sucht die angegebene Textdatei des Reatsels und liest deren Daten aus, 
   * speichert sie zwischen und erstellt dann ein Raetselobjekt.

   * @param input Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return Ein neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String input) {
    
    String titel = input + ".txt";
    List<String> rows = null;
    File file = findeRaetsel(titel);
    
    if (file != null) {
      try {
        rows = Files.readAllLines(
          FileSystems.getDefault().getPath(findeRaetsel(titel).getAbsolutePath()), 
          StandardCharsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }
      String text = "";
      for (String temp : rows) {
        text += temp;
      }
      String[] lines = extrahiere(text);
      List<String> atome = null;
      String[] antwortM = lines[3].split(",");
      List<String> formeln = new ArrayList<String>();    
      for (String temp : lines[6].split(",")) {
        formeln.add(killSpace(temp));
      }
      //String[] name = lines[0].split(".txt");
      atome = this.exAtome(lines[4]);
      for (int i = 0; i < antwortM.length; i++) {
        antwortM[i] = killSpace(antwortM[i]);
      }
      
      return new Raetsel(
          input,                    //spaltenAnz
          Integer.parseInt(lines[7]), //stufe
          atome,                      //Atome
          lines[1],                   //Raetseltext
          antwortM,                   //Antwortm�glichkeiten
          Integer.parseInt(lines[5]), //Wievielte Antwort die L�sung ist
          lines[2],                   //Antworttext
          formeln);                   //Formelliste  
    } 
    
    else {
      new praesentation.FehlerDialog("Raetsel konnte nicht gefunden werden. "
          + "Starte Freies Raetsel"); 
      List<String> notfall = new ArrayList<String>();
      notfall.add("A");
      notfall.add("B");
      notfall.add("C");
      return erstelleFrRa(notfall);
    }
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.

   * @param atomA Anzahl und Namen der verfuegbaren Atomaren Aussagen, die Benutzer angeben kann.
   */
  public Raetsel erstelleFrRa(List<String> atomA) {
    return new Raetsel(
        FREIES_RAETSEL_NAME,
        4,
        atomA, 
        null, 
        null, 
        0, 
        null, 
        new ArrayList<String>());
  }
  
  private String killSpace(String input) {
    String[] teile = input.split(" ");
    StringJoiner sj = new StringJoiner(" ");
    for (String temp : teile) {
      if (!temp.isEmpty()) {
        sj.add(temp);
      }
    }
    return sj.toString();
  }
}
