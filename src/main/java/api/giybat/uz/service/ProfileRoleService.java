package api.giybat.uz.service;

import api.giybat.uz.entity.ProfileRoleEntity;
import api.giybat.uz.enums.UserRoles;
import api.giybat.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileRoleService {
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public void create(Integer profileId, UserRoles role){

        ProfileRoleEntity entity=new ProfileRoleEntity();

        entity.setProfileId(profileId);
        entity.setRoleName(role);
        entity.setCreatedDate(LocalDateTime.now());

        profileRoleRepository.save(entity);
    }

    public void deleteRoles(Integer profileId) {
        profileRoleRepository.deleteAllByProfileId(profileId);
    }
}
