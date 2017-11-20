package br.com.trocaJogos.model;

/**
 * @author lucas lanziloti
 * @author weverton
 * @author joao lucas
 */
public enum StatusPropostaEnum {

    PROPOSTA_EM_ANALISE("Proposta em Analise"),
    PROPOSTA_EM_RECUSADA("Proposta Recusada"),
    PROPOSTA_ACEITA("Proposta Aceita");

    public String descricao;

    StatusPropostaEnum(String descricao) {
        this.descricao = descricao;
    }

}
