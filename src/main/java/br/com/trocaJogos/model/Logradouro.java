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
@Table(name = "lgr_logradouro")
public class Logradouro implements Serializable {

    private static final long serialVersionUID = 246345459594126433L;
    
    @Id
    @GeneratedValue
    @Column(name = "lgr_id", nullable = false)
    private Integer id;
    
    @Column(name = "lgr_descricao", nullable = false)
    private String descricao;
    
    @Column(name = "lgr_cep", nullable = false)
    private String cep;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
        final Logradouro other = (Logradouro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
