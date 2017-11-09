package br.com.trocaJogos.controller;

import br.com.trocaJogos.dao.GeneroDao;
import br.com.trocaJogos.dao.JogoDao;
import br.com.trocaJogos.dao.PlataformaDao;
import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Plataforma;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author lucas
 */
@ViewScoped
@ManagedBean(name = "jogoMb")
public class JogoMb {

    private JogoDao jogoDao = new JogoDao();
    private GeneroDao generoDao = new GeneroDao();
    private PlataformaDao plataformaDao = new PlataformaDao();

    private Jogo jogo = new Jogo();
    private List<Genero> generos = new ArrayList<>();
    private List<Plataforma> plataformas = new ArrayList<>();

    private UploadedFile file;
    private File anexo;
    private String base64;
    private String extensao;

    @PostConstruct
    private void init() {
        generos = generoDao.listaTodos();
        plataformas = plataformaDao.listaTodos();
    }

    public void salvar() throws IOException {
        if (anexo == null) {
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Adicione uma imagem!"));

            return;
        }

        jogo.setImg(Base64.getEncoder().encodeToString(Files.readAllBytes(anexo.toPath())));
        if (jogo.getId() == null) {
            jogoDao.salvar(jogo);
        } else {
            jogoDao.alterar(jogo);
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
        jogo = new Jogo();
        anexo = null;
        base64 = null;
        extensao = null;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
