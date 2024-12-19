package FavoritePlaces.FavoritePlaces.service;

import FavoritePlaces.FavoritePlaces.model.FavoriteZipCode;
import FavoritePlaces.FavoritePlaces.model.LogData;
import FavoritePlaces.FavoritePlaces.repository.FavoriteZipCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteZipCodeService {

    private final FavoriteZipCodeRepository favoriteZipCodeRepository;
    private final LogDataService logDataService;

    public FavoriteZipCodeService(FavoriteZipCodeRepository favoriteZipCodeRepository, LogDataService logDataService) {
        this.favoriteZipCodeRepository = favoriteZipCodeRepository;
        this.logDataService = logDataService;
    }

    public FavoriteZipCode addFavoriteZipCode(String zipCode) {
        FavoriteZipCode favoriteZipCode = new FavoriteZipCode(zipCode);

        favoriteZipCode = favoriteZipCodeRepository.save(favoriteZipCode);
        
        logDataService.addLog(favoriteZipCode);
        
        return favoriteZipCode;
    }

    public List<FavoriteZipCode> getAllFavoriteZipCodes() {
        return favoriteZipCodeRepository.findAll();
    }

    public boolean alreadyExists(String cep) {
        return favoriteZipCodeRepository.existsByZipCode(cep);
    }
}
