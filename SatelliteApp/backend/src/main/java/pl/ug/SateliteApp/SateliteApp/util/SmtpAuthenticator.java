package pl.ug.SateliteApp.SateliteApp.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {
        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "satellitesbachelor@gmail.com";
        String password = "lzuuuzhfjsokhovt";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}