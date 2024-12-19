package FavoritePlaces.FavoritePlaces.service;

import FavoritePlaces.FavoritePlaces.model.FavoriteZipCode;
import FavoritePlaces.FavoritePlaces.model.LogData;
import FavoritePlaces.FavoritePlaces.repository.FavoriteZipCodeRepository;
import FavoritePlaces.FavoritePlaces.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogDataService {

	private final LogRepository logRepository;
	private final FavoriteZipCodeRepository favoriteZipCodeRepository;
	private static final Logger logger = LoggerFactory.getLogger(LogDataService.class); // Create the logger

	public LogDataService(LogRepository logRepository, FavoriteZipCodeRepository favoriteZipCodeRepository) {
		this.logRepository = logRepository;
		this.favoriteZipCodeRepository = favoriteZipCodeRepository;
	}

	public void addLog(FavoriteZipCode favoriteZipCode) {
		if (favoriteZipCode == null) {
			logger.error("FavoriteZipCode cannot be null");
			return;
		}
		LogData logData = new LogData();
		logData.setFavoriteZipCode(favoriteZipCode);

		logData = logRepository.save(logData);

		logger.info("New log entry created: {}", logData);

		logger.info("Log entry details: ID = {}, ZIP Code = {}, Date = {}",
				logData.getId(),
				logData.getFavoriteZipCode().getZipCode(),
				logData.getDate());
	}

}
