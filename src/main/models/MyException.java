package main.models;

/**
 * Created by User on 26.04.2017.
 */
public class MyException extends Exception {
    static String message;

    public MyException(String message) {
        this.message = message;
    }
}
