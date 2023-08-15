package pl.ug.SateliteApp.SateliteApp.service;

import pl.ug.SateliteApp.SateliteApp.domain.ShortObject;
import pl.ug.SateliteApp.SateliteApp.domain.User;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ScriptPython {
    Process mProcess;

    public void getCoordinates(ShortObject object) {
        Process process;
        String line0 = object.getName();
        String line1 = object.getTleLine1();
        String line2 = object.getTleLine2();

        try {
            process = Runtime.getRuntime().exec(new String[]{"GetCoordinates.py", line0, line1, line2});
            mProcess = process;
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println("stdout: " + line);
            }
        } catch (IOException e) {
            System.out.println("Exception in reading output" + e.toString());
        }
    }

    public void getNextPass(ShortObject object, User user) {
        Process process;
        String line0 = object.getName();
        String line1 = object.getTleLine1();
        String line2 = object.getTleLine2();
        String lon = String.valueOf(user.getCoords()[0]);
        String lat = String.valueOf(user.getCoords()[1]);
        try {
            process = Runtime.getRuntime().exec(new String[]{"GetNextPass", line0, line1, line2, lon, lat});
            mProcess = process;
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println("stdout: " + line);
            }
        } catch (IOException e) {
            System.out.println("Exception in reading output" + e.toString());
        }
    }
}