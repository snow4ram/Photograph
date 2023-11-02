package springphotograph.photograph.image.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import springphotograph.photograph.exception.StorageException;
import springphotograph.photograph.image.dto.StorageProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class PhotographService {
    private final Path root;

    @Autowired
    public PhotographService(StorageProperties properties) {
        this.root = Paths.get(properties.getUrl());
    }

    public String store(MultipartFile file) {

        //url : 부모 : /url 하위 자식 : /XXX.png
        if (file.isEmpty()) {
            throw new StorageException("");
        }

        Path destinationFile = this.root.resolve(Paths.get(Objects.requireNonNull(
                        file.getOriginalFilename())))
                .normalize().toAbsolutePath();

        System.out.println("---------------------DestinationFile---------------------");
        log.info("destinationFile = {}", destinationFile);
        log.info("root = {}", root);
        System.out.println("---------------------------------------------------------");

        if (!destinationFile.getParent().equals(this.root.toAbsolutePath())) {
            throw new StorageException("");
        }

        String ex = extractExt(destinationFile.toString());
        String uuid = UUID.randomUUID().toString();

        return uuid+"."+ex;

    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        //pns
        return originalFilename.substring(pos +1);
    }
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
