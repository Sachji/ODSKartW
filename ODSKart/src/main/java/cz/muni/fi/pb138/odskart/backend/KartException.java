package cz.muni.fi.pb138.odskart.backend;

/**
 * A class describing KartException
 *
 * @author Jiří Šácha
 */
public class KartException extends Exception {

    /**
     * Creates a new instance of <code>KartException</code> without detail
     * message.
     */
    public KartException() {
    }

    /**
     * Constructs an instance of <code>KartException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public KartException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>KartException</code> with the specified
     * detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public KartException(String message, Throwable cause) {
        super(message, cause);
    }

}
