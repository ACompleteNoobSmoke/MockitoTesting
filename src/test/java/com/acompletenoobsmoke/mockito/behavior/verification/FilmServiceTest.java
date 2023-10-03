package com.acompletenoobsmoke.mockito.behavior.verification;

import com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film.Film;
import com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film.FilmRepository;
import com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film.FilmRequest;
import com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService underTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFilm() {
        Film newFilm = new Film("12", "The Holy Man", 550, LocalDate.now());
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);

        underTest.addFilm(newFilm);
        verify(filmRepository).save(filmArgumentCaptor.capture());

        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().isEqualTo(newFilm);
    }

    @Test
    public void testAddFilm2() {
        Film newFilm = new Film("11", "Audition", 220, LocalDate.now());
        underTest.addFilm(newFilm);
        then(filmRepository).should(never()).save(newFilm);
    }

    @Test
    public void testAddFilmRequest() {
        Film newFilm = new Film("12", "Alien Resurrection", 600, LocalDate.now());
        FilmRequest request = new FilmRequest(newFilm.getTitle(), newFilm.getPrice(), newFilm.getReleaseDate());
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);

        underTest.addFilmRequest(request);
        verify(filmRepository).save(filmArgumentCaptor.capture());

        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().ignoringFields("filmID").isEqualTo(newFilm);
    }

    @Test
    public void testAddFilmRequest2() {
        Film newFilm = new Film("1", "Insidious", 300, LocalDate.now());
        FilmRequest request = new FilmRequest(newFilm.getTitle(), newFilm.getPrice(), newFilm.getReleaseDate());
        underTest.addFilmRequest(request);
        verify(filmRepository, never()).save(any(Film.class));
    }

    @Test
    public void testUpdatePrice() {
        int newPrice = 899;
        String filmID = "1";
        Film newFilm = new Film(filmID, "The Grudge", 510, LocalDate.now());
        Film updatedFilm = new Film(filmID, "The Grudge", newPrice, LocalDate.now());

        when(filmRepository.findFilmByID(filmID)).thenReturn(newFilm);
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        underTest.updatePrice(filmID, newPrice);

        verify(filmRepository).save(filmArgumentCaptor.capture());
        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().isEqualTo(updatedFilm);
    }

    @Test
    public void testUpdatePrice2() {
        underTest.updatePrice(null, 8000);
        verifyNoInteractions(filmRepository);
    }

    @Test
    public void testUpdatePrice3() {
        String filmID = "2";
        int newPrice = 300;
        Film newFilm = new Film(filmID, "The Ring", newPrice, LocalDate.now());
        when(filmRepository.findFilmByID(filmID)).thenReturn(newFilm);
        underTest.updatePrice(filmID, newPrice);
        verify(filmRepository, times(1)).findFilmByID(filmID);
        verifyNoMoreInteractions(filmRepository);
    }

    @Test
    public void testUpdatePrice4() {
        String filmID = "5";
        int newPrice = 700;
        Film newFilm = new Film(filmID, "The Thing", 600, LocalDate.now());
        when(filmRepository.findFilmByID(filmID)).thenReturn(newFilm);

        underTest.updatePrice(filmID, newPrice);
        InOrder inOrder = Mockito.inOrder(filmRepository);
        inOrder.verify(filmRepository).findFilmByID(filmID);
        inOrder.verify(filmRepository).save(newFilm);
    }
}
