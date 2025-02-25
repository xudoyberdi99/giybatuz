package api.giybat.uz.repository;

import api.giybat.uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);
}
