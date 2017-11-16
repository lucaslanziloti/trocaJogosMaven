
package br.com.trocaJogos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "jdu_jogo_do_usuario")
public class JogoDoUsuario implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "jdu_id", nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    private Usuario usuario = new Usuario();

    @ManyToOne()
    @JoinColumn(name = "jgo_id", referencedColumnName = "jgo_id")
    private Jogo jogo = new Jogo();

    public JogoDoUsuario() {
    }

    public JogoDoUsuario(Jogo jogo, Usuario usuario) {
        this.jogo = jogo;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final JogoDoUsuario other = (JogoDoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
