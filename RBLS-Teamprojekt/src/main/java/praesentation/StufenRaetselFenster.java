package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import modell.Fassade;
import steuerung.WahrheitstabellenSteuerungen;
import stufe0.AntwortFeldStufe0;
import stufe0.TabellenAnsichtStufe0;

/**
 * Grafische Ansicht eines Raetsels. Zeigt eine Wahrheitstabelle
 * an und stoesst beim Klicken des Menue-Buttons den Wechsel der Ansicht zum
 * Raetselauswahlmenue an.

 * @author Nick
 */
public class StufenRaetselFenster extends RaetselFenster {

  private String name = "[Platzhalterrätsel]";
  private String frage = "[Platzhalterfragetext]";
  
  // private JTextArea frageFeld;
  private JEditorPane frageFeld;
  private JPanel antwortAnsicht;
  // private Schaltflaeche tipp;
  private Schaltflaeche weiter;
  
  private Fensterverwaltung fw;
  private Fassade modell;

  /**
   * Erstellt die grafische Ansicht eines Stufenraetselfenster und initialisiert die Schaltflaechen.

   * @param fensterverwaltung Fensterverwaltung zum Wechseln des aktiven Fensters
   * @param modell Praesentationsfassade zum Erhalten von Informationen des aktiven Raetsels
   */
  public StufenRaetselFenster(Fensterverwaltung fensterverwaltung,
      Fassade modell, WahrheitstabellenSteuerungen wstrg) {
    
    this.fw = fensterverwaltung;
    this.modell = modell;
    this.name = modell.gibAktivenRaetselnamen();
    // Ersetzt alle Vorkommnisse von § durch eine neue Zeile 
    this.frage = modell.gibFragestellung();
    
    ansicht = new JPanel();
    ansicht.setLayout(new BoxLayout(ansicht, BoxLayout.Y_AXIS));
    // ansicht.setBackground(FarbenEinstellungen.getStufenRaetselAnsichtBackground());
    ansicht.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselAnsichtBackground());
    
    //FragePanel//
    JPanel fragePanel = new JPanel();
    fragePanel.setLayout(new BorderLayout());
    // fragePanel.setBackground(FarbenEinstellungen.getStufenRaetselFensterFragePanelBackground());
    fragePanel.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselFensterFragePanelBackground());
    

    // konkrete Tabellenansicht
    
  
    if (modell.gibStufe() == 0) {
      this.tabelle = new TabellenAnsichtStufe0(modell, wstrg, fw);
      modell.setzeStufe(0);
      // this.tabelle = new KonkreteTabellenAnsicht(modell, wstrg, fw);
    }
    
    else {
      this.tabelle = new KonkreteTabellenAnsicht(modell, wstrg, fw); 
    }

    
    // frageFeld = new JTextArea(frage);
    JEditorPane frageFeld = new JEditorPane("text/html", "");
    
    frageFeld.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    
    Font font = new javax.swing.plaf.FontUIResource("Courier", Font.PLAIN, 20); // Monospaced; Courier; Serif
    frageFeld.setFont(font);
    
    frageFeld.setText(frage);
    
    frageFeld.setEditable(false);
    frageFeld.setHighlighter(null);
    // frageFeld.setLineWrap(true);
    // frageFeld.setWrapStyleWord(true);
    
    JPanel frageFeldPanel = new JPanel();
    frageFeldPanel.setLayout(new BorderLayout());
    
    frageFeld.setCaretPosition(0);
    JScrollPane schiebeRegler = new JScrollPane(frageFeld);
    schiebeRegler.setBorder(BorderFactory.createEmptyBorder());
    schiebeRegler.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
          // this.thumbColor = new Color(255, 102, 0);
          // this.thumbColor = FarbenEinstellungen.getStufenRaetselScrollBarThumb();
          this.thumbColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarThumb();
          // this.trackColor = FarbenEinstellungen.getStufenRaetselScrollBarTrack();
          this.trackColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarTrack();
          
      }
    });
    
    
//    JScrollBar vertical = schiebeRegler.getVerticalScrollBar();
//    vertical.setValue( vertical.getMinimum() );
    
    frageFeldPanel.add(schiebeRegler, BorderLayout.CENTER);
    JPanel frageRahmen = erzeugeRahmenPanel(frageFeldPanel, this.name);
    String leerzeilen = frage.trim();
    if (! leerzeilen.isEmpty()) {
      frageRahmen.setPreferredSize(new Dimension(1, 
          (int) (frage.length() * 0.8 + leerzeilen.split("\n").length * 70)));
    } else {
      frageRahmen.setPreferredSize(new Dimension(1, (int) (frage.length() * 0.8)));
    }
    
    // fragePanel.add(menuePanel, BorderLayout.WEST);
    fragePanel.add(frageRahmen, BorderLayout.CENTER);
    // fragePanel.add(tippPanel, BorderLayout.EAST);
    
    //WahrheitstabellenPanel//
    
    /*
     * Tabellen-Panel (ANFANG)
     */
    
    
    JPanel tabellenPanel = new JPanel();
    tabellenPanel = tabelle.gibAnsicht();
    // tabellenPanel.setBackground(FarbenEinstellungen.getStufenRaetselTabellenPanelBackground());
    tabellenPanel.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselTabellenPanelBackground());
    
    
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 500));          /* Groesse des Panels */
    // tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 5 * (int) tabelle.getZeilenHoehe()));
    
    /*
     * Tabellen-Panel (ENDE)
     */
    
    //AntwortfeldPanel//
    JPanel antwortPanel = new JPanel();
    
    if (modell.gibStufe() == 0) {
      String[] antworten = {"Aussage", "keine Aussage"};
      String loesung = "Aussage";
      
      antwortAnsicht = new AntwortFeldStufe0(antworten, loesung, this, fw).gibAnsicht();
    }
    else {
      antwortAnsicht = new AntwortFeld(modell.gibAntwortmoeglichkeiten(),
          modell.gibAntwortText(), modell.gibLoesung(), this, fw).gibAnsicht();
    }
    
    
    JPanel antwortRahmen = erzeugeRahmenPanel(antwortAnsicht, "Lösung");
    antwortPanel.setLayout(new BorderLayout());
    Border antwortBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);
    antwortPanel.setBorder(antwortBorder);
    // antwortPanel.setBackground(FarbenEinstellungen.getStufenRaetselAntwortPanelBackground());
    antwortPanel.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselAntwortPanelBackground());
    antwortPanel.add(antwortRahmen, BorderLayout.CENTER);
    
    weiter = new Schaltflaeche("WEITER", fw);
    weiter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fw.erledigeRaetsel();
        }
      });
    antwortPanel.add(weiter, BorderLayout.EAST);
    weiter.setVisible(false);
    
    /*
     * Aussagenpanel ANFANG
     *
     */
    
    
    JPanel aussagenPanel = new JPanel();
    aussagenPanel.setLayout(new BorderLayout());
    
//    Border border = BorderFactory.createEmptyBorder(5,5,5,5);
//    // aussagenPanel.setBorder(border);
//    aussagenPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    
    aussagenPanel.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselFensterFragePanelBackground());

    JEditorPane aussageFeld = new JEditorPane("text/html", modell.gibTextImAussagenfeld());
    
    aussageFeld.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
  
    aussageFeld.setFont(font);
 
    aussageFeld.setEditable(false);
    aussageFeld.setHighlighter(null);
    
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel1.setBorder(border1);
    panel1.add(aussageFeld);
    // panel1.setBackground(FarbenEinstellungen.getStufenRaetselPanel1Background());
    panel1.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselAntwortPanelBackground());
    
    aussagenPanel.add(panel1);
    

    /*
     * Aussagenpanel ENDE
     *
     */
    
    //Ansicht zusammenfuegen//
    ansicht.add(fragePanel, 0);
    ansicht.add(aussagenPanel, 1);
    ansicht.add(tabellenPanel, 2);
    ansicht.add(antwortPanel, 3);
    
   
    
    
  }
  
  private JPanel erzeugeRahmenPanel(JPanel innen, String titel) {
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel1.setBorder(border1);
    panel1.add(innen);
    // panel1.setBackground(FarbenEinstellungen.getStufenRaetselPanel1Background());
    panel1.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselAntwortPanelBackground());
    
    JPanel panel2 = new JPanel();
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    // Border border2 = BorderFactory.createEmptyBorder(1, 1, 1, 1);
    // border2 = BorderFactory.createTitledBorder(border2, titel);              Kein Titel im Panel
    // panel2.setBorder(border2);
    panel2.add(panel1);
    // panel2.setBackground(new Color(255, 102, 0));
    // panel2.setBackground(FarbenEinstellungen.getStufenRaetselPanel2Background());
    panel2.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselPanel2Background());
    JPanel panel3 = new JPanel();
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
    Border border3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel3.setBorder(border3);
    panel3.add(panel2, BorderLayout.CENTER);
    // panel3.setBackground(FarbenEinstellungen.getStufenRaetselPanel3Background());
    panel3.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselPanel3Background());
    
    return panel3;
  }

  private void TippAn() {
    tabelle.zeigeTippAn();
  }

  private void geheZuRaetselwahlMenue() {
    fw.oeffneRaetselwahl(modell.gibStufe());
  }
  
  /**
   * Gibt an, ob die Wahrheitstabelle ausgefuellt wurde und oeffnet evtl ein Hinweisfenster.
   * @return true falls Tabelle ausgefuellt, ansonsten false
   */
  public boolean pruefeTabelle() {
    if (!((KonkreteTabellenAnsicht) tabelle).istAusgefuellt()) {
      new FehlerDialog("Die Tabelle ist noch nicht (richtig) ausgefüllt");
      return false;
    } else {
      return true;
    }
  }
  
  public boolean pruefeLoesungStufe0() {
    
    String[] result = (((TabellenAnsichtStufe0) this.tabelle).ueberpruefeLoesung()).split(" ");
    String[] loesung = modell.gibRaetselStufe0().gibLoesungen();
    boolean flag = true;
    
    for (int i = 0; i < loesung.length; i++) {
      if (!result[i].equals(loesung[i])) {
        flag = false;
        break;
      }
    }
    if (flag) {
      return true;
    }
   
    return false;
  }
  
  public void schliesseRaetselAb() {
    weiter.setVisible(true);
  }

}