package br.com.trocaJogos.mb;

import br.com.trocaJogos.dao.GeneroDao;
import br.com.trocaJogos.dao.JogoDao;
import br.com.trocaJogos.dao.PlataformaDao;
import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Plataforma;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "jogoMb")
public class JogoMb {

    private JogoDao jogoDao = new JogoDao();
    private GeneroDao generoDao = new GeneroDao();
    private PlataformaDao plataformaDao = new PlataformaDao();
    
    private Jogo jogo = new Jogo();
    private List<Genero> generos = new ArrayList<>();
    private List<Plataforma> plataformas = new ArrayList<>();
    
    @PostConstruct
    private void init() {
        generos = generoDao.listaTodos();
        plataformas = plataformaDao.listaTodos();
    }

    public void salvar(){
        if(jogo.getId() == null){
            jogoDao.salvar(jogo);
        }else{
            jogoDao.alterar(jogo);
        }
    }
    
    public void cancelar(){
        jogo = new Jogo();
    }
    
    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }
}
