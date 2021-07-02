package praesentation.tabelle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import praesentation.FarbenUI;

/**
 * Unterklasse von DefaultTableCellRenderer zum Aendern der Farben einzelner Tabellenzellen.

 * @author Nick
 */
public class FarbRenderer extends DefaultTableCellRenderer {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    JLabel l = (JLabel) super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, col);
    FarbModell fm = (FarbModell) table.getModel();
    switch (fm.gibStatus(row, col)) {
      case standard:l.setBackground(FarbenUI.getFarbRendererStandard());
      break;
      case atomar:l.setBackground(FarbenUI.getFarbRendererAtomar());
      break;
      case wahr:l.setBackground(FarbenUI.getFarbRendererWahr());
      break;
      case falsch:l.setBackground(FarbenUI.getFarbRendererFalsch());
      break;
      case markiert_wahr:l.setBackground(FarbenUI.getFarbRendererMarkiertWahr());
      break;
      case markiert_falsch:l.setBackground(FarbenUI.getFarbRendererMarkiertFalsch());
      break;
      case tipp:l.setBackground(FarbenUI.getFarbRendererTipp());
      break;
      default: l.setBackground(FarbenUI.getFarbRendererDefault());
    }
    setHorizontalAlignment(javax.swing.JLabel.CENTER);
    return l;
  }
}