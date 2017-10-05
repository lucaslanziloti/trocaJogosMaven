
package br.com.trocaJogos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author lucas lanziloti
 * @author weverton
 * @author joao lucas
 */
@Entity
@Table(name = "usr_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1580056085369485374L;
    
    @Id
    @GeneratedValue
    @Column(name = "usr_id", nullable = false)
    private Integer id;

    @Column(name = "usr_nome", nullable = false)
    private String nome;

    @Column(name = "usr_data_nasc")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "usr_cpf", nullable = false)
    private String cpf;

    @Column(name = "usr_rg", nullable = false)
    private String rg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
