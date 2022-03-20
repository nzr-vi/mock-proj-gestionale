package it.es.gestionale.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Value("${file.basePath}")
    public String basePath;
    //private final String basePath="src/main/resources/static/";

    public String saveFile(String cartellaDest, MultipartFile file) throws IOException {
        // Pulizia path e nome file da caratteri speciali
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename())
    						.replace(" ", ""); 

    	// Percorso relativo del file
        String cartellaOut = "/" + cartellaDest; 
        // Percorso interno su cui il file verrà scritto
        cartellaDest = basePath + cartellaDest;

        // Costruisco il puntamento alla cartella di destinazione
        Path destinazione = Paths.get(cartellaDest);

        // Crea la destinazione se non esiste
        if (!Files.exists(destinazione)) { 
            Files.createDirectories(destinazione);
        }

        try (InputStream inputStream = file.getInputStream()) {
        	// Risolve il percorso completo di nome file
            Path filePath = destinazione.resolve(fileName); 
            // Scrive su FS sovvrascrivendo eventuale file con lo stesso nome
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return cartellaOut + "/" + fileName;
            
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + file.getName(), ioe);
        }
    }
}
