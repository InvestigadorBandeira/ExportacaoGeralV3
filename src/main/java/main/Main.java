package main;

import javax.swing.JOptionPane;

import view.PrincipalView;

public class Main {
    public static void main(String[] args) {
	try {
	    // new Exemplo();
	    new PrincipalView();
	} catch (Exception ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage(),
		    "Exportação Geral para Sac", JOptionPane.ERROR_MESSAGE);
	}
    }
}
