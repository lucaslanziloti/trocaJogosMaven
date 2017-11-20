package br.com.trocaJogos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author lucas lanziloti
 * @author weverton
 * @author joao lucas
 */
@Entity
@Table(name = "ptr_proposta_troca")
public class PropostaTroca implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ptr_id", nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "usr_origem_id", referencedColumnName = "usr_id")
    private Usuario usuarioOrigem = new Usuario();

    @ManyToOne()
    @JoinColumn(name = "usr_destino_id", referencedColumnName = "usr_id")
    private Usuario usuarioDestino = new Usuario();

    @ManyToOne()
    @JoinColumn(name = "jgo_id", referencedColumnName = "jgo_id")
    private Jogo jogo = new Jogo();

    @Enumerated(EnumType.STRING)
    @Column(name = "ptr_status", nullable = false)
    private StatusPropostaEnum status = StatusPropostaEnum.PROPOSTA_EM_ANALISE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public StatusPropostaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPropostaEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PropostaTroca other = (PropostaTroca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
