package com.bifromq.mqtt.core.filter;

public class SQLException extends RuntimeException {

    public SQLException() {
        super();
    }

    public SQLException(String s) {
        super(s);
    }

    public SQLException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SQLException(Throwable throwable) {
        super(throwable);
    }
}