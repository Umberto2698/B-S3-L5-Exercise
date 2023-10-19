package exceptions;

public class NumberLessThanZeroException extends RuntimeException {
    public NumberLessThanZeroException() {
        super("Il numero inserito non può essere utilizzato come grandezza di una lista.");
    }
}
