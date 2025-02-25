package api.giybat.uz.repository;

import api.giybat.uz.entity.ProfileRoleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity,Integer> {
    @Transactional
    @Modifying
    void deleteAllByProfileId(Integer profileId);
}
