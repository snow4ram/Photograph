package springphotograph.photograph.image.dto;
import org.springframework.stereotype.Component;



@Component
public class StorageProperties {
    private String name = "url";

    public String getUrl() {
        return name;
    }


}
