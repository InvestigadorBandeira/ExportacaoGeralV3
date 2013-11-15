package model;

public class Dados {
    private String valor;
    private String contaContabil;
    private char tipoLancamento;

    public String getContaContabil() {
	return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
	this.contaContabil = contaContabil;
    }

    public char getTipoLancamento() {
	return tipoLancamento;
    }

    public void setTipoLancamento(char tipoLancamento) {
	this.tipoLancamento = tipoLancamento;
    }

    public String getValor() {
	return valor;
    }

    public void setValor(String valor) {
	this.valor = valor;
    }

    @Override
    public String toString() {
	return String
		.format("%s %7s %7s", valor, contaContabil, tipoLancamento);
    }
}
