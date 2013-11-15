package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import util.Exportacao;
import util.FixedLengthDocument;
import util.Importacao;

import com.toedter.calendar.JDateChooser;

public class PrincipalView extends JFrame {

    private final JPanel contentPane;
    private final JPanel pnExportacao;
    private final JLabel lblImportacao;
    private final JLabel lblArquivoImportacao;
    private final JTextField txtArquivoImportacao;
    private final JButton btnSelecionarArquivo;
    private final JLabel lblExportacao;
    private final JLabel lblMensagemImportacao;
    private final JTextField txtArquivoExportacao;
    private final JButton btnExportar;
    private final JLabel lblCodigoHistorico;
    private final JTextField txtCodigoHistorico;
    private final JTextField txtComplemento;
    private final JLabel lblData;
    private final JLabel lblComplemento;
    private final JDateChooser txtData;
    private final JLabel lblMensagemExportacao;
    private final JLabel lblPlanilhas;
    private final JComboBox cbPlanilhas;
    private final JButton btnImportarPlanilha;

    private File arquivo;

    public PrincipalView() {
	setIconImage(Toolkit.getDefaultToolkit().getImage(
		PrincipalView.class.getResource("/imagens/tools.png")));
	setResizable(false);
	setTitle(" Exporta\u00E7\u00E3o Geral para Sac  |  3.0          (Vinicius Lima)");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 500, 469);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JPanel pnImportacao = new JPanel();
	pnImportacao.setBackground(new Color(255, 255, 204));
	pnImportacao.setBounds(0, 0, 494, 200);
	contentPane.add(pnImportacao);
	pnImportacao.setLayout(null);

	lblImportacao = new JLabel("Importa\u00E7\u00E3o");
	lblImportacao.setForeground(new Color(105, 105, 105));
	lblImportacao.setHorizontalAlignment(SwingConstants.CENTER);
	lblImportacao.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblImportacao.setBounds(0, 0, 494, 55);
	pnImportacao.add(lblImportacao);

	lblArquivoImportacao = new JLabel("Nome do Arquivo");
	lblArquivoImportacao.setBounds(10, 73, 100, 14);
	pnImportacao.add(lblArquivoImportacao);

	txtArquivoImportacao = new JTextField();
	txtArquivoImportacao.setEditable(false);
	txtArquivoImportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtArquivoImportacao.setBounds(120, 66, 200, 25);
	pnImportacao.add(txtArquivoImportacao);
	txtArquivoImportacao.setColumns(10);

	btnSelecionarArquivo = new JButton("Selecionar Arquivo");
	btnSelecionarArquivo
		.addActionListener(new BtnSelecionarArquivoAction());
	btnSelecionarArquivo.setBounds(330, 66, 154, 25);
	pnImportacao.add(btnSelecionarArquivo);

	lblMensagemImportacao = new JLabel("Nenhum arquivo importado.");
	lblMensagemImportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblMensagemImportacao.setBounds(10, 145, 474, 25);
	pnImportacao.add(lblMensagemImportacao);

	lblPlanilhas = new JLabel("Planilhas");
	lblPlanilhas.setBounds(10, 113, 100, 14);
	pnImportacao.add(lblPlanilhas);

	cbPlanilhas = new JComboBox();
	cbPlanilhas.setFont(new Font("Tahoma", Font.BOLD, 12));
	cbPlanilhas.setBounds(120, 109, 200, 25);
	pnImportacao.add(cbPlanilhas);

	btnImportarPlanilha = new JButton("Importar Planilha");
	btnImportarPlanilha.addActionListener(new BtnImportarPlanilhaAction());
	btnImportarPlanilha.setBounds(330, 109, 154, 25);
	pnImportacao.add(btnImportarPlanilha);

	pnExportacao = new JPanel();
	pnExportacao.setBackground(new Color(182, 230, 238));
	pnExportacao.setBounds(0, 201, 494, 244);
	contentPane.add(pnExportacao);
	pnExportacao.setLayout(null);

	lblExportacao = new JLabel("Exporta\u00E7\u00E3o");
	lblExportacao.setHorizontalAlignment(SwingConstants.CENTER);
	lblExportacao.setForeground(new Color(105, 105, 105));
	lblExportacao.setFont(new Font("Tahoma", Font.BOLD, 36));
	lblExportacao.setBounds(0, 0, 494, 55);
	pnExportacao.add(lblExportacao);

	JLabel lblArquivoExportacao = new JLabel("Nome do Arquivo");
	lblArquivoExportacao.setBounds(10, 70, 100, 14);
	pnExportacao.add(lblArquivoExportacao);

	txtArquivoExportacao = new JTextField();
	txtArquivoExportacao.setText("c:\\sac\\z00.txt");
	txtArquivoExportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtArquivoExportacao.setColumns(10);
	txtArquivoExportacao.setBounds(120, 63, 234, 25);
	pnExportacao.add(txtArquivoExportacao);

	btnExportar = new JButton("Exportar");
	btnExportar.addActionListener(new BtnExportarActionListener());
	btnExportar.setBounds(364, 63, 100, 25);
	pnExportacao.add(btnExportar);

	txtData = new JDateChooser();
	txtData.setFont(new Font("Tahoma", Font.BOLD, 14));
	txtData.setBounds(120, 99, 140, 25);
	pnExportacao.add(txtData);

	lblData = new JLabel("Data");
	lblData.setBounds(10, 106, 100, 14);
	pnExportacao.add(lblData);

	lblCodigoHistorico = new JLabel();
	lblCodigoHistorico.setText("C\u00F3digo Hist\u00F3rico");
	lblCodigoHistorico.setBounds(10, 142, 100, 14);
	pnExportacao.add(lblCodigoHistorico);

	txtCodigoHistorico = new JTextField();
	txtCodigoHistorico.setDocument(new FixedLengthDocument(3));
	txtCodigoHistorico.setText("500");
	txtCodigoHistorico.setHorizontalAlignment(SwingConstants.CENTER);
	txtCodigoHistorico.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtCodigoHistorico.setBounds(120, 135, 39, 25);
	pnExportacao.add(txtCodigoHistorico);

	lblComplemento = new JLabel();
	lblComplemento.setText("Complemento");
	lblComplemento.setBounds(10, 178, 100, 14);
	pnExportacao.add(lblComplemento);

	txtComplemento = new JTextField();
	txtComplemento.setDocument(new FixedLengthDocument(50));
	txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtComplemento.setBounds(120, 171, 344, 25);
	pnExportacao.add(txtComplemento);

	lblMensagemExportacao = new JLabel();
	lblMensagemExportacao.setText("Nenhum arquivo exportado.");
	lblMensagemExportacao.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblMensagemExportacao.setBounds(10, 207, 454, 17);
	pnExportacao.add(lblMensagemExportacao);

	//
	this.setVisible(true);
	this.setLocationRelativeTo(null);
    }

    private void mensagem(String mensagem) {
	JOptionPane.showMessageDialog(this, mensagem, "Exportação Geral/Sac",
		JOptionPane.PLAIN_MESSAGE);
    }

    private class BtnSelecionarArquivoAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    JFileChooser chooser = new JFileChooser(".");
	    chooser.setDialogTitle("Selecione um arquivo do Excel");
	    chooser.setMultiSelectionEnabled(false);
	    chooser.setFileFilter(new FileNameExtensionFilter("Arquivo XLS",
		    "xls"));

	    int retorno = chooser.showOpenDialog(rootPane);

	    if (retorno == JFileChooser.APPROVE_OPTION) {
		arquivo = chooser.getSelectedFile();
		String nome = chooser.getSelectedFile().getName();

		txtArquivoImportacao.setText(nome);

		cbPlanilhas.removeAllItems();

		Workbook excel;
		try {
		    excel = Workbook.getWorkbook(arquivo);
		    cbPlanilhas.setModel(new DefaultComboBoxModel(excel
			    .getSheetNames()));
		} catch (BiffException | IOException ex) {
		    lblMensagemImportacao
			    .setText("Erro selecionando o arquivo: "
				    + arquivo.getName());
		}

	    }
	}
    }

    private class BtnImportarPlanilhaAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    Importacao importacao;

	    if (cbPlanilhas.getItemCount() == 0) {
		mensagem("Você seve selecionar um arquivo primeiro.");
		return;
	    }

	    String planilha = "";

	    try {
		planilha = String.valueOf(cbPlanilhas.getSelectedItem());

		importacao = new Importacao(arquivo, planilha);
		lblMensagemImportacao.setText(arquivo.getName() + " ("
			+ planilha + ")" + " - importado com sucesso.");
		// txtArquivoExportacao.setText("z"
		// + arquivo.getName().replaceAll("\\.(xls|XLS)$", ".txt")
		// .replace(" ", ""));
	    } catch (Exception ex) {
		lblMensagemImportacao
			.setText("Erro no processamento do arquivo: "
				+ arquivo.getName() + " (" + planilha + ")");
		// txtArquivoExportacao.setText(null);
		mensagem(ex.getMessage());
	    }

	}
    }

    private class BtnExportarActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

	    // processa a saida do arquivo
	    String nome = txtArquivoExportacao.getText().trim();
	    String data = "";

	    if (txtData.getDate() != null)
		data = new SimpleDateFormat("dd/MM/yyyy").format(
			txtData.getDate()).replaceAll("/", "");

	    String historico = txtCodigoHistorico.getText().trim();
	    String complemento = txtComplemento.getText().trim();

	    if (complemento.isEmpty()) {
		complemento = " ";
	    }

	    if (nome.isEmpty())
		mensagem("Nenhum arquivo foi importado.");
	    else if (data.isEmpty()) {
		mensagem("Digite a data corretamente: dd/mm/aaaa.");
		txtData.requestFocus();
	    } else {
		if (historico.isEmpty() || historico.length() != 3) {
		    mensagem("Código de histórico vazio ou errado. (3 numeros)");
		    txtCodigoHistorico.requestFocus();
		} else {
		    try {
			new Exportacao().salvar(nome, data, historico,
				complemento);
			lblMensagemExportacao.setText("Arquivo exportado: "
				+ nome);
		    } catch (Exception ex) {
			mensagem(ex.getMessage());
			txtArquivoExportacao.requestFocus();
		    }
		}
	    }
	}
    }

}
