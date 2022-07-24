import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas{

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        // leitura da imagem
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal =  ImageIO.read(inputStream);

        // cria nova imagem em memória com transparencia e com novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura +200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a img original para novo imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(fonte);
        graphics.setColor(Color.ORANGE);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura-50);

        // escrever a nova imagem
        ImageIO.write(novaImagem, "png", new File("C:/Users/renan.thomaz/OneDrive/alura-stickers/alura-stickers/saida/".concat(nomeArquivo)));
    }
}