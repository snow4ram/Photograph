package springphotograph.photograph.image;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageUploadRequest {

    private String content;


    //MultipartFile : getOriginal
    private List<MultipartFile> file;

    @Builder
    public ImageUploadRequest(String content, List<MultipartFile> file) {
        this.content = content;
        this.file = file;
    }
}
