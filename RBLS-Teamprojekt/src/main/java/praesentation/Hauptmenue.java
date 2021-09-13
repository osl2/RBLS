package praesentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modell.Fassade;

/**
 * Grafische Ansicht des Hauptmenues.
 * Stoesst bei Mausklick eines Buttons das Oeffnen des Raetselwahl-Bildschirms oder des freien Modus
 * bzw. das Beenden des Programms ueber die Fensterverwaltung an.

 * @author Nick
 */

// public class Hauptmenue extends javax.swing.JPanel {

  
public class Hauptmenue extends ImagePanel {
  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1940385720982920614L;

  private Fensterverwaltung fw;

  private Schaltflaeche stufe0;
  private Schaltflaeche stufe1;
  private Schaltflaeche stufe2;
  private Schaltflaeche stufe3;
  private Schaltflaeche stufe4;
  private Schaltflaeche freierModus;
  private Schaltflaeche beenden;
  private Schaltflaeche einstellungen;
  
  public Hauptmenue(Fensterverwaltung fw, Image image) {
    super(image);
    this.fw = fw;
    init();
  }
    
  /**
   * initialiert GUI und Buttonaktionen.
   */
  private void init() {
    
    stufe0 = new Schaltflaeche(3, fw);
    stufe1 = new Schaltflaeche(3, fw);
    stufe2 = new Schaltflaeche(3, fw);
    stufe3 = new Schaltflaeche(3, fw);
    stufe4 = new Schaltflaeche(3, fw);
    freierModus = new Schaltflaeche("FREIER MODUS", fw);
    beenden = new Schaltflaeche("BEENDEN", fw);
    einstellungen = new Schaltflaeche(3, fw);
    
    JPanel startFeld = new JPanel(new java.awt.FlowLayout());
    // startFeld.setBackground(FarbenEinstellungen.getHauptmenuStartFeldBackground());
    startFeld.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getHauptmenuStartFeldBackground());
    JPanel mitte = new JPanel(new java.awt.BorderLayout());
    // mitte.setBackground(FarbenEinstellungen.getHauptmenuJPanelBackground());
    mitte.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getHauptmenuJPanelBackground());
    mitte.add(startFeld, java.awt.BorderLayout.SOUTH);
    JLabel starttext = new javax.swing.JLabel("Start", SwingConstants.CENTER);
    mitte.add(starttext, java.awt.BorderLayout.NORTH);

    this.setLayout(new java.awt.GridBagLayout());
    // this.setBackground(FarbenEinstellungen.getHauptmenuBackground());
    this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getHauptmenuBackground());
        
    //Stufenbuttons//
    stufe0.setText("Level 0");
    stufe0.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(0);
      }
    });
    startFeld.add(stufe0);
    
    
    stufe1.setText("Level 1");
    stufe1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(1);
      }
    });
    startFeld.add(stufe1);
        
    stufe2.setText("Level 2");
    stufe2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(2);
      }
    });
    startFeld.add(stufe2);
        
    stufe3.setText("Level 3");
    stufe3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(3);
      }
    });
    startFeld.add(stufe3);
        
    stufe4.setText("Level 4");
    stufe4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(4);
      }
    });
    startFeld.add(stufe4);
    
    int stufe = fw.gibFassade().gibAbgeschlosseneStufe();
    
    if (stufe < 1) {
      stufe2.setEnabled(false);
    }
    if (stufe < 2) {
      stufe3.setEnabled(false);
    }
    if (stufe < 3) {
      stufe4.setEnabled(false);
      freierModus.setEnabled(false);
    }
    
    freierModus.setEnabled(true);
        
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 3;
    c.gridy = 2;
    this.add(mitte, c);
        
    //weitere Buttons//
    freierModus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeFreienModus();
      }
    });
    beenden.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeBeenden();
      }
    });
    c.insets = new Insets(74, 50, 74, 50);
    c.fill = java.awt.GridBagConstraints.BOTH;
    c.gridx = 0;
    c.ipadx = 80;
    c.ipady = 60;
    this.add(beenden, c);
    c.gridx = 4;
    this.add(freierModus, c);
    
    //Titel//
    c.gridx = 3;
    c.gridy = 1;
    c.ipady = 2;
    c.insets = new Insets(120, 50, 0, 50);
    JLabel titel1 = new JLabel("RÄTSEL-BASIERTE", SwingConstants.CENTER);
    JLabel titel2 = new JLabel("LOGIK-SOFTWARE", SwingConstants.CENTER);
    JPanel titel = new JPanel();
    titel.setLayout(new BoxLayout(titel, BoxLayout.Y_AXIS));
    titel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    titel2.setAlignmentX(Component.CENTER_ALIGNMENT);
    titel1.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 48));
    titel2.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 48));
    titel.add(titel1, 0);
    titel.add(titel2, 1);
    // titel.setBackground(FarbenEinstellungen.getHauptmenuTitleBackground());
    titel.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getHauptmenuTitleBackground());
    this.add(titel, c);

    einstellungen.setText("Einstellungen");
    einstellungen.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 30));
//    JPanel leer = new JPanel();
//    leer.setLayout(new BoxLayout(leer, BoxLayout.Y_AXIS));
//    leer.setBackground(new Color(255, 255, 255, 0));
    c.gridy = 3;
    c.ipady = 40;
    einstellungen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeEinstellungen("");
      }
    });
    this.add(einstellungen, c);
    

  }

  /**
   * Stoesst Oeffnen von Raetselwahl an.

   * @param stufe Stufe der Raetsel
   */
  private void klickeStart(int stufe) {
    fw.gibFassade().setzeStufe(stufe);
    fw.oeffneRaetselwahl(stufe);
  }
  
  private void klickeEinstellungen(String status) {
    fw.oeffneEinstellungen(status);
  }

  /**
   * stoesst Beenden an.
   */
  private void klickeBeenden() {
    fw.beende();
  }

  /**
   * stoesst Starten des freien Modus an.
   */
  private void klickeFreienModus() {
    fw.gibFassade().setzeStufe(-1);
    fw.starteFreienModus();
  }
  
  
}