package FavoritePlaces.FavoritePlaces.repository;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FavoritePlaces.FavoritePlaces.model.LogData;

@Repository
public interface LogRepository extends JpaRepository<LogData, Id> {
}
