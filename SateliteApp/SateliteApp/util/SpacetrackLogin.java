package pl.ug.SateliteApp.SateliteApp.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;

public class SpacetrackLogin {

    //TODO lockfile to limit queries per hour
    private File lockfile;
    public SpacetrackLogin() {}

    public void updateDB() {

        try {
            String baseURL = "https://www.space-track.org";
            String authPath = "/ajaxauth/login";
            String userName = "tomasz.rudnicki@onet.pl";
            String password = "UGsatelites2021";
            String query = "https://www.space-track.org/basicspacedata/query/class/boxscore/orderby/COUNTRY asc/emptyresult/show";

            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            URL url = new URL(baseURL + authPath);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            String input = "identity=" + userName + "&password=" + password;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

            }
            url = new URL(baseURL + query);
            br = new BufferedReader(new InputStreamReader((url.openStream())));

            while ((output = br.readLine()) != null) {

            }
            url = new URL(baseURL + "/ajaxauth/logout");
            br = new BufferedReader(new InputStreamReader((url.openStream())));
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}