package FavoritePlaces.FavoritePlaces.controller;
import java.util.List;
import FavoritePlaces.FavoritePlaces.service.FavoriteZipCodeService;
import FavoritePlaces.FavoritePlaces.service.ViaCepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import FavoritePlaces.FavoritePlaces.model.FavoriteZipCode;

@RestController
@RequestMapping("/api/cep")
public class FavoriteZipCodeController {
	private final FavoriteZipCodeService zipCodeService;
	private final ViaCepService viaCepService;
	private static final Logger logger = LoggerFactory.getLogger(FavoriteZipCodeController.class);
	public FavoriteZipCodeController(FavoriteZipCodeService zipCodeService, ViaCepService viaCepService) {
		this.zipCodeService = zipCodeService;
		this.viaCepService = viaCepService;
	}

	@GetMapping("/favorites")
	public List<FavoriteZipCode> getFavoriteZipCodes() {
		return zipCodeService.getAllFavoriteZipCodes();
	}

	@PostMapping("/favorite")
	public ResponseEntity<ObjectNode> addFavoriteZipCode(@RequestParam String zipCode) {
		ObjectNode responseJson = viaCepService.getZipCodeData(zipCode);

		if (responseJson.has("error") && !responseJson.get("error").asText().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		}

		if (zipCodeService.alreadyExists(zipCode)) {
			ObjectNode errorResponse = responseJson.objectNode();
			errorResponse.put("data", "");
			errorResponse.put("error", "The ZIP code already exists.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		zipCodeService.addFavoriteZipCode(zipCode);
		logger.info("CEP adicionado com sucesso.");
		ObjectNode successResponse = responseJson.objectNode();
		successResponse.set("data", responseJson.get("data"));
		successResponse.put("error", "");
		return ResponseEntity.ok(successResponse);
	}

}
