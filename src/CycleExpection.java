public class CycleExpection extends Exception {

    public CycleExpection() {
    }

    public CycleExpection(String message) {
        super("Detectado no grafo a presença de ciclo a partir do Nodo: " + message);
    }

    public CycleExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public CycleExpection(Throwable cause) {
        super(cause);
    }

    public CycleExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
