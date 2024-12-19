package FavoritePlaces.FavoritePlaces.controller;

import FavoritePlaces.FavoritePlaces.service.ViaCepService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Random;

@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {

	private final ViaCepService viaCepService;

	public ZipCodeController(ViaCepService viaCepService) {
		this.viaCepService = viaCepService;
	}

	@GetMapping("/{cep}")
	public ResponseEntity<ObjectNode> getZipCodeData(@PathVariable String cep) {
		String cleanedCep = cep.replace("-", "");
		ObjectNode result = viaCepService.getZipCodeData(cleanedCep);

		if (result.has("error") && !result.get("error").asText().isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}

		return ResponseEntity.ok(result);
	}

	@GetMapping("/generateRandom")
	public String generateRandomZipCode() {
		return generateRandomCep();
	}

	// random ceps
	private String generateRandomCep() {
		Random random = new Random();

		int firstPart = random.nextInt(90000) + 10000; // 10000-99999
		int secondPart = random.nextInt(900) + 100; // 100-999

		return String.format("%05d-%03d", firstPart, secondPart);
	}
}
