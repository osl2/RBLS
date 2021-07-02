package praesentation;

import java.awt.Color;

/**
 * Design der Buttons ausgelagert in eine Unterklasse von JButton.

 * @author Nick
 */
class Schaltflaeche extends javax.swing.JButton {

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

  public Schaltflaeche() {
    this.setBorderPainted(false);
    // this.setBackground(new Color(255, 102, 0));
    this.setBackground(FarbenUI.getSchaltflaeceBackground());
    this.setForeground(FarbenUI.getSchaltflaecheForeground());
  }

  public Schaltflaeche(int farbe) {
    setzeFarbDesign(farbe);
  }
  
  public Schaltflaeche(String text) {
    this.setBorderPainted(false);
    // this.setBackground(new Color(255, 102, 0));
    this.setBackground(FarbenUI.getSchaltflaeceBackground());
    this.setForeground(FarbenUI.getSchaltflaecheForeground());
    this.setText(text);
  }
  
  public Schaltflaeche(String text, int farbe) {
    this.setText(text);
    setzeFarbDesign(farbe);
  }
  
  private void setzeFarbDesign(int farbe) {
    switch (farbe) {
      case 1:
        // this.setBackground(new Color(255, 102, 0));
        this.setBackground(FarbenUI.getSchaltflaecheColor1Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor1Foreground());
        this.setBorderPainted(false);
        break;
      case 2:
        this.setBackground(FarbenUI.getSchaltflaecheColor2Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor2Foreground());
        this.setBorderPainted(false);
        break;
      case 3:
        this.setBackground(FarbenUI.getSchaltflaecheColor3Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor4Foreground());
        break;
      case 4:
        this.setBackground(FarbenUI.getSchaltflaecheColor4Background());
        break;
      case 5:
        this.setBackground(FarbenUI.getSchaltflaecheColor5Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor5Foreground());
        break;
      case 6:
        this.setBackground(FarbenUI.getSchaltflaecheColor6Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor6Foreground());
        break;
      case 7:
        this.setBackground(FarbenUI.getSchaltflaecheColor7Background());
        this.setForeground(FarbenUI.getSchaltflaecheColor7Foreground());
        break;
      default:
        this.setBackground(FarbenUI.getSchaltflaecheColorDefaultBackground());
        break;
    }
  }
  
}