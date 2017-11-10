package br.com.trocaJogos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lucas
 */
@Entity
@Table(name = "cid_cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 8594792741868247365L;

    @Id
    @GeneratedValue
    @Column(name = "cid_id", nullable = false)
    private Integer id;
    
    @Column(name = "cid_descricao", nullable = false)
    private String descricao;
    
    @Column(name = "cid_uf", nullable = true)
    private String uf;

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getDisplay(){
        if(this.id != null){
            return this.descricao + " - " + this.uf;
        }
        
        return "";
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
