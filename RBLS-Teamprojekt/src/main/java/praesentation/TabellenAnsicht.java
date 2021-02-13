package praesentation;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Vorlage f�r die Ansicht einer Wahrheitstabelle.
 * @author Nick
 */
public abstract class TabellenAnsicht {

  protected JTable tabelle;
  
  public abstract void zeigeTippAn();

  public abstract void aktualisiere(int[] zelle);

  public abstract JPanel gibAnsicht();
  
}