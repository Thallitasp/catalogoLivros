package exception;

public class LivroJaExisteException extends Exception {
    public LivroJaExisteException(String mensagem) {
        super(mensagem);
    }
}
