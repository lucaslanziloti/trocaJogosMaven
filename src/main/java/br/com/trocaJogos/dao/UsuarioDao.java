package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Cidade;
import br.com.trocaJogos.model.Endereco;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.DistanceMatrixResponse;
import br.com.trocaJogos.util.Element;
import br.com.trocaJogos.util.GeoCodeUtil;
import br.com.trocaJogos.util.HibernateUtil;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import java.io.IOException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class UsuarioDao extends GenericDao<Usuario> {

    private HibernateUtil hibernateUtil = new HibernateUtil();
    private JogoDesejadoDao jogoDesejadoDao = new JogoDesejadoDao();
    private JogoDoUsuarioDao jogoDoUsuarioDao = new JogoDoUsuarioDao();

    public UsuarioDao() {
        super(Usuario.class);
    }

    @Override
    public Usuario carregar(Integer id) {
        Usuario usuario = super.carregar(id);

        usuario.setJogosDesejados(jogoDesejadoDao.jogosDo(usuario));
        usuario.setJogosDoUsuario(jogoDoUsuarioDao.jogosDo(usuario));

        return usuario;
    }

    public Usuario logar(String email, String senha) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.email = :email AND u.senha = :senha")
                .setParameter("senha", senha)
                .setParameter("email", email)
                .list();

        if (resultado != null && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return new Usuario();
        }
    }

    public List<Usuario> buscarUsuarioQuePossui(Jogo jogo, Usuario usuario, Cidade cidade) {
        Session session = hibernateUtil.getSession();

        StringBuilder hql = new StringBuilder();
        hql.append("select j.usuario FROM JogoDoUsuario j ");
        hql.append("WHERE j.jogo = :jogo ");
        hql.append("AND j.usuario <> :usuario ");

        if (cidade != null && cidade.getId() != null) {
            hql.append("AND j.usuario.endereco.cidade = :cidade ");
        }

        Query query = session.createQuery(hql.toString())
                .setParameter("jogo", jogo)
                .setParameter("usuario", usuario);

        if (cidade != null && cidade.getId() != null) {
            query.setParameter("cidade", cidade);
        }
        
        return query.list();
    }

    public Boolean verificaCpfExiste(String cpf) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.cpf = :cpf")
                .setParameter("cpf", cpf)
                .list();

        return resultado != null && !resultado.isEmpty();
    }

    public Boolean verificaEmailExiste(String email) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .list();

        return resultado != null && !resultado.isEmpty();
    }

    public void calculaDistancia(List<Usuario> usuarios, Usuario usuarioOrigem) throws IOException {
        String latLongOrigem = procuraLatLong(usuarioOrigem.getEndereco());
        try {
            for (Usuario usr : usuarios) {
                String latLongDestino = procuraLatLong(usr.getEndereco());

                DistanceMatrixResponse calcularDistanciaXML = GeoCodeUtil.calcularDistanciaXML(latLongOrigem, latLongDestino);
                Object row = (Object) calcularDistanciaXML.getRow().get(0);
                Element element = (Element) row;
                usr.setDistancia(element.getDistance().getText());
            }
        } catch (Exception ex) {
            usuarioOrigem.setDistancia(" - ");
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

}
