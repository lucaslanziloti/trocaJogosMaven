package br.com.trocaJogos.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lucas
 */
@XmlRootElement
public class GeocodeResponse implements Serializable {

	private static final long serialVersionUID = -902627865543952673L;
	private String status;
    private Result result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
