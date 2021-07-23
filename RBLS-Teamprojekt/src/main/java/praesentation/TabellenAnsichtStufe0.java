package praesentation;

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

import praesentation.tabelle.FarbModell;
import praesentation.tabelle.ZellenStatus;
import steuerung.WahrheitstabellenSteuerungen;

public class TabellenAnsichtStufe0 extends TabellenAnsicht {
  
  private WahrheitstabellenSteuerungen strg;
  private Fassade modell;
  private JTable tabelle;             //  
  private Schaltflaeche ausfuellen;
  private String[][] inhalt;
  private int zeilenzahl = 2;
  private int spaltenzahl = 3;
  private boolean[] markierteZeilen;
  private int[] tipp;
  private boolean aktiv = true;
  private Fensterverwaltung fw;

  private enum Modus {
    standard, entfernen, markieren
  }

  private Modus modus = Modus.standard;
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
  public TabellenAnsichtStufe0(Fassade modell, WahrheitstabellenSteuerungen strg, Schaltflaeche tipp_schaltflaeche, Fensterverwaltung fw) {
    this.fw = fw;
    ausfuellen = new Schaltflaeche("<html>&nbsp Fülle<br />Tabelle</html>", this.fw);
    this.modell = modell;
    this.strg = strg;
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
    

    // Tabellenrahmen //
    tabellenRahmen.setLayout(new BorderLayout());
    tabellenRahmen.add(tabelle, BorderLayout.CENTER);
    tabellenRahmen.setBackground(Color.BLACK);                                /* Rahmen der Tabelle */
    tabellenRahmen.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
    JScrollPane scrollPane = new JScrollPane(tabellenRahmen);
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        // this.thumbColor = new Color(255, 102, 0);
        this.thumbColor = Color.BLUE;
        this.trackColor = new Color(186, 185, 219);
      }
    });
    scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
        // this.thumbColor = new Color(255, 102, 0);
        this.thumbColor = Color.BLUE;
        this.trackColor = new Color(186, 185, 219);
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
    inhalt=new String[2][3];
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        inhalt[i][j] = "hi";
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
        e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        aktualisiere(new int[] { i, j });
      }
    }
    // tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    // tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    
    tabelle.setPreferredScrollableViewportSize(tabelle.getPreferredSize());
    tabelle.setFillsViewportHeight(true);
  }


  @Override
  public void zeigeTippAn() {
    // TODO Auto-generated method stub
    
  }

  
  
  private void klickeZelle(int i, int j) {
    if (i > 0 && j >= 0) {
      strg.befehl("ZelleAendern(" + i + "," + j + ")");
      aktualisiere(new int[] { i, j });
    } 
    if (i >= 0) {
      ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
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
      inhalt[i][j] = modell.gibZelle(zelle);
    }
    
    
    /* Wahr/Falsch-Zellen */
    if (i > 0 && j >= 0) {
      if (inhalt[i][j].equals("true")) {
        // inhalt[i][j] = modell.getEinstellungen().beschriftungZelleWahr();               // Hier wird die Beschriftung einer Zelle in der Tabelle festgelegt
        inhalt[i][j] = "Aussage";
        if (!markierteZeilen[i]) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
        } else {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_wahr);
        }
      } else {
        // inhalt[i][j] = modell.getEinstellungen().beschriftungZelleFalsch();
        inhalt[i][j] = "keine Aussage";
        if (!markierteZeilen[i]) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
        } else {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert_falsch);
        }
      }
    }
    
    /* Überschriften der Spalten */
    
    /* Atomar */
    if (i == 0 && j >= 0 && j < modell.gibAtomareAussage().size()) {
      ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.atomar);
    } 
    
    
    tabelle.getModel().setValueAt(inhalt[zelle[0]][zelle[1]], zelle[0], zelle[1]);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
  }
  
  @Override
  public JPanel gibAnsicht() {
    JPanel p = new JPanel();
    p.setBackground(Color.BLACK);
    p.setForeground(Color.BLACK);
    p.setSize(50, 50);
    // return p;
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
  
  


}
