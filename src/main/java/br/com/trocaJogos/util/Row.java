package br.com.trocaJogos.util;

public class Row {

    private Element element = new Element();

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }  

    @Override
    public String toString() {
        return element.toString();
    }
}
