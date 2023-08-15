//package pl.ug.SateliteApp.SateliteApp.service;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.client.RestTemplate;
//import pl.ug.SateliteApp.SateliteApp.domain.OrbitalObject;
//import pl.ug.SateliteApp.SateliteApp.domain.User;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//@Qualifier("UserService")
//public class UserService extends ObjectMapper {
//
//    private final RestTemplate restTemplate;
//    ObjectMapper mapper;
//    File dataFile;
//    JSONPObject operationsFile;
//
//    public UserService() throws FileNotFoundException {
//        mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        this.dataFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "userDatabase.json");
//        this.restTemplate = new RestTemplateBuilder().build();
//    }
//
//
//}
