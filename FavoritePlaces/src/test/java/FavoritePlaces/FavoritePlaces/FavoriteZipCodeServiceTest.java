package FavoritePlaces.FavoritePlaces;

import FavoritePlaces.FavoritePlaces.model.FavoriteZipCode;
import FavoritePlaces.FavoritePlaces.repository.FavoriteZipCodeRepository;
import FavoritePlaces.FavoritePlaces.service.FavoriteZipCodeService;
import FavoritePlaces.FavoritePlaces.service.LogDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FavoriteZipCodeServiceTest {

    private FavoriteZipCodeRepository favoriteZipCodeRepository;
    private LogDataService logDataService;
    private FavoriteZipCodeService favoriteZipCodeService;

    @BeforeEach
    void setUp() {
        favoriteZipCodeRepository = mock(FavoriteZipCodeRepository.class);
        logDataService = mock(LogDataService.class);
        favoriteZipCodeService = new FavoriteZipCodeService(favoriteZipCodeRepository, logDataService);
    }

    @Test
    void testAddFavoriteZipCode() {
        String zipCode = "12345";
        FavoriteZipCode favoriteZipCode = new FavoriteZipCode(zipCode);

        when(favoriteZipCodeRepository.save(any(FavoriteZipCode.class))).thenReturn(favoriteZipCode);

        FavoriteZipCode result = favoriteZipCodeService.addFavoriteZipCode(zipCode);

        System.out.println("Added FavoriteZipCode: " + result);

        verify(favoriteZipCodeRepository, times(1)).save(any(FavoriteZipCode.class));
        verify(logDataService, times(1)).addLog(favoriteZipCode);
    }

    @Test
    void testGetAllFavoriteZipCodes() {
        List<FavoriteZipCode> mockZipCodes = Arrays.asList(
                new FavoriteZipCode("12345"),
                new FavoriteZipCode("67890"));

        when(favoriteZipCodeRepository.findAll()).thenReturn(mockZipCodes);

        List<FavoriteZipCode> result = favoriteZipCodeService.getAllFavoriteZipCodes();

        System.out.println("All FavoriteZipCodes: " + result);

        assertEquals(2, result.size());
        verify(favoriteZipCodeRepository, times(1)).findAll();
    }

    @Test
    void testAlreadyExists() {
        String zipCode = "12345";

        when(favoriteZipCodeRepository.existsByZipCode(zipCode)).thenReturn(true);

        boolean result = favoriteZipCodeService.alreadyExists(zipCode);

        System.out.println("Already Exists for ZIP Code " + zipCode + ": " + result);

        assertTrue(result);
        verify(favoriteZipCodeRepository, times(1)).existsByZipCode(zipCode);
    }
}
