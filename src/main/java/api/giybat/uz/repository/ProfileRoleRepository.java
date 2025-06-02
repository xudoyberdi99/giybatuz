package api.giybat.uz.repository;

import api.giybat.uz.entity.ProfileRoleEntity;
import api.giybat.uz.enums.UserRoles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity,Integer> {
    @Transactional
    @Modifying
    void deleteAllByProfileId(Integer profileId);

    @Query("select p.roleName From ProfileRoleEntity p where p.profileId=?1")
    List<UserRoles> gitAllRoleListProfileId(Integer profileId);
}
