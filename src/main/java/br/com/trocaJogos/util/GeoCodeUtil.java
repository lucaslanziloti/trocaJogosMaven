package br.com.trocaJogos.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class GeoCodeUtil {
    //driving
    //walking
    //bicycling
    //transit

    public static DistanceMatrixResponse calcularDistanciaXML(String origins, String destinations) {
        try {
            String key = "AIzaSyDcBPYY9Ip0wdwP6nx1fEusn9aYNDRPDqo";
            
//            https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=-23.1760853,-45.81955749999999&destinations=-23.1760853,-45.81955749999999&key=AIzaSyDcBPYY9Ip0wdwP6nx1fEusn9aYNDRPDqo

            StringBuilder stringUrl = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/xml?units=metric&origins=");
            stringUrl.append(URLEncoder.encode(origins, "UTF-8"));
            stringUrl.append("&destinations=");
            stringUrl.append(URLEncoder.encode(destinations, "UTF-8"));
            stringUrl.append("&mode=walking&language=pt-BR&sensor=false");
            stringUrl.append("&key=").append(key);

            URL url = new URL(stringUrl.toString());

            InputStreamReader inputReader = new InputStreamReader(url.openStream());
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String linha;
            String xml = "";
            while ((linha = bufferedReader.readLine()) != null) {
                xml += linha;
            }

            XStream stream = new XStream(new DomDriver());
            stream.alias("DistanceMatrixResponse", DistanceMatrixResponse.class);
            stream.alias("row", Row.class);
            stream.alias("element", Element.class);
            stream.alias("duration", Duration.class);
            stream.alias("distance", Distance.class);
            DistanceMatrixResponse distanceMatrixResponse = (DistanceMatrixResponse) stream.fromXML(xml);

            return distanceMatrixResponse;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
