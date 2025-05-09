import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaxaConversao {

    public JsonStructure searchApi(String pair1, String pair2, double amount) {

        String apiK = "Coloque_Sua_Chave_Aqui";

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/"+apiK+"/pair/"+pair1+"/"+pair2);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), JsonStructure.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
