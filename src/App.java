import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.lang.Math;

public class App {
    public static void main(String[] args) throws Exception {
       
        // Fazer uma conexão HTTP e buscar os top 250 filmes

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam. (título, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        var gerador = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            InputStream inputStream = new URL(filme.get("image")).openStream();
            gerador.cria(inputStream, filme.get("title").concat(".png"));
            System.out.println(filme.get("title"));
            System.out.println(filme.get("imDbRating"));
            Double valor = Double.valueOf(filme.get("imDbRating"));
            int rating = (int) Math.round((valor*6)/10);
            for(int i = 0; i < rating; i++){
                System.out.print("🌟");
            }
            System.out.println();
        }

    }
}
