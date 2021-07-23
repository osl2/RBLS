package praesentation;

import java.awt.Color;

public class Einstellungen {
  
  private String zellenBeschriftungWahr; 
  private String zellenBeschriftungFalsch; 
  private FarbenEinstellungen farbenEinstellungen;
  
  private String zellenBeschriftungWahrStart;
  private String zellenBeschriftungFalschStart;
  private FarbenEinstellungen farbenEinstellungenStart;
  
  public Einstellungen(Color color, String wahr, String falsch) {
    this.zellenBeschriftungWahr = wahr; // "wahr"; "w"; "true";
    this.zellenBeschriftungFalsch = falsch; // "falsch"; "f"; "false";
    this.farbenEinstellungen = new FarbenEinstellungen(color);
    
    this.zellenBeschriftungWahrStart = wahr;
    this.zellenBeschriftungFalschStart = falsch;
    this.farbenEinstellungenStart = new FarbenEinstellungen(color);
  }
  
  public String beschriftungZelleWahr() {
    return zellenBeschriftungWahr;
  }
  
  public String beschriftungZelleFalsch() {
    return zellenBeschriftungFalsch;
  }
  
  public void setzeBeschrifungZelleWahr(String zellenBeschriftungWahr) {
    this.zellenBeschriftungWahr = zellenBeschriftungWahr;
  }
  
  public void setzeBeschrifungZelleFalsch(String zellenBeschriftungFalsch) {
    this.zellenBeschriftungFalsch = zellenBeschriftungFalsch;
  }
  
  public FarbenEinstellungen getFarbenEinstellungen() {
    return farbenEinstellungen;
  }
  
  public void zuruecksetzen() {
    zellenBeschriftungWahr = zellenBeschriftungWahrStart;
    zellenBeschriftungFalsch = zellenBeschriftungFalschStart;
    farbenEinstellungen = farbenEinstellungenStart;
  }
  
  public String[] gibStartWahrZurueck() {
    String[] s = {zellenBeschriftungWahrStart, zellenBeschriftungFalschStart};
    return s;
  }
}
