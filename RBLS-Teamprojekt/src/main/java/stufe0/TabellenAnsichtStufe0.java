package stufe0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import praesentation.Fensterverwaltung;
import praesentation.Schaltflaeche;
import praesentation.TabellenAnsicht;
import praesentation.tabelle.FarbModell;
import praesentation.tabelle.ZellenStatus;
import steuerung.WahrheitstabellenSteuerungen;

public class TabellenAnsichtStufe0 extends TabellenAnsicht {
  
  private WahrheitstabellenSteuerungen strg;
  private Fassade modell;
  private JTable tabelle;             //  
  private Schaltflaeche ausfuellen;
  private String[][] inhalt;
  private int zeilenzahl;
  private int spaltenzahl;
  private boolean[] markierteZeilen;

  private Fensterverwaltung fw;
  private Schaltflaeche zumMenu;


  private int stufe = 0;
  
  private JPanel panel;
  private JPanel tabellenRahmen = new JPanel();

  /**
   * Erstellt eine Wahrheitstabelle mit den Daten aus der Praesentationsfassade
   * und initialisiert die Schaltflaechen und die JTable.
   * 
   * @param modell Praesentationsfassade mit den Daten
   * @param strg   Wahrheitstabellensteuerung fuer Weitergabe der Befehle
   */
  public TabellenAnsichtStufe0(Fassade modell, WahrheitstabellenSteuerungen strg, Fensterverwaltung fw) {
    this.fw = fw;
    this.modell = modell;
    this.strg = strg;
    zumMenu = new Schaltflaeche("zum Menü", 6, this.fw);
    init();
  }
  
  private void init() {
    zeilenzahl = modell.gibZeilenAnz();
    spaltenzahl = modell.gibSpaltenAnz();
    stufe = modell.gibStufe();
    markierteZeilen = new boolean[zeilenzahl];
    Arrays.fill(markierteZeilen, false);
    initTabelle();

    // SchaltflaechenPanel//
    JPanel schaltflaechenPanel = new JPanel();
    schaltflaechenPanel.setLayout(new BoxLayout(schaltflaechenPanel, BoxLayout.Y_AXIS));
    schaltflaechenPanel.setBackground(Color.WHITE);
    schaltflaechenPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    
    schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 5)));
  
    schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 5)));
   
    schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    
    zumMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        geheZuRaetselwahlMenue();
      }
    });
    
    zumMenu.setzeFarbe(new Color(220, 220, 220));
    
    schaltflaechenPanel.add(zumMenu, BorderLayout.NORTH);
    

    // Tabellenrahmen //
    tabellenRahmen.setLayout(new BorderLayout());
    tabellenRahmen.add(tabelle, BorderLayout.CENTER);
    tabellenRahmen.setBackground(Color.BLACK);                                /* Rahmen der Tabelle */
    tabellenRahmen.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
    JScrollPane scrollPane = new JScrollPane(tabellenRahmen);
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        this.thumbColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarThumb();
        this.trackColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarTrack(); 
      }
    });
    scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        this.thumbColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarThumb();
        this.trackColor = fw.getEinstellungen().getFarbenEinstellungen().getStufenRaetselScrollBarTrack(); 
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
   
    inhalt = new String[modell.gibAnzahlAussagen() + 1][2];
    inhalt[0][0] = modell.gibRaetselStufe0().gibSpaltenueberschriften()[0];
    inhalt[0][1] = modell.gibRaetselStufe0().gibSpaltenueberschriften()[1];
    char start = 'A';
   
    for (int i = 1; i < modell.gibAnzahlAussagen()+1; i++) {
      char aussageChar = (char) (i-1);
      aussageChar += start;
      String aussage = String.valueOf(aussageChar);
      inhalt[i][0] = aussage;
      inhalt[i][1] = modell.gibRaetselStufe0().gibJaNeinWahrFalsch().get(0);
    }
   
    tabelle = new JTable(inhalt, inhalt[0]);
    FarbModellStufe0 tm = new FarbModellStufe0(inhalt, inhalt[0]);
    tabelle.setModel((FarbModellStufe0) tm);
    
    for (int j = 0; j < spaltenzahl; j++) {
      tabelle.getColumnModel().getColumn(j)
          .setCellRenderer(new stufe0.FarbRendererStufe0(fw));
      
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
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        int i = tabelle.rowAtPoint(evt.getPoint());
        int j = tabelle.columnAtPoint(evt.getPoint());
        System.out.println("Ändern: row=" + i + " col=" + j);
        if (i > 0 && i < 3 && j == 1) {
          evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
      }
      
      @Override
      public void mouseExited(java.awt.event.MouseEvent e)
      {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
    });
    
    tabelle.setRowHeight((int) (tabelle.getRowHeight() * 1.5));
    tabelle.setFocusable(false);
    tabelle.setRowSelectionAllowed(false);
  
    
    tabelle.setPreferredScrollableViewportSize(tabelle.getPreferredSize());
    tabelle.setFillsViewportHeight(true);
  }


  @Override
  public void zeigeTippAn() {
   // kein Tipp für Stufe 0
    
  }

  
  
  private void klickeZelle(int i, int j) {
    if (i > 0 && j > 0) {
      aktualisiere(new int[] { i, j });
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
    if (i < zeilenzahl && j < spaltenzahl) {
      // inhalt[i][j] = modell.gibZelle(zelle);
      if (inhalt[i][j] == modell.gibRaetselStufe0().gibJaNeinWahrFalsch().get(0)) {
        inhalt[i][j] = modell.gibRaetselStufe0().gibJaNeinWahrFalsch().get(1);
        ((FarbModellStufe0) tabelle.getModel()).setzeStatus(i, j, ZellenStatusStufe0.falsch);
      }
      else  {
        inhalt[i][j] = modell.gibRaetselStufe0().gibJaNeinWahrFalsch().get(0);
        ((FarbModellStufe0) tabelle.getModel()).setzeStatus(i, j, ZellenStatusStufe0.wahr);
      }
    }
    
    tabelle.getModel().setValueAt(inhalt[zelle[0]][zelle[1]], zelle[0], zelle[1]);
    ((FarbModellStufe0) tabelle.getModel()).fireTableCellUpdated(i, j);
    
  }
  
  @Override
  public JPanel gibAnsicht() {
    return panel;
  }
  
  public boolean istAusgefuellt() {
    return strg.gibTabelleVoll();
  }

  @Override
  public double getZeilenHoehe() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  private void geheZuRaetselwahlMenue() {
    fw.oeffneRaetselwahl(modell.gibStufe());
  }
  
  private String gibInhaltZelle(int i, int j) {
    return (String) tabelle.getModel().getValueAt(i, j);
  }
  
  public String ueberpruefeLoesung() {
    String result = "";
    for (int i = 1; i < zeilenzahl; i++) {
      result = result + gibInhaltZelle(i, 1) + " ";
    }
    return result;
  }


}
