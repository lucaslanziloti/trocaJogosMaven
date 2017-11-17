package br.com.trocaJogos.util;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -5413369755167243927L;
	private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
