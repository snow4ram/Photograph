package springphotograph.photograph.image;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {


    private final ImageRepository imageRepository;

    @Value("${image.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }


    @GetMapping
    public String save() {
        return "homepage";
    }

    @PostMapping
    public String upload(ImageUploadRequest multipartFile) {

        log.info("multipartFile = {} " , multipartFile);

        multipartFile.getFile().forEach(f->{
            String originalFilename = f.getOriginalFilename();

            int index = originalFilename.lastIndexOf(".");
            String substring = originalFilename.substring(index + 1);

            log.info("orginaal Name = {}" , substring);

            String s = UUID.randomUUID().toString() + "." + substring;

            try {
                f.transferTo(new File(getFullPath(s)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("orginaal Name = {}" , s);
            imageRepository.save(s);
        });

        return "redirect:";
    }


    @GetMapping("/list")
    public String list(Model model) {


        List<String> all = imageRepository.findByImage();

        log.info("all = {}" , all);

        log.info("filename = {}" , all);
        model.addAttribute("image", all);
        return "list";
    }

    @ResponseBody
    @GetMapping("/list/{filename}")
    public Resource download(@PathVariable(name = "filename") String filename) throws MalformedURLException {
        return new UrlResource("file:" + getFullPath(filename));
    }
}
