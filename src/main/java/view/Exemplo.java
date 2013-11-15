package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import util.Importacao;

import com.toedter.calendar.JDateChooser;

public class Exemplo extends JFrame {

    private final JPanel contentPane;
    private final JTextField txtArquivoImportacao;
    private final JButton btnAbrirArquivo;
    private final JLabel lblMensagemImportacao;
    private final JComboBox cbxPLanilha;
    private final JButton btnProcessarImportacao;

    public Exemplo() {
	setResizable(false);
	setIconImage(Toolkit.getDefaultToolkit().getImage(
		Exemplo.class.getResource("/imagens/tools.png")));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 556, 355);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	//
	JDateChooser calendar = new JDateChooser();
	calendar.setBounds(143, 175, 145, 25);
	contentPane.add(calendar);
	calendar.setDateFormatString("dd/MM/yyyy");
	// calendar.setBorder(new LineBorder(new Color(30, 144, 255), 1,
	// false));
	calendar.setBackground(Color.WHITE);

	txtArquivoImportacao = new JTextField();
	txtArquivoImportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtArquivoImportacao.setEditable(false);
	txtArquivoImportacao.setColumns(10);
	txtArquivoImportacao.setBounds(10, 11, 234, 25);
	contentPane.add(txtArquivoImportacao);

	btnAbrirArquivo = new JButton("Selecionar Arquivo");
	btnAbrirArquivo.addActionListener(new ButtonActionListener());
	btnAbrirArquivo.setBounds(254, 11, 150, 25);
	contentPane.add(btnAbrirArquivo);

	lblMensagemImportacao = new JLabel("Nenhum arquivo importado.");
	lblMensagemImportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblMensagemImportacao.setBounds(10, 47, 474, 25);
	contentPane.add(lblMensagemImportacao);

	cbxPLanilha = new JComboBox();
	cbxPLanilha.setModel(new DefaultComboBoxModel(new String[] { "Camed",
		"Exportacao" }));
	cbxPLanilha.setBounds(254, 83, 150, 25);
	contentPane.add(cbxPLanilha);

	btnProcessarImportacao = new JButton("Processar Importa\u00E7\u00E3o");
	btnProcessarImportacao.setBounds(254, 119, 150, 25);
	contentPane.add(btnProcessarImportacao);

	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    private void mensagem(String mensagem) {
	JOptionPane.showMessageDialog(this, mensagem, "Exportação Geral/Sac",
		JOptionPane.PLAIN_MESSAGE);
    }

    private class ButtonActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    JFileChooser chooser = new JFileChooser(".");
	    chooser.setDialogTitle("Selecione um arquivo do Excel");
	    chooser.setMultiSelectionEnabled(false);
	    chooser.setFileFilter(new FileNameExtensionFilter("Arquivo XLS",
		    "xls"));

	    int retorno = chooser.showOpenDialog(rootPane);

	    Workbook excel = null;
	    Sheet planilha = null;

	    if (retorno == JFileChooser.APPROVE_OPTION) {
		String arquivo = chooser.getSelectedFile().getPath();
		String nome = chooser.getSelectedFile().getName();

		txtArquivoImportacao.setText(nome);

		Importacao importacao;

		lblMensagemImportacao.setText(nome
			+ " - importado com sucesso.");

		try {
		    excel = Workbook.getWorkbook(chooser.getSelectedFile());
		} catch (BiffException | IOException ex) {
		    mensagem(ex.getMessage());
		}
	    }

	    if (excel != null) {

		cbxPLanilha.removeAllItems();

		planilha = excel.getSheet("exportação");

		StringBuilder nomes = new StringBuilder("NOMES");

		for (String n : excel.getSheetNames())
		    nomes.append("\n" + n);
		mensagem(nomes.toString());
	    }

	    if (planilha != null) {
		StringBuilder dados = new StringBuilder("DADOS\n\n");

		int linhas = planilha.getRows();
		int colunas = planilha.getColumns();

		System.out.println("LINHAS: " + linhas + "\nCOLUNAS: "
			+ colunas);

		for (int l = 0; l < linhas; l++) {
		    for (int c = 0; c < colunas; c++)
			dados.append(" ||  "
				+ planilha.getCell(c, l).getContents().trim());

		    dados.append("\n");
		}

		mensagem(dados.toString());

		excel.close();

	    }

	}
    }
}
