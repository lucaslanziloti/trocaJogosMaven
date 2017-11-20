/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.CidadeDao;
import br.com.trocaJogos.dao.PropostaTrocaDao;
import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Cidade;
import br.com.trocaJogos.model.Endereco;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.PropostaTroca;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.DistanceMatrixResponse;
import br.com.trocaJogos.util.Element;
import br.com.trocaJogos.util.GeoCodeUtil;
import br.com.trocaJogos.util.ViewUtil;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "procuraParaTrocaMb")
public class ProcuraParaTrocaMb {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private PropostaTrocaDao propostaTrocaDao = new PropostaTrocaDao();
    private CidadeDao cidadeDao = new CidadeDao();

    private Usuario usuario = new Usuario();
    private List<Usuario> usuariosQuePossuemJogo = new ArrayList<>();

    private Jogo jogoSelecionado = new Jogo();
    private Cidade cidade = new Cidade();

    @PostConstruct
    private void init() {
        Object fromSession = ViewUtil.getFromSession("usuarioLogado");

        if (fromSession != null) {
            usuario = (Usuario) fromSession;

            usuario = usuarioDao.carregar(usuario.getId());
        }
    }

    public void buscar(Jogo jogo) {
        usuariosQuePossuemJogo = usuarioDao.buscarUsuarioQuePossui(jogo, usuario, cidade);

        if (usuariosQuePossuemJogo.isEmpty()) {
            ViewUtil.adicionarMensagemDeAlerta("Nenhum usuário possue esse jogo ainda!");
        } else {
            jogoSelecionado = jogo;

            try {
                calculaDistancia();
            } catch (Exception ex) {
                usuario.setDistancia(" - ");
            }
            RequestContext.getCurrentInstance().execute("$('html, body').animate({scrollTop: $('.dataGridBusca').offset().top}, 600);");
        }
    }
    
    public void limpaCidade(){
        cidade = new Cidade();
    }

    public List<Cidade> buscaCidade(String query) {
        return cidadeDao.buscarPorNome(query);
    }

    private void calculaDistancia() throws IOException {
        String latLongOrigem = procuraLatLong(usuario.getEndereco());
        try {
            for (Usuario usr : usuariosQuePossuemJogo) {
                String latLongDestino = procuraLatLong(usr.getEndereco());

                DistanceMatrixResponse calcularDistanciaXML = GeoCodeUtil.calcularDistanciaXML(latLongOrigem, latLongDestino);
                Object row = (Object) calcularDistanciaXML.getRow().get(0);
                Element element = (Element) row;
                usr.setDistancia(element.getDistance().getText());
            }
        } catch (Exception ex) {
            usuario.setDistancia(" - ");
        }
    }

    public String procuraLatLong(Endereco endereco) throws IOException {
        final Geocoder geocoder = new Geocoder();
        String latLongOrigem = "";

        StringBuilder enderecoBuilder = new StringBuilder();
        enderecoBuilder.append(endereco.getLogradouro().getDescricao()).append(", ");
        enderecoBuilder.append(endereco.getNumero()).append(", ");
        enderecoBuilder.append(endereco.getCidade().getDisplay());

        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(enderecoBuilder.toString()).setLanguage("pt").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        if (geocoderResponse != null && !geocoderResponse.getResults().isEmpty()) {
            latLongOrigem = geocoderResponse.getResults().get(0).getGeometry().getLocation().getLat().toString();
            latLongOrigem += "," + geocoderResponse.getResults().get(0).getGeometry().getLocation().getLng().toString();
        }

        return latLongOrigem;
    }

    public void solicitaTroca(Usuario usuarioParaTroca) {
        PropostaTroca propostaTroca = new PropostaTroca();

        propostaTroca.setUsuarioOrigem(usuario);
        propostaTroca.setUsuarioDestino(usuarioParaTroca);
        propostaTroca.setJogo(jogoSelecionado);
        
        if(!propostaTrocaDao.validaTroca(propostaTroca)){
            ViewUtil.adicionarMensagemDeAlerta("Esta troca já foi solicitada!");
            
            return;
        }

        propostaTrocaDao.salvar(propostaTroca);

        ViewUtil.adicionarMensagemDeSucesso("Proposta realizada com sucesso");
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

    public Jogo getJogoSelecionado() {
        return jogoSelecionado;
    }

    public void setJogoSelecionado(Jogo jogoSelecionado) {
        this.jogoSelecionado = jogoSelecionado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
