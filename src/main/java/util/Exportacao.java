package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Dados;
import Excecao.ExportacaoGeralException;

public class Exportacao {
    private final SingletonDados dados = SingletonDados.instance();

    public void salvar(String nome, String data, String historico,
	    String complemento) throws Exception {

	if (dados.estaVazia()) {
	    throw new ExportacaoGeralException("Sem dados para exporta��o.");
	} else {
	    salvaNoArquivo(nome, data, historico, complemento);
	}
    }

    private void salvaNoArquivo(String nome, String data, String historico,
	    String complemento) throws IOException {
	BufferedWriter out = new BufferedWriter(new FileWriter(nome));

	for (Dados d : dados.getDados()) {
	    out.write(formataExportacao(d, data, historico, complemento));
	}
	out.close();
    }

    private String formataExportacao(Dados dados, String data,
	    String historico, String complemento) {
	StringBuilder lancamento = new StringBuilder();

	// inicial 15 zeros
	lancamento.append("000000000000000");

	// data ddmmaaaa
	lancamento.append(data);

	// código do histórico
	lancamento.append(historico);

	// 1 débito
	// 2 crédito
	if (dados.getTipoLancamento() == 'd') {
	    lancamento.append(1);
	} else {
	    lancamento.append(2);
	}

	// valor com 13 digitos colocar zeros a esquerda
	for (int i = 0; i < (13 - dados.getValor().length()); i++) {
	    lancamento.append("0");
	}
	lancamento.append(dados.getValor());

	// 60 histórico complemento
	lancamento.append(complemento);
	for (int i = 0; i < (60 - complemento.length()); i++) {
	    lancamento.append(" ");
	}

	// 10 dígitos pra conta
	// formata conta lancamento
	for (int i = 0; i < (10 - dados.getContaContabil().length()); i++) {
	    lancamento.append("0");
	}
	lancamento.append(dados.getContaContabil());

	lancamento.append("\n");

	return lancamento.toString();
    }
}
