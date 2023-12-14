package springphotograph.photograph.image;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    private Integer imageSize;

    private String changeName;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private User user;

    @Builder
    public Image(String imageName, Integer imageSize, String changeName, String type) {
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.changeName = changeName;
        this.type = type;
    }

    public void addUser(User user) {

        if (this.user == null || this.user.equals(user)){
            if (this.user != null) {
                this.user.getImages().remove(this);
            }

            //snow4ram/sbb-mission
        }
        this.user = user;

        if (user != null) {
            if (!user.getImages().contains(this)) {
                user.getImages().add(this);
            }
        }
    }
}
