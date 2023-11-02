package springphotograph.photograph.image.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springphotograph.photograph.image.dto.StorageProperties;
import springphotograph.photograph.image.service.PhotographService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PhotographController {

    @Autowired
    PhotographService photographService;

    @Autowired
    StorageProperties properties;

    @GetMapping
    public String showUploadForm(){
        return "upload";
    }

    @PostMapping
    public String saveUploadForm(@RequestParam("file") MultipartFile file) {

        String store = photographService.store(file);

        log.info("store = {}", store);

        return "redirect:";
    }


}
