import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Principal {
    public static void main(String[] args) throws Exception {
        // top 250 filmes https://imdb-api.com/en/API/Top250Movies/k_o0rb5eyp
//        String url = "https://imdb-api.com/en/API/Top250Movies/k_o0rb5eyp";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();


//                NASA
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();


//          CRIANDO NOSSA PRÓPRIA API COM SPRING
//        String url = "http://localhost:8081/linguagens";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerado = new GeradoraDeFigurinha();

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);


            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTilulo() + ".png";


            gerado.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTilulo());
            System.out.println();


        }

    }

}


