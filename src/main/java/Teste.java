
import br.com.trocaJogos.dao.CidadeDao;
import br.com.trocaJogos.dao.EnderecoDao;
import br.com.trocaJogos.dao.LogradouroDao;
import br.com.trocaJogos.model.Endereco;

/**
 *
 * @author lucas
 */
public class Teste {
    
    public static void main(String[] args) {
        LogradouroDao logradouroDao = new LogradouroDao();
//        
//        Logradouro logradouro = new Logradouro();
//        
//        logradouro.setDescricao("Rua das Couves");
//        
////        dao.salvar(logradouro);
////        
        CidadeDao cidadeDao = new CidadeDao();
//        
//        Cidade cidade = new Cidade();
//        
//        cidade.setDescricao("São José dos Campos");
//        
//        dao.salvar(cidade);
//        
        EnderecoDao enderecoDao = new EnderecoDao();
        
        Endereco endereco = new Endereco();
        
        endereco.setNumero(300);
        endereco.setPontoReferencia("Na esquina da padaria PÃOZÃO");
        
        enderecoDao.salvar(endereco);
    }
}
