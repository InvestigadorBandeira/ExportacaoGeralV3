package util;

import java.util.ArrayList;
import java.util.List;

import model.Dados;

public class SingletonDados {
    private List<Dados> dados = new ArrayList<Dados>();
    private static SingletonDados instance = null;

    private SingletonDados() {
    }

    public synchronized static SingletonDados instance() {
	if (instance == null) {
	    instance = new SingletonDados();
	}
	return instance;
    }

    public List<Dados> getDados() {
	return dados;
    }

    public void setDados(List<Dados> dados) {
	this.dados = dados;
    }

    public void zeraDados() {
	dados = new ArrayList<Dados>();
    }

    public void adicionaDados(Dados modelo) {
	dados.add(modelo);
    }

    public boolean estaVazia() {
	return dados.isEmpty();
    }
}
