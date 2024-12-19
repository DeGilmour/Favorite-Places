package FavoritePlaces.FavoritePlaces.repository;
import FavoritePlaces.FavoritePlaces.model.FavoriteZipCode;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteZipCodeRepository extends JpaRepository<FavoriteZipCode, String> {

   Optional<FavoriteZipCode> findByZipCode(String zipCode);

   boolean existsByZipCode(String zipCode);
}
