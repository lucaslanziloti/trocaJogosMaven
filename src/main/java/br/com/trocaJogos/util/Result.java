package br.com.trocaJogos.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author lucas
 */
@XmlRootElement
public class Result implements Serializable {

    private static final long serialVersionUID = -1585721707328817690L;
    private Geometry geometry;
    private String formattedAddress;
    private AddressComponent addressComponent;
    private String type;

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
