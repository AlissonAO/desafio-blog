package br.com.frwk.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cleber Leão
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(UploadController.class);
    /**
     * deverá definir o endereço para salvar o arquivo no computador
     */
    private static String UPLOADED_FOLDER = "c://teste//";

    @PostMapping(path = "upload/{user}")
    //@ResponseBody
    public ResponseEntity<?> uploadFile( @RequestParam("file") MultipartFile uploadfile, @PathVariable("user") Integer user) {

        logger.debug("Upload de um arquivo!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("Selecione uma foto!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Upload realizado com sucesso - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping("/upload/multi/{user}")
    public ResponseEntity<?> uploadFileMulti( @RequestParam("files") MultipartFile[] uploadfiles, @PathVariable("user") Integer user) {

        logger.debug("Upload de varios arquivos!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("Selecione uma foto! ", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Upload realizado com sucesso - "
                + uploadedFileName, HttpStatus.OK);

    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }

    }
}
