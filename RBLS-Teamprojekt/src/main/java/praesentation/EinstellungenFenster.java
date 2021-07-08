package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;


public class EinstellungenFenster extends javax.swing.JPanel{
  
  private Fensterverwaltung fw;
  private Schaltflaeche zurueck;
  private JRadioButton radioButton1;
  private JRadioButton radioButton2;
  private JRadioButton radioButton3;
  private JRadioButton radioButton4;
  private JRadioButton radioButton5;
  private JLabel aenderungenGespeichert;
  
  public EinstellungenFenster(Fensterverwaltung fw, String status) {
    this.fw = fw;
    init(status);
    
  }
  
  private void init(String status) {
    ImageIcon img = new ImageIcon(getClass().getResource("/Icon/hintergrund.png"));
    ImagePanel buttonPanel = new ImagePanel(img.getImage());
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    Schaltflaeche button1 = new Schaltflaeche("Farbauswahl", 5, fw);
    
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        oeffneFarbAuswahl();
      }
    });
    
    JLabel labelWahrFalsch = new JLabel("Wähle die Bezeichnung für die Belegungen einer Aussage aus:");
    Schaltflaeche saveButton = new Schaltflaeche("Speichern", 5, fw);
    Schaltflaeche button3 = new Schaltflaeche("Einstellungen zurücksetzen", 5, fw);
    
    JLabel leer1 = new JLabel("");
    JLabel leer2 = new JLabel("");
    JLabel leer3 = new JLabel("");
    JLabel leer_ = new JLabel("");
    JLabel leer5 = new JLabel("");
    
    aenderungenGespeichert = new JLabel(status);
    aenderungenGespeichert.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 24));
    
    leer1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height));
    buttonPanel.add(leer1);
    button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height*2));
    buttonPanel.add(button1);
    leer2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height));
    buttonPanel.add(leer2);
    
    labelWahrFalsch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height * 2));
    labelWahrFalsch.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 24));
    buttonPanel.add(labelWahrFalsch);
    
    
    radioButton1 = new JRadioButton("wahr/falsch");
    radioButton1.setActionCommand("wahr/falsch");
    radioButton1.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));
    radioButton1.setSelected(true);

    radioButton2 = new JRadioButton("w/f");
    radioButton2.setActionCommand("w/f");
    radioButton2.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));

    radioButton3 = new JRadioButton("richtig/falsch");
    radioButton3.setActionCommand("richtig/falsch");
    radioButton3.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));

    radioButton4 = new JRadioButton("1/0");
    radioButton4.setActionCommand("1/0");
    radioButton4.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));

    radioButton5 = new JRadioButton("true/false");
    radioButton5.setActionCommand("true/false");
    radioButton5.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(radioButton1);
    group.add(radioButton2);
    group.add(radioButton3);
    group.add(radioButton4);
    group.add(radioButton5);

    buttonPanel.add(radioButton1);
    buttonPanel.add(radioButton2);
    buttonPanel.add(radioButton3);
    buttonPanel.add(radioButton4);
    buttonPanel.add(radioButton5);
    
    leer_.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height));
    buttonPanel.add(leer_);
    
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setzeWahrFalsch();
      }
    });
    buttonPanel.add(saveButton);
    
    
    leer3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height));
    buttonPanel.add(leer3);
    button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height * 2));
    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        einstellungenZuruecksetzen();
      }
    });
    buttonPanel.add(button3);
    
    leer5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
        button1.getMinimumSize().height));
    buttonPanel.add(leer5);
    
    buttonPanel.add(aenderungenGespeichert);
    
    
    this.setLayout(new BorderLayout());
    this.add(buttonPanel, BorderLayout.CENTER);
    
    zurueck = new Schaltflaeche("ZUM MENÜ", 2, fw);
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    this.add(zurueck, BorderLayout.WEST);
  }
  
  /**
   * fuehrt zurueck zum Hauptmenue.
   */
  private void klickeZurueck() {
    fw.oeffneMenue();
  }
  
  private void setzeWahrFalsch() {
    Einstellungen einstellungen = fw.getEinstellungen();
    if (radioButton1.isSelected()) {
      einstellungen.setzeBeschrifungZelleWahr("wahr");
      einstellungen.setzeBeschrifungZelleFalsch("falsch");
    }
    
    else if (radioButton2.isSelected()) {
      einstellungen.setzeBeschrifungZelleWahr("w");
      einstellungen.setzeBeschrifungZelleFalsch("f");
    }
    
    else if (radioButton3.isSelected()) {
      einstellungen.setzeBeschrifungZelleWahr("richtig");
      einstellungen.setzeBeschrifungZelleFalsch("falsch");
    }
    
    else if (radioButton4.isSelected()) {
      einstellungen.setzeBeschrifungZelleWahr("1");
      einstellungen.setzeBeschrifungZelleFalsch("0");
    }
    
    else if (radioButton5.isSelected()) {
      einstellungen.setzeBeschrifungZelleWahr("true");
      einstellungen.setzeBeschrifungZelleFalsch("false");
    }
    aenderungenGespeichert.setText("Änderungen wurden gespeichert!");
    
  }
  
  private void oeffneFarbAuswahl() {
    fw.oeffneFarbAuswahl();
    
  }
  
  private void einstellungenZuruecksetzen() {
    fw.getEinstellungen().getFarbenEinstellungen().zuruecksetzen();
    fw.getEinstellungen().zuruecksetzen();
    fw.oeffneEinstellungen("Einstellungen wurden zurückgesetzt!");
  }

}
