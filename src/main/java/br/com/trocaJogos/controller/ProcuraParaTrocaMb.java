/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "procuraParaTrocaMb")
public class ProcuraParaTrocaMb {
    
    private UsuarioDao usuarioDao = new UsuarioDao();

    private Usuario usuario = new Usuario();
    List<Usuario> usuariosQuePossuemJogo = new ArrayList<>();
    
    @PostConstruct
    private void init() {
        Object fromSession = ViewUtil.getFromSession("usuarioLogado");

        if (fromSession != null) {
            usuario = (Usuario) fromSession;
            
            usuario = usuarioDao.carregar(usuario.getId());
        }
    }
    
    public void buscar(Jogo jogo){
        usuariosQuePossuemJogo = usuarioDao.buscarUsuarioQuePossui(jogo, usuario);
        
        if(usuariosQuePossuemJogo.isEmpty()){
            ViewUtil.adicionarMensagemDeAlerta("Nenhum usuário possue esse jogo ainda!");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuariosQuePossuemJogo() {
        return usuariosQuePossuemJogo;
    }

    public void setUsuariosQuePossuemJogo(List<Usuario> usuariosQuePossuemJogo) {
        this.usuariosQuePossuemJogo = usuariosQuePossuemJogo;
    }
}
