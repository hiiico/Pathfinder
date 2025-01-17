package com.example.pathfinder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileService {

    private final CurrentUserService currentUserService;

    public boolean saveUserFile(MultipartFile gpxCoordinates) throws IOException {

        //TODO check file content

        String directoryName = currentUserService.getUser().getUsername();
        String directoryPath = "src" + File.separator
                + "main" + File.separator
                + "resources" + File.separator
                + "uploads" +  File.separator
                + directoryName + File.separator;
        File directory = new File(directoryPath);

        boolean directoryCreated = directory.mkdir();

        if (directory.exists()) {
            Path destinationFile = Paths
                    .get("src" + File.separator
                            + "main" + File.separator
                            + "resources" + File.separator
                            + "uploads"+ File.separator
                            + directoryName + File.separator
                            + gpxCoordinates.getOriginalFilename())
                    .normalize()
                    .toAbsolutePath();
            // TODO check if file exist
            if(!destinationFile.toFile().exists()) {
                try (InputStream inputStream = gpxCoordinates.getInputStream()) {
                    Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                }
                return true;
            }

        }
        return false;
    }

}
