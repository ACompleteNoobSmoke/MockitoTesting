package com.acompletenoobsmoke.mockito.test_doubles.dummy;

public class DummyEmailService implements EmailService {

    @Override
    public void sendMessage(String message) {
        throw new AssertionError("Method Not Implemented");
    }
}
