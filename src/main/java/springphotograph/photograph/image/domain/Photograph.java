package springphotograph.photograph.image.domain;

import java.nio.file.Path;

public class Photograph {

    private Long id;

    private String name;

    private Path image;

    public Photograph(String name, Path image) {
        this.name = name;
        this.image = image;
    }

    public Long idUpdate(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("값이 없습니다.");
        }
        return this.id = id;
    }


    public String getName() {
        return name;
    }

    public Path getImage() {
        return image;
    }
}
