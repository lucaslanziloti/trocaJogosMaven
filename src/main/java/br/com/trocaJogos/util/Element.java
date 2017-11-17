package br.com.trocaJogos.util;


public class Element {

    private String status;

    private Duration duration = new Duration();

    private Distance distance = new Distance();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return duration.toString() +" "+ distance.toString();
    }
    
}
