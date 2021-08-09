package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import modell.Fassade;
import praesentation.tabelle.FarbModell;
import praesentation.tabelle.ZellenStatus;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht einer Wahrheitstabelle. Über das Befehlsmuster werden der
 * Wahrheitstabellensteuerung Aktionen mitgeteilt. Außerdem wird die Ansicht
 * einer Zelle mit den Informationen der Modell-Fassade aktuell gehalten.
 * 
 * @author Nick
 */
public class KonkreteTabellenAnsicht extends TabellenAnsicht {

  private WahrheitstabellenSteuerungen strg;
  private Fassade modell;
  private JTable tabelle;             //  
  private Schaltflaeche ausfuellen;
  private Schaltflaeche mehrSpalten;
  private Schaltflaeche wenigerSpalten;
  private Schaltflaeche zeileMarkieren;
  private String[][] inhalt;
  private int zeilenzahl = 9;               /* Warum? */
  private int spaltenzahl = 5;              /* Warum? */
  private boolean[] markierteZeilen;
  private int[] tipp;
  private boolean aktiv = true;
  private Fensterverwaltung fw;
  
  private Schaltflaeche zumMenu;
  private Schaltflaeche tippButton;

  private enum Modus {
    standard, entfernen, markieren
  }

  private Modus modus = Modus.standard;
  private int stufe = 4;
  private JPanel panel;
  private JPanel tabellenRahmen = new JPanel();
  // private Schaltflaeche tipp_schaltflaeche;
  
  private double zeilenHoehe = 1.5;

  /**
   * Erstellt eine Wahrheitstabelle mit den Daten aus der Praesentationsfassade
   * und initialisiert die Schaltflaechen und die JTable.
   * 
   * @param modell Praesentationsfassade mit den Daten
   * @param strg   Wahrheitstabellensteuerung fuer Weitergabe der Befehle
   */
  public KonkreteTabellenAnsicht(Fassade modell, WahrheitstabellenSteuerungen strg, Fensterverwaltung fw) {
    this.fw = fw;
    ausfuellen = new Schaltflaeche("<html>&nbsp Fülle<br />Tabelle</html>", this.fw);
    mehrSpalten = new Schaltflaeche("+", 6, this.fw);
    wenigerSpalten = new Schaltflaeche("-", 6, this.fw);
    zeileMarkieren = new Schaltflaeche("Markieren", 6, this.fw);
    zumMenu = new Schaltflaeche("zum Menü", 6, this.fw);
    this.modell = modell;
    this.strg = strg;
    // this.tipp_schaltflaeche = tipp_schaltflaeche;
    init();
  }
  
//  /**
//   * Erstellt eine Wahrheitstabelle mit den Daten aus der Praesentationsfassade
//   * und initialisiert die Schaltflaechen und die JTable.
//   * 
//   * @param modell Praesentationsfassade mit den Daten
//   * @param strg   Wahrheitstabellensteuerung fuer Weitergabe der Befehle
//   */
//  public KonkreteTabellenAnsicht(Fassade modell, WahrheitstabellenSteuerungen strg, Fensterverwaltung fw) {
//    this.fw = fw;
//    ausfuellen = new Schaltflaeche("<html>&nbsp Fülle<br />Tabelle</html>", this.fw);
//    mehrSpalten = new Schaltflaeche("+", 6, this.fw);
//    wenigerSpalten = new Schaltflaeche("-", 6, this.fw);
//    zeileMarkieren = new Schaltflaeche("Markieren", 6, this.fw);
//    this.modell = modell;
//    this.strg = strg;
//    init();
//  }

  private void init() {
    zeilenzahl = modell.gibZeilenAnz();
    spaltenzahl = modell.gibSpaltenAnz();
    stufe = modell.gibStufe();
    markierteZeilen = new boolean[zeilenzahl];
    Arrays.fill(markierteZeilen, false);
    
    /*
     * Schaltflaechenpanel (rechte Seite) (ANFANG) 
     */
    
    JPanel schaltflaechenPanel = new JPanel();
    
    // schaltflaechenPanel.setLayout(new BoxLayout(schaltflaechenPanel, BoxLayout.Y_AXIS));
    
    if (stufe == 2 || stufe == 4) {
      schaltflaechenPanel.setLayout(new GridLayout(5, 1, 20, 20));
    }
    else {
      schaltflaechenPanel.setLayout(new GridLayout(4, 1, 22, 22));
    }
    schaltflaechenPanel.setBackground(Color.WHITE);
    schaltflaechenPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    
    
//    schaltflaechenPanel.setLayout(new BoxLayout(schaltflaechenPanel, BoxLayout.Y_AXIS));

//    schaltflaechenPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
   
    mehrSpalten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fuegeSpalteHinzu();
      }
    });
    
    wenigerSpalten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        wechseleModus(wenigerSpalten, Modus.entfernen);
      }
    });
    
    
    if (stufe == 2 || stufe == 4) {
      JPanel plusMinusPanel = new JPanel();
      plusMinusPanel.setLayout(new GridLayout(1, 2, 20, 20));
      plusMinusPanel.setBackground(Color.WHITE);
      plusMinusPanel.add(mehrSpalten);
      plusMinusPanel.add(wenigerSpalten);
      schaltflaechenPanel.add(plusMinusPanel);
    }
    
    zeileMarkieren.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        wechseleModus(zeileMarkieren, Modus.markieren);
      }
    });
    
    
    
    schaltflaechenPanel.add(zeileMarkieren);
    
    
    
//    schaltflaechenPanel.add(
//        Box.createRigidArea(new Dimension(0, (int) (mehrSpalten.getMaximumSize().height * 1))));
//    ausfuellen.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//        fuelleAus();
//      }
//    });
    
    if (stufe != 3) {
      schaltflaechenPanel.add(ausfuellen);
    }
   
    // schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    
    // schaltflaechenPanel.add(tipp_schaltflaeche);
    
    
    
    tippButton = new Schaltflaeche("Tipp", 2, fw);
    tippButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          zeigeTippAn();
        }
      });
    
    
    schaltflaechenPanel.add(tippButton);
    
    zumMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        geheZuRaetselwahlMenue();
      }
    });
    
    zumMenu.setzeFarbe(new Color(220, 220, 220));
    
    schaltflaechenPanel.add(zumMenu);
    
    /*
     * Schaltflaechenpanel (rechte Seite) (ENDE) 
     */
    
    /*
     * Tabellen-Panel (ANFANG)
     */
    
    initTabelle();

    // Tabellenrahmen //
    tabellenRahmen.setLayout(new BorderLayout());
    tabellenRahmen.add(tabelle, BorderLayout.CENTER);
    tabellenRahmen.setBackground(Color.GRAY);
    tabellenRahmen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));            /* Rahmen der Tabelle */
    
    JScrollPane scrollPane = new JScrollPane(tabellenRahmen);
    
    /* Vertikale Scrollbar der Tabelle */
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        // this.thumbColor = new Color(255, 102, 0);
        this.thumbColor = Color.BLUE;
        this.trackColor = new Color(186, 185, 219);                                   /* Farbe der ScrollBar ändern */
      }
    });
    
    /* Horizontale Scrollbar der Tabelle */
    scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        // this.thumbColor = new Color(255, 102, 0);
        this.thumbColor = Color.BLUE;
        this.trackColor = new Color(186, 185, 219);                                   /* Farbe der ScrollBar ändern */
      }
    });
    
    scrollPane.setBorder(BorderFactory.createEmptyBorder());

    // Panel //
    panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(schaltflaechenPanel, BorderLayout.EAST);
    panel.setBackground(Color.WHITE);
    tabelle.setFillsViewportHeight(true);
  }

  
  private void initTabelle() {
    // Modelldaten //
    
    /*
     * Zellen der Tabelle werden mit "wahr" oder "falsch" befüllt
     */
    
      inhalt = new String[zeilenzahl][spaltenzahl];
      for (int i = 0; i < inhalt.length; i++) {
        for (int j = 0; j < inhalt[0].length; j++) {
          inhalt[i][j] = modell.gibZelle(new int[] { i, j });
          if (i > 0 && inhalt[i][j].equals("true")) {
            inhalt[i][j] = "wahr";                          // ???? wo verwendet?
          } else if (i > 0) {
            inhalt[i][j] = "falsch";                        // ???? wo verwendet?
          }
        }
      }
   
    
      
    
    // JTable //
    
    /*
     * Konstruktor JTable: JTable(Daten, Spaltennamen)
     */
    tabelle = new JTable(inhalt, inhalt[0]) {
      private static final long serialVersionUID = 1L;
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
          Component component = super.prepareRenderer(renderer, row, column);
          TableColumn spalte = getColumnModel().getColumn(column);
          spalte.setPreferredWidth(Math.max(component.getPreferredSize().width 
              + getIntercellSpacing().width, spalte.getPreferredWidth()));
          return component;
      }
    };
    FarbModell tm = new FarbModell(inhalt, inhalt[0]);
    tabelle.setModel((FarbModell) tm);
    for (int j = 0; j < spaltenzahl; j++) {
      tabelle.getColumnModel().getColumn(j)
          .setCellRenderer(new praesentation.tabelle.FarbRenderer(fw));
    }

   
    /*
     * Anklicken von Zellen, die auf "wahr" oder "falsch" gesetzt sind
     */
    tabelle.addMouseListener(new java.awt.event.MouseAdapter() {
      
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int i = tabelle.rowAtPoint(evt.getPoint());
        int j = tabelle.columnAtPoint(evt.getPoint());
        klickeZelle(i, j);
      }
      
      @Override
      public void mouseEntered(java.awt.event.MouseEvent e) {
        
       
      }
      
      @Override
      public void mouseExited(java.awt.event.MouseEvent e)
      {
        
      }
    });
    
    this.zeilenHoehe *= tabelle.getRowHeight();
    tabelle.setRowHeight((int) (this.zeilenHoehe));
    tabelle.setFocusable(false);
    tabelle.setRowSelectionAllowed(false);
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        aktualisiere(new int[] { i, j });
      }
    }
    // tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    
    tabelle.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
  }

  private void klickeZelle(int i, int j) {
    if (i > 0 && j >= 0 && modus == Modus.markieren) {
      markiereZeile(i);
      return;
    }
    if (!aktiv) { 
      return;
    }
    if (i >= 0 && j >= 0 && modus == Modus.entfernen) {
      entferneSpalte(j);
      return;
    }
    if (i > 0 && j >= 0) {
      strg.befehl("ZelleAendern(" + i + "," + j + ")");
      aktualisiere(new int[] { i, j });
    } else if (i == 0 && j >= 0) {
      klickeFormel(j);
      return;
    }
    if (i >= 0) {
      ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
    }
  }

  private void klickeFormel(int spalte) {
    strg.befehl("FormelEingeben(" + spalte + ")");
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(0, spalte);
    aktualisiere(new int[] { 0, spalte });
  }

  private void fuegeSpalteHinzu() {
    strg.befehl("SpalteHinzufuegen");
    spaltenzahl = modell.gibSpaltenAnz();
    tabelle.setVisible(false);
    initTabelle();
    tabelle.setVisible(true);
    tabellenRahmen.add(tabelle);
  }

  private void entferneSpalte(int j) {
    strg.befehl("SpalteEntfernen(" + j + ")");
    spaltenzahl = modell.gibSpaltenAnz();
    tabelle.setVisible(false);
    initTabelle();
    tabelle.setVisible(true);
    tabellenRahmen.add(tabelle);
    wechseleModus(wenigerSpalten, Modus.entfernen);
  }

  private void markiereZeile(int i) {
    markierteZeilen[i] = !markierteZeilen[i];
    for (int j = 0; j < spaltenzahl; j++) {
      if (markierteZeilen[i] && inhalt[i][j].equals("wahr")) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_wahr);
      } else if (markierteZeilen[i] && inhalt[i][j].equals("falsch")) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_falsch);
      } else if (inhalt[i][j].equals("wahr")) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
      } else {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
      }
      ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
    }
    wechseleModus(zeileMarkieren, Modus.markieren);
  }

  private void wechseleModus(Schaltflaeche s, Modus m) {
    if (modus == Modus.standard) {
      modus = m;
      s.setBackground(Color.DARK_GRAY);
      s.setForeground(new Color(186, 185, 219));
    } else if (modus == m) {
      modus = Modus.standard;
      s.setBackground(new Color(186, 185, 219));
      s.setForeground(Color.DARK_GRAY);
    }
  }

  /**
   * Holt Position einer fehlerhaft ausgefuellten Zelle aus der Steuerung und
   * markiert diese.
   */
  public void zeigeTippAn() {
    int[] alterTipp = tipp;
    if (!(alterTipp == null) && alterTipp[1] < spaltenzahl) {
      aktualisiere(alterTipp);
    }
    tipp = strg.gibTip();
    if (tipp == null) {
      new FehlerDialog("Es gibt keine fehlerhaften Tabellenfelder.");
      return;
    }
    ((FarbModell) tabelle.getModel()).setzeStatus(tipp[0], tipp[1], ZellenStatus.tipp);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(tipp[0], tipp[1]);
  }

  private void fuelleAus() {
    strg.befehl("FuelleTabelle");
    for (int i = modell.gibAtomareAussage().size() - 1; i < spaltenzahl; i++) {
      for (int j = 1; j < zeilenzahl; j++) {
        aktualisiere(new int[] { j, i });
      }
    }
    if (istAusgefuellt() && !modell.gibAktivenRaetselnamen().equals("Freies Raetsel")) {
      mehrSpalten.setEnabled(false);
      wenigerSpalten.setEnabled(false);
      ausfuellen.setEnabled(false);
      aktiv = false;
    } else if (!modell.gibAktivenRaetselnamen().equals("Freies Raetsel")) {
      new FehlerDialog("Die Tabelle ist noch fehlerhaft");
      zeigeTippAn();
    }
  }

  /**
   * Aktualisiert den Wert einer Zelle mit den Daten der Praesentationsfassade.
   * Wird für jede Zelle aufgerufen.
   * @param Koordinaten der Zelle
   */
  
  public void aktualisiere(int[] zelle) {
    int i = zelle[0];
    int j = zelle[1];
    inhalt[i][j] = modell.gibZelle(zelle);
    
    /* Wahr/Falsch-Zellen */
    if (i > 0 && j >= 0) {
      if (inhalt[i][j].equals("true")) {
        inhalt[i][j] = modell.getEinstellungen().beschriftungZelleWahr();               // Hier wird die Beschriftung einer Zelle in der Tabelle festgelegt
        if (!markierteZeilen[i]) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
        } else {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_wahr);
        }
      } else {
        inhalt[i][j] = modell.getEinstellungen().beschriftungZelleFalsch();
        if (!markierteZeilen[i]) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
        } else {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_falsch);
        }
      }
    }
    
    /* Überschriften der Spalten in der Tabelle */
    
    /* Atomar */
    if (i == 0 && j >= 0 && j < modell.gibAtomareAussage().size()) {
      ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.atomar);
    } 
    
    /* Zusammengesetze Formel */
    else if (i == 0 && j >= 0) {
      ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.standard);
    }
    
    
    tabelle.getModel().setValueAt(inhalt[zelle[0]][zelle[1]], zelle[0], zelle[1]);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
  }

  public JPanel gibAnsicht() {
    return panel;
  }
  
  public boolean istAusgefuellt() {
    return strg.gibTabelleVoll();
  }

  public double getZeilenHoehe() {
    return zeilenHoehe;
  }
  
  private void geheZuRaetselwahlMenue() {
    fw.oeffneRaetselwahl(modell.gibStufe());
  }
  
}