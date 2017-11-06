package br.com.trocaJogos.mb;

import br.com.trocaJogos.dao.JogoDao;
import br.com.trocaJogos.model.Jogo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "jogoMb")
public class JogoMb {

    private Jogo jogo = new Jogo();
    private JogoDao jogoDao = new JogoDao();

    public void salvar(){
        if(jogo.getId() == null){
            jogoDao.salvar(jogo);
        }else{
            jogoDao.alterar(jogo);
        }
    }
    
    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}
