package com.example.archivosapi.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/apiarchivos")
public class ArchivoController {

    // Método para servir la imagen llamada imagen3.jpg
    @GetMapping("/imagen/imagen3")
    public ResponseEntity<Resource> obtenerImagen() throws IOException {
        return servirArchivo("/static/imagen/imagen3.jpg", MediaType.IMAGE_JPEG);
    }

    // Método para servir el PDF llamado ejemplo.pdf
    @GetMapping("/pdf/ejemplo")
    public ResponseEntity<Resource> obtenerPdf() throws IOException {
        return servirArchivo("/static/pdf/ejemplo.pdf", MediaType.APPLICATION_PDF);
    }

    // Método para servir el archivo HTML llamado index.html
    @GetMapping("/html/index")
    public ResponseEntity<Resource> obtenerHtml() throws IOException {
        return servirArchivo("/static/html/index.html", MediaType.TEXT_HTML);
    }

    // Método para servir el archivo XML llamado datos.xml
    @GetMapping("/xml/datos")
    public ResponseEntity<Resource> obtenerXml() throws IOException {
        return servirArchivo("/static/xml/datos.xml", MediaType.APPLICATION_XML);
    }

    // Método para servir el archivo JSON llamado config.json
    @GetMapping("/json/config")
    public ResponseEntity<Resource> obtenerJson() throws IOException {
        return servirArchivo("/static/json/config.json", MediaType.APPLICATION_JSON);
    }

    // Método genérico para servir cualquier archivo con el tipo de contenido adecuado
    private ResponseEntity<Resource> servirArchivo(String ruta, MediaType mediaType) throws IOException {
        Resource recurso = new ClassPathResource(ruta);
        if (!recurso.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        byte[] contenido = Files.readAllBytes(recurso.getFile().toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<>(recurso, headers, HttpStatus.OK);
    }
}
