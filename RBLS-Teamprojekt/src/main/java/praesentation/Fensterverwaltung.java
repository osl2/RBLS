package praesentation;

import javax.swing.JFrame;

import steuerung.Hauptsteuerung;

public class Fensterverwaltung {

	private Hauptsteuerung strg;
	private JFrame aktivesFenster;
	
	public Fensterverwaltung() {	//braucht Fassade
		init();
	}
	
	private void init() {
		strg = new Hauptsteuerung();
		aktivesFenster = new Hauptmenue(this);
	    aktivesFenster.setTitle("RBLS");
	    aktivesFenster.setSize(1000, 620);
	    aktivesFenster.setResizable(true);
	    aktivesFenster.setLocation(50, 50);
	    aktivesFenster.setVisible(true);
	}

	public void oeffneMenue() {	//�ffnet Hauptmenue
		aktivesFenster.setVisible(false);
		aktivesFenster = new Hauptmenue(this);
	    aktivesFenster.setTitle("Hauptmen�");
	    aktivesFenster.setSize(1000, 620);
	    aktivesFenster.setResizable(true);
	    aktivesFenster.setLocation(50, 50);
	    aktivesFenster.setVisible(true);
		
	}
	
	public void oeffneRaetselwahl(int stufe) {
		aktivesFenster.setVisible(false);
		aktivesFenster = new Raetselwahl(this, stufe);	//R�tselliste aus Modell statt Stufe
	    aktivesFenster.setTitle("R�tselwahl");
	    aktivesFenster.setSize(1000, 620);
	    aktivesFenster.setResizable(true);
	    aktivesFenster.setLocation(50, 50);
	    aktivesFenster.setVisible(true);
		
	}

	public void starteRaetsel(String name) {
		
	}
	
	public void oeffneAbschlussFenster() {
		
	}
	
	
	
}
