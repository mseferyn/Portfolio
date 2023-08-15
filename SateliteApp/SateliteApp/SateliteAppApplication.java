package pl.ug.SateliteApp.SateliteApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import pl.ug.SateliteApp.SateliteApp.service.SatelliteService;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class SateliteAppApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SateliteAppApplication.class, args);
    }
}
