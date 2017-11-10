package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.ViewUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author lucas
 */
@SessionScoped
@ManagedBean(name = "loginMb")
public class LoginMb {

    private UsuarioDao usuarioDao = new UsuarioDao();

    private Usuario usuarioLogado = new Usuario();
    private String email = "";
    private String senha = "";

    @PostConstruct
    private void init() {
    }

    public void logar() {
        Usuario usuario = usuarioDao.logar(email, senha);

        if (usuario == null || usuario.getId() == null) {
            ViewUtil.adicionarMensagemDeErro("Email e senha incorretos!");
            sair();
        } else {
            usuarioLogado = usuario;

            email = "";
            senha = "";

            ViewUtil.adicionarMensagemDeSucesso("Logado com Sucesso!");

            ViewUtil.redirecionar("/index.faces");
        }
    }

    public void sair() {
        usuarioLogado = new Usuario();
        email = "";
        senha = "";

        ViewUtil.redirecionar("/index.faces");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
