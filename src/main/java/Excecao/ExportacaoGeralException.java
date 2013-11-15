package Excecao;

public class ExportacaoGeralException extends Exception {
    public ExportacaoGeralException() {
    }

    public ExportacaoGeralException(String msg) {
	super(msg);
    }

    public ExportacaoGeralException(Throwable cause) {
	super(cause);
    }

    public ExportacaoGeralException(String msg, Throwable cause) {
	super(msg, cause);
    }

}
