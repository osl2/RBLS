package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import modell.Fassade;
import steuerung.Hauptsteuerung;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht des freien Modus. Zeigt eine
 * Wahrheitstabelle an und stoesst beim Klicken des Men�-Buttons den Wechsel der
 * Ansicht zum Hauptmen� an.

 * @author Nick
 */
public class FreiesRaetselFenster extends RaetselFenster {

  private List<String> aussagenListe = new ArrayList<String>();
  private JDialog atomareAussagen;
  private JTextField[] aussagen;
  
  private Fensterverwaltung fv;
  private Fassade modell;
  private Hauptsteuerung strg;

  /**
   * Erstellt die grafische Ansicht eines FreiesRaetselFenster mit noetigen Buttons und Tabelle.

   * @param fensterverwaltung Fensterverwaltung zum Wechseln des Fensters
   * @param modell Praesentationsfassade
   *     zum Setzen und Erhalten von Informationen ueber atomare Aussagen und Status
   */
  public FreiesRaetselFenster(Fensterverwaltung fensterverwaltung,
      Fassade modell, Hauptsteuerung strg) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    this.strg = strg;
    
    //Dialogfenster//
    atomareAussagen = new JDialog();
    atomareAussagen.getContentPane().setLayout(
        new BoxLayout(atomareAussagen.getContentPane(), BoxLayout.Y_AXIS));
    atomareAussagen.getContentPane().setBackground(Color.WHITE);
    
    aussagen = new JTextField[5];
    ((JComponent) atomareAussagen.getContentPane()).setBorder(
        BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel modusHinweis1 = new JLabel("Im freien Modus kannst du die Wahrheitstabelle "
        + "ohne Rätsel mit eigenen Aussagen benutzen.");
    modusHinweis1.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));
    modusHinweis1.setForeground(Color.WHITE);
    atomareAussagen.add(modusHinweis1, 0);
    JLabel modusHinweis2 = new JLabel("Überflüssige Klammern werden hier automatisch entfernt. "
        + "Wähle die Namen für bis zu 5 atomare Aussagen:");
    modusHinweis2.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));
    modusHinweis2.setForeground(Color.WHITE);
    atomareAussagen.add(modusHinweis2, 1);
    JLabel aussagenHinweis = new JLabel(" ");
    aussagenHinweis.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));
    aussagenHinweis.setForeground(Color.WHITE);
    atomareAussagen.add(aussagenHinweis, 2);
    for (int j = 0; j < aussagen.length; j++) {
      aussagen[j] = new JTextField("");
      atomareAussagen.getContentPane().add(aussagen[j], j + 3);
    }
    Schaltflaeche okButton = new Schaltflaeche("OK", Schaltflaeche.WEISS, fv);
    okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          for (int j = 0; j < aussagen.length; j++) {
            if (!aussagen[j].getText().equals("")) {
              aussagenListe.add(aussagen[j].getText());
            }
          }
          if (!aussagenListe.isEmpty()) {
            atomareAussagen.dispose();
            initTabelle();
          } else {
            atomareAussagen.dispose();
            aussagenListe.add("A");
            aussagenListe.add("B");
            aussagenListe.add("C");
            initTabelle();
          }
        }
      });
    atomareAussagen.add(okButton);
    atomareAussagen.setTitle("Atomare Aussagen");
    atomareAussagen.setSize(600, 440);
    atomareAussagen.setMinimumSize(new Dimension(1000, 480));
    atomareAussagen.setResizable(true);
    atomareAussagen.setLocationRelativeTo(null);
    atomareAussagen.setAlwaysOnTop(true);
    atomareAussagen.setModal(true);
    atomareAussagen.setModalityType(ModalityType.APPLICATION_MODAL);
    atomareAussagen.getContentPane().setBackground(new Color(255, 102, 0));
    atomareAussagen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    ImageIcon img = new ImageIcon(getClass().getResource("/Icon/icon.png"));
    atomareAussagen.setIconImage(img.getImage());
    atomareAussagen.setVisible(true);
    
    ansicht = new JPanel();
    ansicht.setLayout(new BoxLayout(ansicht, BoxLayout.Y_AXIS));
    ansicht.setBackground(Color.WHITE);
    
    //oberes Panel//
    JPanel oben = new JPanel();
    oben.setLayout(new BorderLayout());
    oben.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Menü", 2, fv);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuMenue();
        }
      });
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    menuePanel.add(menueKnopf);
    menuePanel.setBackground(Color.WHITE);
    
    JLabel text = new JLabel("Freier Modus      ", SwingConstants.CENTER);
    
    oben.add(menuePanel, BorderLayout.WEST);
    oben.add(text, BorderLayout.CENTER);

    //WahrheitstabellenPanel//
    JPanel tabellenPanel = new JPanel();
    tabellenPanel.setLayout(new BorderLayout());
    tabellenPanel.add(tabelle.gibAnsicht());
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1000));
    tabellenPanel.setBackground(Color.WHITE);
    
    //Ansicht zusammenfuegen//
    ansicht.add(oben, 0);
    ansicht.add(tabellenPanel, 1);
  }

  private void initTabelle() {
    modell.erstelleRaetsel(aussagenListe);
    WahrheitstabellenSteuerungen wstrg;
    wstrg = strg.raetselFensterInit();
    Schaltflaeche tipp = new Schaltflaeche("Tipp", 2, fv);
    tipp.setEnabled(false);
    tabelle = new KonkreteTabellenAnsicht(modell, wstrg, tipp, fv);
  }

  private void geheZuMenue() {
    fv.oeffneMenue();
  }
  
}