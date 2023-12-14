package springphotograph.photograph.image;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

    private final UploadService uploadService;


    @GetMapping
    public String save() {
        return "homepage";
    }

    @PostMapping
    public String upload(ImageUploadRequest multipartFile) {

        log.info("multipartFile = {} " , multipartFile);
        uploadService.upload(multipartFile);

        return "redirect:";
    }


    @GetMapping("/list")
    public String list(Model model) {

        List<String> all = uploadService.findAll();

        model.addAttribute("image", all);

        return "list";
    }

    @ResponseBody
    @GetMapping("/list/{filename}")
    public Resource download(@PathVariable(name = "filename") String filename) throws MalformedURLException {
        String fullPath = uploadService.getFullPath(filename);

        log.info("fullPath = {}" ,fullPath);

        return new UrlResource("file:" + fullPath);
    }

}
