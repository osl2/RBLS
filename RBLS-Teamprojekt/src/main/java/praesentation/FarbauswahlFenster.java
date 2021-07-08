package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

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
    buttonPanel.add(colorChooser);
    
    colorChooser.setSize(new Dimension(40, 40));
    
    
    Schaltflaeche button1 = new Schaltflaeche("Speichern", 5, fw);
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        speichern();
      }
    });
    
    // buttonPanel.add(leer1);
    
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
    
  }

}
