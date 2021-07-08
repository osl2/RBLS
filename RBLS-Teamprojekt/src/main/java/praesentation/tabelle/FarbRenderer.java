package praesentation.tabelle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import praesentation.FarbenEinstellungen;
import praesentation.Fensterverwaltung;

/**
 * Unterklasse von DefaultTableCellRenderer zum Aendern der Farben einzelner Tabellenzellen.

 * @author Nick
 */
public class FarbRenderer extends DefaultTableCellRenderer {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;
  private Fensterverwaltung fw;
  
  public FarbRenderer(Fensterverwaltung fw) {
    this.fw = fw;
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    JLabel l = (JLabel) super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, col);
    FarbModell fm = (FarbModell) table.getModel();
    switch (fm.gibStatus(row, col)) {
      case standard:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererStandard());
      break;
      case atomar:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererAtomar());
      break;
      case wahr:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererWahr());
      break;
      case falsch:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererFalsch());
      break;
      case markiert_wahr:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererMarkiertWahr());
      break;
      case markiert_falsch:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererMarkiertFalsch());
      break;
      case tipp:l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererTipp());
      break;
      default: l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererDefault());
    }
    setHorizontalAlignment(javax.swing.JLabel.CENTER);
    return l;
  }
}