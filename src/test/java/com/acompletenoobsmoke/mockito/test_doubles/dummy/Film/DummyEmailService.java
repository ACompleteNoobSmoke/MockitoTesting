package com.acompletenoobsmoke.mockito.test_doubles.dummy.Film;


public class DummyEmailService implements FilmEmailService {

    @Override
    public void sendEmail(String message) {
        throw new AssertionError("Method not implemented!!");
    }
}
