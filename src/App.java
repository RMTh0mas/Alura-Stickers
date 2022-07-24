import java.net.URL;
import java.util.List;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
       
        // Fazer uma conexão HTTP e buscar os top 250 filmes

        //String url = "https://api.nasa.gov/planetary/apod?api_key=SUIKmiwcqVr4ZgMG0vdEzqoS3e4yzrhdAPaSyPK7&start_date=2022-06-12&end_date=2022-06-14";
        String url = "https://api.mocki.io/v2/549a5d8b";

        ClientHttp http = new ClientHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoMocki();
        List<Conteudo> conteudos = extrator.extrairConteudos(json);
        GeradoraDeFigurinhas gerador = new GeradoraDeFigurinhas();

        for (int i = 0; i <  3; i++) {

            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            gerador.cria(inputStream, conteudo.getTitulo().concat(".png"));
            System.out.println(conteudo.getTitulo());
            //System.out.println(filme.get("imDbRating"));
            //Double valor = Double.valueOf(filme.get("imDbRating"));
            //int rating = (int) Math.round((valor*6)/10);
            /*for(int i = 0; i < rating; i++){
                System.out.print("🌟");
            }*/
            System.out.println();
        }

    }
}
