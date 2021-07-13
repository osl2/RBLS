package praesentation;

import java.awt.Color;

public class Einstellungen {
  
  private String zellenBeschriftungWahr; 
  private String zellenBeschriftungFalsch; 
  private FarbenEinstellungen farbenEinstellungen;
  
  public Einstellungen(Color color) {
    this.zellenBeschriftungWahr = "1"; // "wahr"; "w"; "true";
    this.zellenBeschriftungFalsch = "0"; // "falsch"; "f"; "false";
    this.farbenEinstellungen = new FarbenEinstellungen(color);
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
    this.zellenBeschriftungWahr = "1"; // "wahr"; "w"; "true";
    this.zellenBeschriftungFalsch = "0"; // "falsch"; "f"; "false";
  }
}
