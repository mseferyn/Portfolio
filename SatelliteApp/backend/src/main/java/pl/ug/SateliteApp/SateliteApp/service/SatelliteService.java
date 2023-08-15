package pl.ug.SateliteApp.SateliteApp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import pl.ug.SateliteApp.SateliteApp.util.SpacetrackLogin;
import pl.ug.SateliteApp.SateliteApp.domain.OrbitalObject;
import pl.ug.SateliteApp.SateliteApp.domain.ShortObject;
import pl.ug.SateliteApp.SateliteApp.domain.User;

import javax.persistence.Tuple;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
@Qualifier("SatelliteService")
@AllArgsConstructor
public class SatelliteService extends ObjectMapper {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private SpacetrackLogin spacetrackLogin;
    private final RestTemplate restTemplate;
    ObjectMapper mapper;
    File dataFile;
    File userFile;
    File coordScript;

    Process mProcess;
    User currentUser;

    public SatelliteService() throws FileNotFoundException {
        spacetrackLogin = new SpacetrackLogin();
        spacetrackLogin.updateDB();
        mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String localDir = System.getProperty("user.dir");
        Path userPath = Paths.get(localDir + "/src/main/resources/userDatabase.json");
        this.dataFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "testDatabase.json");
        this.userFile = ResourceUtils.getFile(localDir + "\\src\\main\\resources\\userDatabase.json");
        this.coordScript = ResourceUtils.getFile(localDir + "/GetCoordinates.py");
//        this.userFile = ResourceUtils.getFile(userPath);
        this.restTemplate = new RestTemplateBuilder().build();
    }


    public List<OrbitalObject> fileAsShortObjects() throws IOException {
        List<OrbitalObject> nodes = new ArrayList<>();
        String input = fileDataAsString();

        String parsed = input.replaceAll("\\[", "")
                .replace("\"", "")
                .replaceAll("\\]", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll("\\{", "");

        String[] splitParsed = parsed.split("\\}");
        for (String elem : splitParsed) {
            elem.replaceAll("\\},", "");
            elem.replaceAll("\\}", "");
        }

        return nodes;
    }

    public List<OrbitalObject> fileAsObjects() throws IOException {
        List<OrbitalObject> nodes = new ArrayList<OrbitalObject>();
        String input = fileDataAsString();

        String parsed = input.replaceAll("\\[", "")
                .replace("\"", "")
                .replaceAll("\\]", "")
//                .trim()
                .replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll("\\{", "");

        String[] splitParsed = parsed.split("\\}");
        for (String elem : splitParsed) {
            elem.replaceAll("\\},", "");
            elem.replaceAll("\\}", "");
        }

        //# of row = object index, # of column = index of parameter
        String[][] output = new String[splitParsed.length][40];
        String[] splitOne;
        int size = 0;
        int remover = 0;
        for (int i = 0; i < splitParsed.length; i++) {
            splitOne = splitParsed[i].trim().split(",");
//            splitOne = splitParsed[i].split(": ");
            size = splitOne.length;
            if (i > 0) {
                splitOne = Arrays.copyOfRange(splitOne, 1, splitOne.length);
                size -= 1;
            }
            for (int j = 0; j < size; j++) {
//                if (splitOne[j].length() < 2 && j < splitOne.length - 1 && j > remover) {
//                    remover++;
//                }
                output[i][j] = splitOne[j].split(": ")[1];
//                output[i][j - remover] = splitOne[j].split(": ")[1];
            }
        }

        for (int i = 0; i < splitParsed.length; i++) {
            String[] splitInput = output[i];
            OrbitalObject tempObj = new OrbitalObject(splitInput);
            System.out.println();
            nodes.add(tempObj);
        }
        return nodes;
    }

    public List<OrbitalObject> dateSort(String launchDate) throws IOException { //sortuje po dacie
        List<OrbitalObject> output = new ArrayList<OrbitalObject>();
        List<OrbitalObject> input = fileAsObjects();
        for (OrbitalObject elem : input) {
            if (elem.getLaunchDate().equals(launchDate))
                output.add(elem);
        }
        return output;
    }

    public List<ShortObject> dateSort(String launchDate, List<ShortObject> input) throws IOException { //sortuje po dacie
        if (input == new ArrayList<ShortObject>())
            input = shortList(fileAsObjects());
        List<ShortObject> output = new ArrayList<ShortObject>();
        for (ShortObject elem : input) {
            if (elem.getLaunchDate().equals(launchDate))
                output.add(elem);
        }
        return output;
    }

    public List<OrbitalObject> NORADsort(String norad) throws IOException { //sortuje po NORAD
        List<OrbitalObject> output = new ArrayList<OrbitalObject>();
        List<OrbitalObject> input = fileAsObjects();
        for (OrbitalObject elem : input) {
            if (elem.getNORADCatId().equals(norad))
                output.add(elem);
        }

        return output;
    }

    public List<ShortObject> NORADSort(String norad, List<ShortObject> input) throws IOException { //sortuje po NORAD
        if (input == new ArrayList<ShortObject>())
            input = shortList(fileAsObjects());
        List<ShortObject> output = new ArrayList<ShortObject>();
        for (ShortObject elem : input) {
            if (elem.getNORADCatId().equals(norad))
                output.add(elem);
        }

        return output;
    }

    public List<OrbitalObject> countrySort(String country) throws IOException { //sortuje po kraju
        List<OrbitalObject> input = fileAsObjects();
        List<OrbitalObject> output = new ArrayList<OrbitalObject>();
        for (OrbitalObject elem : input) {
            if (elem.getCountryCode().equals(country))
                output.add(elem);
        }
        return output;
    }

    public List<ShortObject> countrySort(String country, List<ShortObject> input) throws IOException { //sortuje po kraju
        if (input == new ArrayList<ShortObject>())
            input = shortList(fileAsObjects());
        List<ShortObject> output = new ArrayList<ShortObject>();
        for (ShortObject elem : input) {
            if (elem.getCountry().equals(country))
                output.add(elem);
        }
        return output;
    }

    public List<OrbitalObject> launchDateSort(String launchDate) throws IOException { //sortuje po dacie wystrzelenia
        List<OrbitalObject> input = fileAsObjects();
        List<OrbitalObject> output = new ArrayList<OrbitalObject>();
        for (OrbitalObject elem : input) {
            if (elem.getLaunchDate().equals(launchDate))
                output.add(elem);
        }
        return output;
    }

    public List<ShortObject> launchDateSort(String launchDate, List<ShortObject> input) throws IOException { //sortuje po kraju
        if (input == new ArrayList<ShortObject>())
            input = shortList(fileAsObjects());
        List<ShortObject> output = new ArrayList<ShortObject>();
        for (ShortObject elem : input) {
            if (elem.getLaunchDate().equals(launchDate))
                output.add(elem);
        }
        return output;
    }

    public List<ShortObject> shortList(List<OrbitalObject> input) {
        Random r = new Random();
        List<ShortObject> output = new ArrayList<ShortObject>();
        String coords = new String();
        ShortObject temp = new ShortObject();
        String[] replaced = new String[2];
        for (OrbitalObject object : input) {
            temp = new ShortObject(object.getObjectName(), object.getLaunchDate(), object.getCountryCode(), object.getTleLine1(), object.getTleLine2(), object.getNORADCatId());
            coords = getCoordinates(temp);
            replaced = coords.replace(" ", "").replace("\n", "").split(",");
            temp.setLatitude(replaced[0].replace(".", "").replaceFirst(":", ".").replaceAll(":", ""));
            temp.setLongitude(replaced[1].replace(".", "").replaceFirst(":", ".").replaceAll(":", ""));
            output.add(temp);

        }
        return output;
    }

    public List<ShortObject> complexSort(String params) throws IOException {
        String[] paramArr = getParams(params);
        List<ShortObject> input = shortList(fileAsObjects());
        for (int i = 0; i < paramArr.length - 1; i++) {
            if (paramArr[i].equals("NORAD_ID") && input.size() != 0 && !paramArr[i + 1].equals("") && !paramArr[i + 1].equals("undefined"))
                input = NORADSort(paramArr[i + 1], input);
            if (paramArr[i].equals("LAUNCH_DATE") && input.size() != 0 && !paramArr[i + 1].equals("") && !paramArr[i + 1].equals("undefined"))
                input = launchDateSort(paramArr[i + 1], input);
            if (paramArr[i].equals("COUNTRY") && input.size() != 0 && !paramArr[i + 1].equals("") && !paramArr[i + 1].equals("undefined"))
                input = countrySort(paramArr[i + 1], input);
        }
        return input;
    }

    public String[] getParams(String params) {
        String[] output = params.split("&|\\:");
        return output;
    }

    public String getCoordinates(ShortObject object) {
        String date = "2022/07/09";
        String line0 = object.getName();
        String line1 = object.getTleLine1();
        String line2 = object.getTleLine2();
        String line;
        String output = "";

        try {
            ProcessBuilder builder = new ProcessBuilder("py", System.getProperty("user.dir") + "\\GetCoordinates.py", date, line0, line1, line2);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String lines = null;

            while ((lines = reader.readLine()) != null) {
                System.out.println(lines);
                output += lines;
            }
//            process = Runtime.getRuntime().exec(new String[]{coordScript.getPath(), line0, line1, line2});
//            mProcess = process;
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
//        InputStream stdout = mProcess.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
//
//        try {
//            while ((line = reader.readLine()) != null) {
//                output += line;
//            }
//        } catch (IOException e) {
//            System.out.println("Exception in reading output" + e.toString());
//        }
        return output;
    }

    public String getNextPass(ShortObject object, User user) {
        Process process;
        String line0 = object.getName();
        String line1 = object.getTleLine1();
        String line2 = object.getTleLine2();
        String lon = String.valueOf(user.getCoords()[0]);
        String lat = String.valueOf(user.getCoords()[1]);
        String line;
        String output = "";
        try {
            process = Runtime.getRuntime().exec(new String[]{"GetNextPass", line0, line1, line2, lon, lat});
            mProcess = process;
        } catch (Exception e) {
            System.out.println("Exception Raised" + e.toString());
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        try {
            while ((line = reader.readLine()) != null) {
                output += line;
            }
        } catch (IOException e) {
            System.out.println("Exception in reading output" + e.toString());
        }
        return output;
    }

    //TODO sorting by local coordinates
    //TODO User database and functionalities
    //===================== USER =========================
    public String fileDataAsString() throws IOException {
        String content = new String(Files.readAllBytes(dataFile.toPath()));
        return content;
    }

    public String userDataAsString() throws IOException {
        String content = new String(Files.readAllBytes(userFile.toPath()));
        return content;
    }

    public void writeToFile(List<User> toWrite, String filename) throws IOException {
        for (User user : toWrite)
            Files.writeString(Path.of(System.getProperty("user.dir"), filename), user.asString() + System.lineSeparator(), CREATE, APPEND);
    }

    public void writeToFile(List<User> toWrite, String filename, String path) throws IOException {
        FileWriter writer = new FileWriter(path, false);
        String toFile = "[";
        for (User user : toWrite)
            toFile += user.asString() + "," + System.lineSeparator();
        toFile = toFile.substring(0, toFile.length() - 3) + "]";
        writer.write(toFile);
        writer.close();
    }

    public List<User> deserializeUser(boolean isDatabase) throws IOException {
        List<User> output = new ArrayList<>();
        String userData = userDataAsString();

        String parsed = userData.replaceAll("\\[", "")
                .replace("\"", "")
                .replaceAll("\\]", "")
                .replaceAll("\r", "")
                .replaceAll("\n", "");

        String[] splitParsed = parsed.split("\\}");
        String[][] separateusers = new String[splitParsed.length][];
        ArrayList<String> toRemove = new ArrayList<>();
        toRemove.add(null);
        toRemove.add("");
        String temp2 = new String();
        for (int i = 0; i < splitParsed.length; i++) {
            separateusers[i] = splitParsed[i].replaceAll("\\},", "").replaceAll("\\}", "").split(",|\\:");
            if (i > 0 && !isDatabase)
//                temp2 = "test";
                separateusers[i] = Arrays.copyOfRange(separateusers[i], 1, separateusers[i].length);
            User temp = new User();
            for (int k = 0; k < separateusers[i].length; k++) {
                separateusers[i][k] = separateusers[i][k].replaceAll(" ", "").trim();
            }
            temp.setId(Integer.parseInt(separateusers[i][1]));
            temp.setName(separateusers[i][3]);
            temp.setPassword(separateusers[i][5]);
            temp.setEmail(separateusers[i][7]);
            float[] coords = new float[2];
            ArrayList<Integer> favourites = new ArrayList<>();
            coords[0] = Float.parseFloat(separateusers[i][9]);
            coords[1] = Float.parseFloat(separateusers[i][10]);
            temp.setCoords(coords);

            for (int j = 12; j < separateusers[i].length; j++)
                if (separateusers[i][j] != null && !separateusers[i][j].equals(""))
                    favourites.add(Integer.parseInt(separateusers[i][j]));
            temp.setFavourites(favourites);
            output.add(temp);
        }
        return output;
    }

    public List<User> usersAsUserList() throws IOException {
        List<User> users = Arrays.asList(mapper.readValue(userFile, User[].class));
        return users;
    }

    public List<String> fileDataAsList() throws IOException {
        String content = new String(Files.readAllBytes(dataFile.toPath()));
        List<String> output = Arrays.asList(content.split(","));
        return output;
    }

    public User addUser(String userData) throws IOException {
        Random rng = new Random();
        List<User> users = deserializeUser(false);
        List<Integer> ids = new ArrayList<>();
        for(int i = 0; i < users.size(); i++)
            ids.add(users.get(i).getId());

        String[] userList = getParams(userData);
        int newId = rng.nextInt(1000);
        while(ids.contains(newId))
            newId = rng.nextInt(1000);

        String email = userList[1];
        String name = userList[3];
        String passwd = userList[5];

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(passwd) || users.get(i).getName().equals(name))
                return null;
        }

        float[] coords = new float[2];
        coords[0] = Float.parseFloat(userList[7]);
        coords[1] = Float.parseFloat(userList[9]);

        User userToAdd = new User(name, passwd, email, coords, new ArrayList<>());
        userToAdd.setId(newId);
        users.add(userToAdd);
        writeToFile(users, userFile.getName(), userFile.getPath());
        return userToAdd;
    }

    public boolean checkPassword(String input) throws IOException {
        List<User> users = usersAsUserList();
        String[] passwordData = getParams(input);
        String password = passwordData[1];
        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getPassword() == password)
                return true;
        return false;
    }

    public boolean updateUser(String passwords) throws IOException {
        String[] loginData = getParams(passwords);
        User toUpdate = new User();
        String oldpass = loginData[1].replaceAll("\"", "").replaceAll(" ", "");
        String newpass = loginData[2].replaceAll("\"", "").replaceAll(" ", "");
        try {
            if (!currentUser.getPassword().equals(oldpass)) {
                return false;
            }
            List<User> users = deserializeUser(false);
            currentUser.setPassword(newpass);

            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getId() == currentUser.getId()) {
                    users.get(i).setPassword(newpass);
                    writeToFile(users, userFile.getName(), userFile.getPath());
                    return true;
                }
        } catch (
                IOException ioException) {
            ioException.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(User user) {
        try {
            List<User> users = usersAsUserList();
            if (users.contains(user)) {
                users.remove(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User loginUser(String input) {//TODO finish logging
        String[] loginData = getParams(input);
        String username = loginData[1].replaceAll("\"", "").replaceAll(" ", "");
        String password = loginData[3].replaceAll("\"", "").replaceAll(" ", "");
        try {
            List<User> users = deserializeUser(false);
            for (int i = 0; i < users.size(); i++)
                if (users.get(i).getName().equals(username) && users.get(i).getPassword().equals(password)) {
                    currentUser = users.get(i);
                    return users.get(i);
                }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }


    public List<User> userNameSort(String name) throws IOException {
        List<User> toSort = Arrays.asList(mapper.readValue(userFile, User[].class));
        List<User> output = new ArrayList<User>();
        for (User user : toSort) {
            if (user.getName().equals(name)) {
                output.add(user);
            }
        }
        return output;
    }

    public List<User> userIdSort(int id) throws IOException {
        List<User> toSort = Arrays.asList(mapper.readValue(userFile, User[].class));
        List<User> output = new ArrayList<User>();
        for (User user : toSort) {
            if (user.getId() == id) {
                output.add(user);
            }
        }
        return output;
    }

    public ArrayList<Integer> parseIds(String ids) {
        String[] parsedStrings = ids.split("&");
        ArrayList<Integer> output = new ArrayList<>();
        for (String parsedId : parsedStrings) {
            if (!output.contains(parsedId))
                output.add(Integer.parseInt(parsedId));
        }
        return output;
    }

    public boolean addNewFavourites(ArrayList<Integer> ids) throws IOException {
        List<User> users = deserializeUser(false);
        currentUser.setFavourites(ids);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == currentUser.getId())
                users.get(i).setFavourites(ids);
            writeToFile(users, userFile.getName(), userFile.getPath());
            return true;
        }
        writeToFile(users, userFile.getName(), userFile.getPath());
        return false;
    }

    public void testFunc() {

    }
}
