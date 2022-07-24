import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo{
    
    public List<Conteudo> extrairConteudos(String json){

        // Extrair só os dados que interessam. (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);
        
        //popular a lista
        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String,String> atributos : listaDeAtributos) {
            String urlImagem = atributos.get("url");
            String urlTitle = atributos.get("title");
            Conteudo conteudo = new Conteudo(urlTitle, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
