
import br.com.trocaJogos.dao.GeneroDao;
import br.com.trocaJogos.dao.JogoDao;
import br.com.trocaJogos.dao.PlataformaDao;
import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Plataforma;

/**
 *
 * @author lucas
 */
public class Teste {

    public static void main(String[] args) {
        GeneroDao generoDao = new GeneroDao();
        Genero genero = new Genero();
        genero.setDescricao("Ação");
        generoDao.salvar(genero);

        PlataformaDao plataformaDao = new PlataformaDao();
        Plataforma plataforma = new Plataforma();
        plataforma.setDescricao("PS4");
        plataformaDao.salvar(plataforma);

        JogoDao jogoDao = new JogoDao();
        Jogo jogo = new Jogo();
        jogo.setDescricao("Shadow of Mordor");
        jogo.setGenero(generoDao.carregar(1));
        jogo.setPlataforma(plataformaDao.carregar(1));

        jogoDao.salvar(jogo);
    }
}
