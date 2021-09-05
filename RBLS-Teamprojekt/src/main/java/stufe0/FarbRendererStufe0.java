package stufe0;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import praesentation.Fensterverwaltung;
import praesentation.tabelle.FarbModell;

public class FarbRendererStufe0 extends DefaultTableCellRenderer {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;
  private Fensterverwaltung fw;
  
  public FarbRendererStufe0(Fensterverwaltung fw) {
    this.fw = fw;
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    JLabel l = (JLabel) super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, col);
    FarbModellStufe0 fm = (FarbModellStufe0) table.getModel();
    
    switch (fm.gibStatus(row, col)) {
      case fest: l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererStandard());
      break;
    
      case wahr: 
        l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererWahr());
      break;
      
      case falsch:
        l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererFalsch());
      break;
      
      default: l.setBackground(fw.getEinstellungen().getFarbenEinstellungen().getFarbRendererDefault());
    }
    setHorizontalAlignment(javax.swing.JLabel.CENTER);
    return l;
  }
  
}
