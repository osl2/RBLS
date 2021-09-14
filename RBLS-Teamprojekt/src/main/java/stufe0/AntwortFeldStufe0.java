package stufe0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import praesentation.FehlerDialog;
import praesentation.Fensterverwaltung;
import praesentation.Schaltflaeche;
import praesentation.StufenRaetselFenster;

public class AntwortFeldStufe0 {
  private JPanel ansicht;
  private Schaltflaeche pruefeKnopf;
  private StufenRaetselFenster fenster;
  private Fensterverwaltung fw;

  private String loesung;
  
  /**
   * Konstruktor, erstellt ein Antwortfeld mit den angegebenen Inhalten.

   * @param antworten Antwortmoeglichkeiten als Liste
   * @param text      Text des Antwortsatzes
   * @param loesung   richtige Antwortmoeglichkeit, soll in der Liste enthalten
   *                  sein
   * @param fenster   StufenRaetselFenster, welches das Antwortfeld benoetigt
   */
  public AntwortFeldStufe0(String[] antworten, 
     String loesung, StufenRaetselFenster fenster, Fensterverwaltung fw) {
    
    this.fw = fw;
    this.loesung = loesung;

    this.fenster = fenster;
    JPanel p = new JPanel();
    p.setLayout(new FlowLayout());
    p.setBackground(Color.WHITE);


    pruefeKnopf = new Schaltflaeche("Prüfen", fw);
    pruefeKnopf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        pruefeAntwort();
      }
    });

    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.setBackground(Color.WHITE);
    p2.add(pruefeKnopf);

    ansicht = new JPanel();
    ansicht.setLayout(new BorderLayout());
    ansicht.add(p2, BorderLayout.CENTER);
  }

  public JPanel gibAnsicht() {
    return ansicht;
  }

  private void pruefeAntwort() {
      
    boolean korrekt = fenster.pruefeLoesungStufe0();
    
    if (korrekt) {
      fenster.schliesseRaetselAb();
    } else {
      new FehlerDialog("Falsche Antwort");
    }
  }
}
