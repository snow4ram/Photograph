package springphotograph.photograph.image;

import lombok.Data;


public class UploadFile {

    private String uploadFileName;

    public UploadFile(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
