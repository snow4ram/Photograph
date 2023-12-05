package springphotograph.photograph.image;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ImageRepository {

    private final Map<Long, String> store = new HashMap<>();

    AtomicLong atomicLong = new AtomicLong(1L);

    public void save(String imageName){

        long andIncrement = atomicLong.getAndIncrement();

        store.put(andIncrement, imageName);
    }

    public List<String> findByImage() {
        return new ArrayList<>(store.values());
    }

}
