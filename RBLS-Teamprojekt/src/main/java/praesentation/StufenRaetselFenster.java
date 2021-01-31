package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import modell.PraesentationFassade;

/**
 * Grafische Ansicht eines R�tsels. Zeigt eine Wahrheitstabelle
 * an und st��t beim Klicken des Men�-Buttons den Wechsel der Ansicht zum
 * R�tselauswahlmen� an.
 * @author Nick
 */
public class StufenRaetselFenster extends RaetselFenster {

  private String name = "Raetsel #27";
  private String frage = "Lorem ipsum dolor sit amet, consetetur sadipscing "
      + "elitr, sed diam nonumy eirmod tempor"
      + " invidunt ut labore et dolore magna aliquyam "
      + "erat, sed diam voluptua. At vero eos et accusam et"
      + "justo duo dolores et ea rebum. Stet clita "
      + "kasd gubergren, no sea takimata sanctus est Lore"
      + "m ipsum dolor sit amet. Lorem ipsum dolor "
      + "sit amet, consetetur sadipscing elitr, sed diam nonumy "
      + "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
      + "At vero eos et accusam et justo duo dolores et ea rebum. "
      + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. ";
  
  private JTextArea frageFeld;
  private JPanel antwortAnsicht;
  private Schaltflaeche tipp;
  private Schaltflaeche weiter;
  
  private Fensterverwaltung fv;
  private PraesentationFassade modell;

  /**
   * Erstellt die grafische Ansicht eines Stufenraetselfenster und initialisiert die Schaltflaechen.
   * @param fensterverwaltung Fensterverwaltung zum Wechseln des aktiven Fensters
   * @param modell Praesentationsfassade zum Erhalten von Informationen des aktiven Raetsels
   */
  public StufenRaetselFenster(Fensterverwaltung fensterverwaltung, PraesentationFassade modell) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    this.tabelle = new KonkreteTabellenAnsicht(modell);
    
    /* TODO BRAUCHT MODELL UND STEUERUNG
    this.name = modell.gibAktivenRaetselnamen();
    this.frage = modell.gibFragestellung();
    */
    ansicht = new JFrame();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().setBackground(Color.WHITE);
    
    //FragePanel//
    JPanel fragePanel = new JPanel();
    fragePanel.setLayout(new BorderLayout());
    fragePanel.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Men�", 2);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuRaetselwahlMenue();
        }
      });
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    menuePanel.add(menueKnopf);
    menuePanel.setBackground(Color.WHITE);
    
    tipp = new Schaltflaeche("Tipp", 2);
    tipp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          zeigeTippAn();
        }
      });
    JPanel tippPanel = new JPanel();
    tippPanel.setLayout(new FlowLayout());
    tippPanel.add(tipp);
    tippPanel.setBackground(Color.WHITE);
    
    frageFeld = new JTextArea(frage);
    frageFeld.setEditable(false);
    frageFeld.setLineWrap(true);
    frageFeld.setWrapStyleWord(true);
    
    JPanel frageFeldPanel = new JPanel();
    frageFeldPanel.setLayout(new BoxLayout(frageFeldPanel,BoxLayout.X_AXIS));
    frageFeldPanel.add(frageFeld);
    JPanel frageRahmen = erzeugeRahmenPanel(frageFeldPanel, this.name);
    
    fragePanel.add(menuePanel, BorderLayout.WEST);
    fragePanel.add(frageRahmen, BorderLayout.CENTER);
    fragePanel.add(tippPanel, BorderLayout.EAST);
    
    //WahrheitstabellenPanel//
    JPanel tabellenPanel = new JPanel();
    tabellenPanel.setLayout(new BorderLayout());
    tabellenPanel.setBackground(Color.DARK_GRAY);
    tabellenPanel.add(new javax.swing.JLabel("TABELLE", SwingConstants.CENTER));
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,1000));
    
    //AntwortfeldPanel//
    JPanel antwortPanel = new JPanel();
    
    ////TODO Platzhalter, bis Programm ausf�hrbar!!
    antwortAnsicht = new AntwortFeld(null,null,null,this).gibAnsicht();
    //antwortAnsicht = new AntwortFeld(modell.gibAntwortmoeglichkeiten(),
    //    modell.gibAntwortText(),modell.gibLoesung(),this).gibAnsicht();
    
    JPanel antwortRahmen = erzeugeRahmenPanel(antwortAnsicht, "L�sung");
    antwortPanel.setLayout(new BorderLayout());
    Border antwortBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);
    antwortPanel.setBorder(antwortBorder);
    antwortPanel.setBackground(Color.WHITE);
    antwortPanel.add(antwortRahmen, BorderLayout.CENTER);
    
    weiter = new Schaltflaeche("WEITER");
    weiter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fv.erledigeRaetsel();
        }
      });
    antwortPanel.add(weiter, BorderLayout.EAST);
    weiter.setVisible(false);
    
    //Ansicht zusammenf�gen//
    ansicht.getContentPane().add(fragePanel, 0);
    ansicht.getContentPane().add(tabellenPanel, 1);
    ansicht.getContentPane().add(antwortPanel, 2);
  }
  
  private JPanel erzeugeRahmenPanel(JPanel innen, String titel) {
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
    Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel1.setBorder(border1);
    panel1.add(innen);
    panel1.setBackground(Color.WHITE);
    
    JPanel panel2 = new JPanel();
    panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
    Border border2 = BorderFactory.createEmptyBorder(1,1,1,1);
    border2 = BorderFactory.createTitledBorder(border2, titel);
    panel2.setBorder(border2);
    panel2.add(panel1);
    panel2.setBackground(new Color(255,102,0));
    JPanel panel3 = new JPanel();
    panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
    Border border3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel3.setBorder(border3);
    panel3.add(panel2);
    panel3.setBackground(Color.WHITE);
    
    return panel3;
  }

  private void zeigeTippAn() {
    tabelle.zeigeTippAn();
  }

  private void geheZuRaetselwahlMenue() {
    //fv.oeffneRaetselwahl(modell.gibAktuelleStufe());
    fv.oeffneMenue();  //TODO Platzhalter, bis Programm ausf�hrbar ist
  }
  
  public void schliesseRaetselAb() {
    //TODO NUR falls Tabelle stimmt
    weiter.setVisible(true);
  }

}