package springphotograph.photograph.image;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    @Value("${image.dir}")
    private String fileDir; //현재 이미지 저장할 파일의 위치

    public String getFullPath(String filename) {
        return fileDir + filename;
    }


    private final ImageRepository imageRepository;

    public void upload(ImageUploadRequest multipartFile) {

        final int size = multipartFile.getFile().size();


        multipartFile.getFile().forEach(f ->{
            try {
                String originalFilename = f.getOriginalFilename();

                log.info("size = {}" , size);

                final int index = Objects.requireNonNull(originalFilename).lastIndexOf(".");

                final String substring = originalFilename.substring(index + 1);

                final String result = UUID.randomUUID() + "." + substring;

                Image imageBuilder = Image
                        .builder()
                        .imageName(originalFilename)
                        .imageSize(size)
                        .changeName(originalFilename)
                        .type(substring)
                        .build();

                f.transferTo(new File(getFullPath(result)));

                imageRepository.save(imageBuilder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public List<String> findAll() {
        List<Image> all = imageRepository.findAll();

        return all.stream()
                .map(Image::getChangeName)
                .collect(Collectors.toList());

    }
}
