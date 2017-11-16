package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.GeneroDao;
import br.com.trocaJogos.dao.JogoDao;
import br.com.trocaJogos.dao.PlataformaDao;
import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Plataforma;
import br.com.trocaJogos.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "catalogoMb")
public class CatalogoMb {

    private JogoDao jogoDao = new JogoDao();
    private GeneroDao generoDao = new GeneroDao();
    private PlataformaDao plataformaDao = new PlataformaDao();

    private List<Genero> generos = new ArrayList<>();
    private List<Plataforma> plataformas = new ArrayList<>();
    private List<Jogo> jogos = new ArrayList<>();
    
    private Jogo jogoSelecionado = new Jogo();
    private Genero generoSelecionado = new Genero();
    private Plataforma plataformaSelecionado = new Plataforma();
    private String descricao;
    
    @PostConstruct
    private void init() {
        generos = generoDao.listaTodos();
        plataformas = plataformaDao.listaTodos();
    }
    
    public void buscaJogos(){
        jogos = jogoDao.buscaPor(generoSelecionado, plataformaSelecionado, descricao);
        
        if(jogos.isEmpty()){
            ViewUtil.adicionarMensagemDeAlerta("Nenhum jogo encontrado");
        }
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

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Jogo getJogoSelecionado() {
        return jogoSelecionado;
    }

    public void setJogoSelecionado(Jogo jogoSelecionado) {
        this.jogoSelecionado = jogoSelecionado;
    }

    public Genero getGeneroSelecionado() {
        return generoSelecionado;
    }

    public void setGeneroSelecionado(Genero generoSelecionado) {
        this.generoSelecionado = generoSelecionado;
    }

    public Plataforma getPlataformaSelecionado() {
        return plataformaSelecionado;
    }

    public void setPlataformaSelecionado(Plataforma plataformaSelecionado) {
        this.plataformaSelecionado = plataformaSelecionado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
