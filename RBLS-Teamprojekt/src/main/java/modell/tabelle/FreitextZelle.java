package modell.tabelle;

public class FreitextZelle extends Zelle{
  
  private String zellenText;

  @Override
  public String toString() {
    return zellenText;
  }
  
  public void setzeZelle(String text) {
    this.zellenText = text;
  }

}
