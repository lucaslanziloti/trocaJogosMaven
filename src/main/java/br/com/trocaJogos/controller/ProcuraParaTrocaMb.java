/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Endereco;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.DistanceMatrixResponse;
import br.com.trocaJogos.util.Element;
import br.com.trocaJogos.util.GeoCodeUtil;
import static br.com.trocaJogos.util.GeoCodeUtil.calcularDistanciaXML;
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

    public void buscar(Jogo jogo) {
        usuariosQuePossuemJogo = usuarioDao.buscarUsuarioQuePossui(jogo, usuario);

        if (usuariosQuePossuemJogo.isEmpty()) {
            ViewUtil.adicionarMensagemDeAlerta("Nenhum usuário possue esse jogo ainda!");
        } else {
            try {
                calculaDistancia();
            } catch (Exception ex) {
                usuario.setDistancia(" - ");
            }
            RequestContext.getCurrentInstance().execute("$('html, body').animate({scrollTop: $('.dataGridBusca').offset().top}, 600);");
        }
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
