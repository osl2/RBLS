package stufe0;


import javax.swing.table.DefaultTableModel;



/** Unterklasse von DefaultTableModel, um einen Status von Zellen zu setzten, 
 * aus dem der Farbwert hervorgeht.

 * @author Nick
 */
public class FarbModellStufe0 extends DefaultTableModel {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;
  ZellenStatusStufe0[][] status;
  
  /**
   * Erzeugt eine Tabelle mit dem angegebenem Inhalt.

   * @param inhalt Zweidimensionaler Array mit dem Tabelleninhalt als Strings
   * @param inhalt2 Array mit Inhalten der obersten Zeile --> Spaltenüberschriften
   */
  public FarbModellStufe0(String[][] inhalt, String[] inhalt2) {
    status = new ZellenStatusStufe0[inhalt.length][inhalt[0].length];
    this.setDataVector(inhalt, inhalt2);
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        status[i][j] = ZellenStatusStufe0.fest;
      }
    }
    status[1][1] = ZellenStatusStufe0.wahr;
    status[2][1] = ZellenStatusStufe0.wahr;
    
  }
  
  public boolean isCellEditable(int row, int column) {
    return false;
  }

  public void setzeStatus(int i, int j, ZellenStatusStufe0 s) {
    status[i][j] = s;
  }
    
  public ZellenStatusStufe0 gibStatus(int i, int j) {
    return status[i][j];
  }
}
