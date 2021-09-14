package praesentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Zeigt zwei Buttons an, durch die das Wechseln zum Hauptmenue
 * bzw. zu einem zufaelligen ungel�sten Raetsel derselben Stufe
 * ueber die Fensterverwaltung angestossen wird.

 * @author Nick
 */
public class Abschlussfenster extends ImagePanel {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = 6076675565632852903L;
  private Fensterverwaltung fw;
  private Schaltflaeche naechstesRaetsel;
  private Schaltflaeche menue;
  private JLabel textLabel;
  private String[] texte = new String[] {
    "Rätsel gelöst!", "Geschafft!", 
      "Du hast das Rätsel gelöst. Klasse.", "Richtig gelöst!"};

  /**
   * Konstruktor.

   * @param fw Fensterverwaltung zum Wechseln der Ansichten.
   */
  public Abschlussfenster(Image img, Fensterverwaltung fw) {
    super(img);
    this.fw = fw;

    naechstesRaetsel = new Schaltflaeche("nächstes Rätsel", 3, fw);
    naechstesRaetsel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeNaechstesRaetsel();
      }
    });
    menue = new Schaltflaeche("zum Menü", 3, fw);
    menue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeMenue();
      }
    });
    JPanel buttons = new JPanel(new java.awt.BorderLayout());
    buttons.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getButtonBackgroundColor());
    naechstesRaetsel.setPreferredSize(new Dimension(300, 100));
    buttons.add(naechstesRaetsel, java.awt.BorderLayout.EAST);
    menue.setPreferredSize(new Dimension(300, 100));
    buttons.add(menue, java.awt.BorderLayout.WEST);
        
    textLabel = new JLabel(texte[new Random().nextInt(texte.length)], SwingConstants.CENTER);
    textLabel.setForeground(Color.BLACK);
    
    this.setLayout(new java.awt.BorderLayout());
    this.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getAbschlussfensterBackground());
    this.setBackground(Color.WHITE);
    this.add(buttons, java.awt.BorderLayout.SOUTH);
    this.add(textLabel, java.awt.BorderLayout.CENTER);
  }
  
  /**
   * fuehrt zum Hauptmenue.
   */
  private void klickeMenue() {
    fw.oeffneMenue();
  }

  /**
  * fuehrt zu zufaelligem Raetsel ueber Fensterverwaltung.
  */
  private void klickeNaechstesRaetsel() {
    fw.starteZufaelligesRaetsel();
  }
  
}