package springphotograph.photograph.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageUploadRequest {


    //MultipartFile : getOriginal
    private List<MultipartFile> file;


}
