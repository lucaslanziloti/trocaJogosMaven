package br.com.trocaJogos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lucas
 */
@Entity
@Table(name = "end_endereco")
public class Endereco {
    
    @Id
    @GeneratedValue
    @Column(name = "end_id", nullable = false)
    private Integer id;

}
