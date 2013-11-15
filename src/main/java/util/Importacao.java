package util;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import model.Dados;
import Excecao.ExportacaoGeralException;

public class Importacao {
    private final SingletonDados dados = SingletonDados.instance();
    private int numeroLinha = 0;

    public Importacao(File arquivo, String planilha) throws Exception {
	dados.zeraDados();
	carregaDados(arquivo, planilha);
    }

    private void carregaDados(File arquivo, String planilha) throws Exception {
	Workbook excel = Workbook.getWorkbook(arquivo);
	Sheet sheet = excel.getSheet(planilha);

	if (sheet == null) {
	    StringBuilder plans = new StringBuilder();
	    plans.append("Não existe nenhuma planilha com o nome '")
		    .append(planilha).append("'.\n");

	    for (String s : excel.getSheetNames())
		plans.append("\n" + s);

	    throw new ExportacaoGeralException(plans.toString());

	}

	int linhas = sheet.getRows();
	int colunas = sheet.getColumns();
	String linha;

	for (int l = 0; l < linhas; l++) {
	    linha = "";

	    for (int c = 0; c < colunas; c++) {
		linha += sheet.getCell(c, l).getContents().trim();

		if (c < (colunas - 1))
		    linha += ";";
	    }

	    numeroLinha++;
	    processaLinha(linha);

	}

	excel.close();

    }

    private void processaLinha(String linha) throws ExportacaoGeralException {
	String[] tex = linha.split(";");

	if (tex.length != 3) {
	    dados.zeraDados();
	    throw new ExportacaoGeralException("Linha " + numeroLinha
		    + " fora do padrão de importação.\n\n" + linhaErrada(tex));
	}

	String valor = tex[0].replace(",", "").replace(".", "")
		.replace("-", "0").replace("R$", "").trim();

	if ("000".equals(valor) || "00".equals(valor) || "0".equals(valor))
	    return;

	Dados dado = new Dados();
	dado.setValor(valor);
	dado.setContaContabil(tex[1].replaceAll("-\\d", "").trim());
	dado.setTipoLancamento(Character.valueOf(tex[2].trim().toLowerCase()
		.charAt(0)));

	// salva na lista
	dados.adicionaDados(dado);

    }

    private String linhaErrada(String[] linha) {
	StringBuilder info = new StringBuilder();

	for (int i = 0; i < linha.length; i++) {
	    info.append(linha[i]);

	    if (i < (linha.length - 1))
		info.append("  ||  ");
	    else
		info.append("\n\n");
	}

	return info.toString();
    }
}
