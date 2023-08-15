package pl.ug.SateliteApp.SateliteApp.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ug.SateliteApp.SateliteApp.domain.OrbitalObject;
import pl.ug.SateliteApp.SateliteApp.domain.ShortObject;
import pl.ug.SateliteApp.SateliteApp.domain.User;
import pl.ug.SateliteApp.SateliteApp.service.MailService;
import pl.ug.SateliteApp.SateliteApp.service.SatelliteService;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class SatelliteController {

    private MailService mailService;
    private SatelliteService satelliteService;
    JsonFactory jsonFactory;
    JsonParser jsonParser;
    private User currentUser;

    public SatelliteController(@Qualifier("SatelliteService") SatelliteService satelliteService) throws IOException, MessagingException {
        this.satelliteService = satelliteService;
        this.mailService = new MailService();

//        mailService.send("satellitesbachelor@gmail.com", "tomasz.rudnicki@onet.pl", "tomkotron@gmail.com", "tomasz.rudnicki@onet.pl", "Java Test Mail UG", "Testowy mail", null);
//        try {
//            List<User> newUsers = List.of(new User("Test name", "Test password"), new User("second name", "no pass"));
//            satelliteService.writeToFile(newUsers, "userDatabase.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            String dataFromFile = satelliteService.fileDataAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/sat/complex/{params}")
    public String getComplex(@PathVariable("params") String params, Model model) throws IOException {
        model.addAttribute(satelliteService.complexSort(params));
        return "satellite-all";
    }

    @PostMapping(path = "/sat/complex/{params}")
    public List<ShortObject> getComplexString(@PathVariable String params, Model model) throws IOException {
        List<ShortObject> output = satelliteService.complexSort(params);
        return output;
    }

    @PostMapping(path = "/testing")
    public void testing() throws IOException {
        List<User> users = satelliteService.deserializeUser(false);
    }

    @GetMapping(path = "/sat/noradid/{norad}") //single NORAD id
    public String getNorad(@PathVariable("norad") String norad, Model model) throws IOException {
        model.addAttribute(satelliteService.NORADsort(norad));
        return "satellite-all";
    }

    @PostMapping(path = "/sat/noradid/{id}")
    public List<OrbitalObject> GetNoradString(@PathVariable String id, Model model) throws IOException {

        model.addAttribute("satelliteID", satelliteService.NORADsort(id));
        List<OrbitalObject> output = satelliteService.NORADsort(id);
        return output;
    }

    @GetMapping(path = "/sat/country/{country}") //single country code
    public String GetCountry(@PathVariable String country, Model model) throws IOException {
        List<String> db = satelliteService.fileDataAsList();
        model.addAttribute("satellite", satelliteService.countrySort(country));
        return "satellite-all";
    }

    @PostMapping(path = "/sat/country/{cn}")
    public List<OrbitalObject> GetCountryString(@PathVariable String cn, Model model) throws IOException {

        model.addAttribute("satellite", satelliteService.countrySort(cn));
        List<OrbitalObject> output = satelliteService.countrySort(cn);
        return output;
    }

    @GetMapping("/api/get-database/string-list")
    public List<String> getDBStringList() throws IOException {
        List<String> db = satelliteService.fileDataAsList();
        return db;
    }

    @GetMapping("/user/{id}")
    public String getId(@PathVariable("userId") int userId, Model model) throws IOException {
        model.addAttribute(satelliteService.userIdSort(userId));
        return "user-add";
    }
    @GetMapping("/user/edit/{password}")
    public boolean changePassword1(@PathVariable("password") String userPass) throws IOException {
        boolean result = satelliteService.updateUser(userPass);
        return result;
    }
    @PostMapping("/user/edit/{password}")
    public boolean changePassword(@PathVariable("password") String userPass) throws IOException {
        boolean result = satelliteService.updateUser(userPass);
        return result;
    }

    @GetMapping("/userName/{name}")
    public String getName(@PathVariable("userName") String userName, Model model) throws IOException {
        model.addAttribute(satelliteService.userNameSort(userName));
        return "user-add";
    }

    @GetMapping("/user/add/{newUserData}")
    public String editUser(@PathVariable("newUserData") String newUserData, Model model) {
        model.addAttribute(newUserData);
        // Zwraca/przygotowuje widok formularza !
        return "user-edit";
    }

    @PostMapping("/user/add/{newUserData}")
    public boolean addNewUser(@PathVariable("newUserData") String newUserData, Model model) throws IOException {

        User newUser = satelliteService.addUser(newUserData);
        return (!(newUser == null));
    }

    @GetMapping("/user/login{login}")
    public String userLogin(@PathVariable("userLogin") String userLogin, Model model) {
        model.addAttribute("loginUser", userLogin);
        return "user-all";
    }
    @PostMapping("/user/login/{loginUser}")
    public Boolean UserLogin(@PathVariable("loginUser") String loginUser,  Model model) {
        User userLogged = satelliteService.loginUser(loginUser);
        if (userLogged != null) {
            currentUser = userLogged;
            return true;
        }
        return false;
    }
    @PostMapping("/user/favs/{ids}")
    public boolean addNewIds(@PathVariable("ids") String ids, Model model) throws IOException {
        ArrayList<Integer> parsedIds = satelliteService.parseIds(ids);
        return satelliteService.addNewFavourites(parsedIds);
    }

    public void readData() throws IOException {
        jsonFactory = new JsonFactory();
        jsonParser = jsonFactory.createParser("C:\\Dane\\05_semestr\\01_TeamProject\\Satellites\\satellites\\SateliteApp\\src\\main\\resources\\SatelliteDatabase.json");
        JsonToken current;
        current = jsonParser.nextToken();
        if (current != JsonToken.START_OBJECT) {
            System.out.println("Error: root should be object: quiting.");
            return;
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();
            // move from field name to field value
            current = jsonParser.nextToken();
            if (fieldName.equals("records")) {
                if (current == JsonToken.START_ARRAY) {
                    // For each of the records in the array
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        // read the record into a tree model,
                        // this moves the parsing position to the end of it
                        JsonNode node = jsonParser.readValueAsTree();
                    }
                } else {
                    System.out.println("Error: records should be an array: skipping.");
                    jsonParser.skipChildren();
                }
            } else {
                System.out.println("Unprocessed property: " + fieldName);
                jsonParser.skipChildren();
            }
        }
    }
}

