package FavoritePlaces.FavoritePlaces.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(ViaCepService.class);

    public ViaCepService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ObjectNode getZipCodeData(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        String result = restTemplate.getForObject(url, String.class);
        return validateCepResponse(result);
    }

    @SuppressWarnings("deprecation")
    private ObjectNode validateCepResponse(String result) {
        ObjectNode responseJson = objectMapper.createObjectNode();


        try {
            JsonNode jsonResponse = objectMapper.readTree(result);

            if (jsonResponse.has("erro") && jsonResponse.get("erro").asBoolean()) {
                logger.warn("CEP malformado ou não existe: {}", result);
                responseJson.put("data", (JsonNode) null);
                responseJson.put("error", "CEP malformado ou não existe.");
                return responseJson;
            }

            String[] requiredFields = { "cep", "logradouro", "complemento", "bairro", "localidade", "uf", "estado",
                    "regiao", "ibge", "ddd", "siafi" };

            for (String field : requiredFields) {
                if (!jsonResponse.has(field)) {
                    logger.warn("Campo ausente no retorno do CEP: {}. Faltando campo: {}", result, field);
                    responseJson.put("data", (JsonNode) null);
                    responseJson.put("error", "Dados incompletos no retorno do CEP. Faltando campo: " + field);
                    return responseJson;
                }
            }

            responseJson.set("data", jsonResponse);
            responseJson.put("error", "");
            logger.info("Resposta do CEP válida: {}", result);

        } catch (Exception e) {
            logger.error("Erro ao processar a resposta do CEP: {}", e.getMessage());
            responseJson.put("data", (JsonNode) null);
            responseJson.put("error", "Erro ao processar a resposta do CEP.");
        }

        return responseJson;
    }
}
