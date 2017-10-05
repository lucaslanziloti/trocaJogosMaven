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
@Table(name = "end_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 246345459594126433L;
    
    @Id
    @GeneratedValue
    @Column(name = "end_id", nullable = false)
    private Integer id;
    
    @Column(name = "end_numero", nullable = false)
    private Integer numero;

    @Column(name = "end_ponto_referencia", nullable = false)
    private String pontoReferencia;

    @ManyToOne
    @JoinColumn(name = "lgr_id", referencedColumnName = "lgr_id")
    private Logradouro logradouro;

    @ManyToOne
    @JoinColumn(name = "cid_id", referencedColumnName = "cid_id")
    private Cidade cidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
