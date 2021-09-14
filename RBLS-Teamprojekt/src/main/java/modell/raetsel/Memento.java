package modell.raetsel;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import praesentation.Fensterverwaltung;
import stufe0.RaetselStufe0;

/**
 * Diese Klasse symbolisiert ein Memento. Sie verwaltet die Speicherung und das
 * Abrufen der Raetsel sowie deren Zustaende und erstellt eine
 * Sicherungs-Textdatei.

 * @author janne
 *
 */
public class Memento {

  /* Eingelesene Zeilen */
  private List<String> memento; 
  
  private int abschlussStufe = 0;
  private Color color; // = new Color(110, 220, 110); // vordefinierte Farbe
  private String[] wahrFalsch = new String[2];
  
  
  public Memento() {
    liesMementoDatei();
  }
  
  public int gibStufenSicherung() {
    this.liesMementoDatei();
    return this.abschlussStufe;
  }

  /** Löscht Daten von Memento: überschreibt textdatei, resettet Memento.
   * 
   */
  public void loesche() {
    Writer fw = null;
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
    } catch (IOException e) {
      new praesentation.FehlerDialog("Sicherung konnte nicht gelöscht werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    this.memento = new ArrayList<String>();
  }

  /**
   * Quelle: http://openbook.rheinwerk-verlag.de/javainsel9/
   * javainsel_17_001.htm#mj87f7ea8c7b8051417049399df2c5782a
   * Erstellt eine Textdatei, welche den aktuellen Spielstand, sprich die höchste
   * geloeste Stufe und den Raetselnamen enthaelt.

   * @return True, wenn die Datei erfolgreich erstellt wurde.
   */
  public boolean erstelleMementoDatei(Raetsel raetsel) {
    Writer fw = null;
    if (istNeu(raetsel.gibName())) {
      memento.add(raetsel.gibName());
    }
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
      if (raetsel.gibStufe() > this.abschlussStufe) {
        fw.write("Stufe: " + raetsel.gibStufe() + "\n");  
        fw.write("Farbe: " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
        fw.write("Belegungen: " + wahrFalsch[0] + " " + wahrFalsch[1] + "\n");
      } else {
        fw.write("Stufe: " + abschlussStufe + "\n");  
        fw.write("Farbe: " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
        fw.write("Belegungen: " + wahrFalsch[0] + " " + wahrFalsch[1] + "\n");
      }
      fw.write("##\n");             
      for (int i = 0; i < memento.size(); i++) {
        fw.write(memento.get(i) + "\n"); /* Hier werden alle gelösten Rätsel aufgelistet */
      }
    } catch (IOException e) {
      new praesentation.FehlerDialog("Sicherung konnte nicht erstellt werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  
  public boolean erstelleMementoDateiStufe0(RaetselStufe0 raetsel) {
    Writer fw = null;
    if (istNeu(raetsel.gibName())) {
      memento.add(raetsel.gibName());
    }
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
      if (raetsel.gibStufe() > this.abschlussStufe) {
        fw.write("Stufe: " + raetsel.gibStufe() + "\n");  
        fw.write("Farbe: " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
        fw.write("Belegungen: " + wahrFalsch[0] + " " + wahrFalsch[1] + "\n");
      } else {
        fw.write("Stufe: " + abschlussStufe + "\n");  
        fw.write("Farbe: " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
        fw.write("Belegungen: " + wahrFalsch[0] + " " + wahrFalsch[1] + "\n");
      }
      fw.write("##\n");             
      for (int i = 0; i < memento.size(); i++) {
        fw.write(memento.get(i) + "\n"); /* Hier werden alle gelösten Rätsel aufgelistet */
      }
    } catch (IOException e) {
      new praesentation.FehlerDialog("Sicherung konnte nicht erstellt werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  
  public boolean erstelleMementoDatei() {
    Writer fw = null;
    
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
      fw.write("Stufe: " + abschlussStufe + "\n");  
      fw.write("Farbe: " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
      fw.write("Belegungen: " + wahrFalsch[0] + " " + wahrFalsch[1] + "\n");
      fw.write("##\n");             
      for (int i = 0; i < memento.size(); i++) {
        fw.write(memento.get(i) + "\n"); /* Hier werden alle gelösten Rätsel aufgelistet */
      }
    } catch (IOException e) {
      new praesentation.FehlerDialog("Sicherung konnte nicht erstellt werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  
  private boolean istNeu(String name) {
    boolean output = true;
    for (String temp : memento) {
      if (temp.equals(name)) {
        output = false;
      }
    }
    return output;
  }
  
  /** Überprüft, ob die Textdatei zur Sicherung der Informationen vorhanden ist 
   * und erzeugt diese wenn nicht.

   * @return  true, wenn die Datei nach ablauf der Methode existiert und auslesbar ist.
   */
  private boolean pruefeTextdatei() {
    File file = new File("Resources/Sicherung/Sicherung.txt");
    
    /* Wenn Sicherungsdatei nicht existiert */ 
    if (!file.exists()) {
      String fileName = "Resources/Sicherung/Sicherung.txt";
      String encoding = "UTF-8";
      try {
        PrintWriter writer = new PrintWriter(fileName, encoding);
        writer.println("Stufe: 0");
        writer.println("Farbe: " + 204 + " " + 255 + " " + 204);
        writer.println("Belegung: 1 0");
        writer.println("##");
        writer.close();
      } catch (IOException e) {
        new praesentation.FehlerDialog("Fehler beim Erstellen. Ist der Ordner entpackt?");
      }
    }
    
    if (file.isFile() && file.canWrite() && file.canRead()) {
      return true;
    }
    
    return false;
  }

  /**
   * Liest die Memento-Textdatei und schreibt den Inhalt in eine String-Liste.
   * Erstellt außerdem eine Liste nur mit den Namen der bisher geloesten Raetsel.
   */
  private void liesMementoDatei() {
    if (!pruefeTextdatei()) {
      new praesentation.FehlerDialog("Die Sicherungsdatei ist nicht erstellbar");
    }
    try {
      memento = extrahiere(Files.readAllLines(
          FileSystems.getDefault().getPath("Resources/Sicherung/Sicherung.txt"),
          StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
    /* Hier wird die Abschlussstufe vermerkt. */
    if (memento != null && !memento.isEmpty()) {
      String abschlussStufeZeile = memento.get(0);
      abschlussStufeZeile = abschlussStufeZeile.split(" ")[1];
      this.abschlussStufe = Integer.parseInt(abschlussStufeZeile);
      
      String farbenRGB = memento.get(1);
      String[] farbenZahlen = farbenRGB.split(" ");
      
      String wahrFalschString = memento.get(2);
      String[] stringArray = wahrFalschString.split(" ");
      wahrFalsch[0] = stringArray[1];
      wahrFalsch[1] = stringArray[2];
      
      if (color == null) {
        int farbeRot = Integer.parseInt(farbenZahlen[1]);
        int farbeGruen = Integer.parseInt(farbenZahlen[2]);
        int farbeBlau = Integer.parseInt(farbenZahlen[3]);
        
        color = new Color(farbeRot, farbeGruen, farbeBlau);
      } 
    }
  
    memento.remove(0);    
    memento.remove(0);
    memento.remove(0);

  }

  private List<String> extrahiere(List<String> input) {
    List<String> output = new ArrayList<String>();
    for (String temp : input) {
      if (!temp.equals("##")) {
        output.add(temp);
      }
    }
    return output;
  }
  
  public List<String> gibGeloesteRaetsel() {
    liesMementoDatei();
    return memento;
  }
  
  public Color gibFarbeZurueck() {
    return color;
  }
  
  public void setzeFarbe(Color color) {
    this.color = color;
  }
  
  public void setzeWahrFalsch(String[] wahrFalsch) {
    this.wahrFalsch = wahrFalsch;
  }
  
  public String[] gibWahrFalschZurueck() {
    return wahrFalsch;
  }
}
