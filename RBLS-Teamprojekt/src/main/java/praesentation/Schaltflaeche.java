package praesentation;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Design der Buttons ausgelagert in eine Unterklasse von JButton.

 * @author Nick
 */
public class Schaltflaeche extends javax.swing.JButton {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -6196848668908499618L;
  public static final int ORANGE = 1;
  public static final int BLAU = 2;
  public static final int WEISS = 3;
  public static final int WEISS_ALT = 4;
  public static final int GRAU_ALT = 5;
  public static final int BLAU_ALT = 6;
  public static final int BLAU_ALT_2 = 7;
  private Fensterverwaltung fw;
  

  public Schaltflaeche(Fensterverwaltung fw) {
    this.setBorderPainted(false);
    // this.setBackground(new Color(255, 102, 0));
    this.fw = fw;
    // this.setBackground(FarbenEinstellungen.getSchaltflaeceBackground());
    this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaeceBackground());
    // this.setForeground(FarbenEinstellungen.getSchaltflaecheForeground());
    this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheForeground());
    
  }

  public Schaltflaeche(int farbe, Fensterverwaltung fw) {
    this.fw = fw;
    setzeFarbDesign(farbe);
   
  }
  
  public Schaltflaeche(String text, Fensterverwaltung fw) {
    // this.setBorderPainted(false);
    this.fw = fw;
    // this.setBackground(new Color(255, 102, 0));
 // this.setBackground(FarbenEinstellungen.getSchaltflaeceBackground());
    this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaeceBackground());
    // this.setForeground(FarbenEinstellungen.getSchaltflaecheForeground());
    this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheForeground());
    this.setText(text);
   
  }
  
  public Schaltflaeche(String text, int farbe, Fensterverwaltung fw) {
    this.setText(text);
    this.fw = fw;
    setzeFarbDesign(farbe);
    
  }
  
 public Schaltflaeche(String text, int farbe) {
   this.setText(text);
   this.setBackground(Color.WHITE);
   this.setForeground(Color.BLACK);
 
  }
 
 public void setzeFarbe(Color c) {
   this.setBackground(c);
 }

 
 
  private void setzeFarbDesign(int farbe) {
  
    switch (farbe) {
      case 1:
        // this.setBackground(new Color(255, 102, 0));
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor1Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor1Foreground());
        break;
      case 2:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor2Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor2Foreground());
        break;
      case 3:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor3Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor4Foreground());
        break;
      case 4:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor4Background());
        break;
      case 5:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor5Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor5Foreground());
        break;
      case 6:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor6Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor6Foreground());
        break;
      case 7:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor7Background());
        this.setForeground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColor7Foreground());
        break;
      default:
        this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getSchaltflaecheColorDefaultBackground());
        break;
    }
  }
  
}