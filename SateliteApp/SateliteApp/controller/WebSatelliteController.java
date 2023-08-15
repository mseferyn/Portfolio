package pl.ug.SateliteApp.SateliteApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ug.SateliteApp.SateliteApp.service.SatelliteService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Controller
public class WebSatelliteController {

    private final SatelliteService satelliteService;
    HttpURLConnection connection;
    URL url;
    BufferedReader reader;
    String line;
    StringBuffer responseContent;
    //format for queries: https://celestrak.com/NORAD/elements/gp.php?{QUERY}=VALUE[&FORMAT=VALUE]

    public WebSatelliteController(@Qualifier("SatelliteService") SatelliteService satelliteService) throws MalformedURLException {
        this.satelliteService = satelliteService;
        responseContent = new StringBuffer();
        url = new URL("https://jsonplaceholder.typicode.com/posts");
//        url = new URL("https://www.space-track.org/");
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            establishConnection();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    public void establishConnection() throws ProtocolException {
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try {
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null)
                responseContent.append(line);
            reader.close();
//            System.out.println(responseContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
