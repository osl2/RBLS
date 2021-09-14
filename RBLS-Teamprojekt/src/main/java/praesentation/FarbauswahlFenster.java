package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class FarbauswahlFenster extends javax.swing.JPanel{
  
  private Fensterverwaltung fw;
  private Schaltflaeche zurueck;
  private JColorChooser colorChooser;
  
  public FarbauswahlFenster(Fensterverwaltung fw) {
    this.fw = fw;
    init();
  }

  private void init() {
    
    ImageIcon img = new ImageIcon(getClass().getResource("/Icon/hintergrund.png"));
    ImagePanel buttonPanel = new ImagePanel(img.getImage());
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    colorChooser = new JColorChooser();
    AbstractColorChooserPanel[] oldPanels = colorChooser.getChooserPanels();
    // hier werden alle vordefinierten Panels entfernt
    for (int i = 0; i < oldPanels.length; i++) {
      colorChooser.removeChooserPanel(oldPanels[i]);
    }
    colorChooser.addChooserPanel(new MyChooserPanel());
    
    buttonPanel.add(colorChooser);
    
    colorChooser.setSize(new Dimension(40, 40));
    
    
    Schaltflaeche button1 = new Schaltflaeche("Speichern", 5, fw);
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        speichern();
      }
    });
    
    buttonPanel.add(button1);
   
    
    this.setLayout(new BorderLayout());
    this.add(buttonPanel, BorderLayout.CENTER);
    
    zurueck = new Schaltflaeche("ZURÜCK", 2, fw);
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    this.add(zurueck, BorderLayout.WEST);
    
    
  }
  
  
  private void klickeZurueck() {
    fw.oeffneEinstellungen("");
  }
  
  private void speichern() {
    Color c = colorChooser.getColor();
    fw.getEinstellungen().getFarbenEinstellungen().setColorDesign(c);
    fw.oeffneFarbAuswahl();
    fw.gibFassade().getMemento().setzeFarbe(c);
    fw.gibFassade().getMemento().erstelleMementoDatei();
  }

}

class MyChooserPanel extends AbstractColorChooserPanel {
  public void buildChooser() {
    String[] farben = {"#F8E0E0", "#F8E6E0", "#F8ECE0", "#F7F2E0", "#F7F8E0", "#F1F8E0", "#ECF8E0", "#E6F8E0", "#E0F8E0", 
        "#E0F8E6", "#E0F8EC", "#E0F8F1", "#E0F8F7", "#E0F2F7", "#E0ECF8", "#E0E6F8", "#E0E0F8", "#E6E0F8", 
        "#ECE0F8", "#F2E0F7", "#F8E0F7", "#F8E0F1", "#F8E0EC", "#F8E0E6" };
    setLayout(new GridLayout(0, farben.length));
    for (String farbe : farben) {
      makeAddButton("", Color.decode(farbe));
    }
  }

  public void updateChooser() {
  }

  public String getDisplayName() {
    return "Farbauswahl";
  }

  public Icon getSmallDisplayIcon() {
    return null;
  }
  public Icon getLargeDisplayIcon() {
    return null;
  }
  private void makeAddButton(String name, Color color) {
    JButton button = new JButton(name);
    button.setBackground(color);
    button.setAction(setColorAction);
    add(button);
  }

  Action setColorAction = new AbstractAction() {
    public void actionPerformed(ActionEvent evt) {
      JButton button = (JButton) evt.getSource();

      getColorSelectionModel().setSelectedColor(button.getBackground());
    }
  };
}
