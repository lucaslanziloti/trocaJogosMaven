package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.UsuarioDao;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.ViewUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "cadastroUsuarioMb")
public class CadastroUsuarioMb {

    private UsuarioDao usuarioDao = new UsuarioDao();

    private Usuario usuario = new Usuario();

    private UploadedFile file;
    private File anexo;
    private String base64;
    private String extensao;

    @PostConstruct
    private void init() {
    }

    public void salvar() throws IOException {
        try {
            if (anexo != null && anexo.toPath() != null) {
                usuario.setImg(Base64.getEncoder().encodeToString(Files.readAllBytes(anexo.toPath())));
                usuario.setExtensao(FilenameUtils.getExtension(anexo.getName()));
            }

            if (usuario.getId() == null) {
                usuarioDao.salvar(usuario);
            } else {
                usuarioDao.alterar(usuario);
            }
            
            ViewUtil.adicionarMensagemDeSucesso("Salvo com Sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            ViewUtil.adicionarMensagemDeSucesso("Ocorreu um erro");
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            file = event.getFile();
            anexo = new File("/tmp/" + file.getFileName());
            FileUtils.copyInputStreamToFile(file.getInputstream(), anexo);
            base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(anexo.toPath()));
            extensao = FilenameUtils.getExtension(anexo.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String pathImg() {
        if (anexo != null && anexo.toPath() != null) {
            return anexo.toPath().toString();
        }
        return "";
    }

    public void cancelar() {
        usuario = new Usuario();
        anexo = null;
        base64 = null;
        extensao = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public File getAnexo() {
        return anexo;
    }

    public void setAnexo(File anexo) {
        this.anexo = anexo;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

}
