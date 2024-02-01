package springphotograph.photograph.image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<Member, Long> {
}
