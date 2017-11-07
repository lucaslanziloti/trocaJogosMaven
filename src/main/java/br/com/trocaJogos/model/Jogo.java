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
 * @author lucas
 */
@Entity
@Table(name = "jgo_jogo")
public class Jogo implements Serializable {

    private static final long serialVersionUID = 8594792741868247365L;

    @Id
    @GeneratedValue
    @Column(name = "jgo_id", nullable = false)
    private Integer id;
    
    @Column(name = "jgo_descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "gnr_id", referencedColumnName = "gnr_id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "plt_id", referencedColumnName = "plt_id")
    private Plataforma plataforma;
    
    @Column(name = "jgo_img", columnDefinition = "TEXT")
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Jogo other = (Jogo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
