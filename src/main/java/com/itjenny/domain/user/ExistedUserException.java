package com.itjenny.domain.user;

public class ExistedUserException extends Exception {
    private static final long serialVersionUID = -1142031579014201640L;

    public ExistedUserException() {
        super();
    }

    public ExistedUserException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ExistedUserException(String arg0) {
        super(arg0);
    }

    public ExistedUserException(Throwable arg0) {
        super(arg0);
    }
}
