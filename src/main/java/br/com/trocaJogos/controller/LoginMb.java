package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.ViewUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
        Usuario usuario = usuarioDao.logar(email.trim(), senha.trim());

        if (usuario == null || usuario.getId() == null) {
            ViewUtil.adicionarMensagemDeErro("Email e senha incorretos!");

            usuarioLogado = new Usuario();
            email = "";
            senha = "";
        } else {
            usuarioLogado = usuario;

            email = "";
            senha = "";

            ViewUtil.adicionarMensagemDeSucesso("Logado com Sucesso!");

            ViewUtil.setInSession("usuarioLogado", usuarioLogado);

            ViewUtil.redirecionar("/index.faces");
        }
    }

    public void sair() {
        usuarioLogado = new Usuario();
        email = "";
        senha = "";

        ViewUtil.removeFromSession("usuarioLogado");

        ViewUtil.redirecionar("/index.faces");
    }

    public Boolean getRoot() {
        return usuarioLogado != null
                && usuarioLogado.getId() != null
                && usuarioLogado.getRoot();
    }

    public Boolean getEstaLogado() {
        return usuarioLogado != null && usuarioLogado.getId() != null;
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
