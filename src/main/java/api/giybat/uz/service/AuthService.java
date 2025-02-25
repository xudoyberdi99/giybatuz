package api.giybat.uz.service;

import api.giybat.uz.dto.RegistrationDto;
import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.enums.GeneralStatus;
import api.giybat.uz.enums.UserRoles;
import api.giybat.uz.exps.AppBadException;
import api.giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ProfileRoleService profileRoleService;

    public String registration(RegistrationDto profiledto){

        Optional<ProfileEntity> profileOption = profileRepository.findByUsernameAndVisibleTrue(profiledto.getUsername());
        if(profileOption.isPresent()){
            ProfileEntity profile=profileOption.get();
            if (profile.getGenstatus().equals(GeneralStatus.IN_REGISTRATION)){
                profileRoleService.deleteRoles(profile.getId());
                profileRepository.delete(profile);
            }else {
                throw new AppBadException("Already exists user");
            }
        }
        ProfileEntity profile=new ProfileEntity();
        profile.setUsername(profiledto.getUsername());
        profile.setName(profiledto.getName());
        profile.setPassword(bCryptPasswordEncoder.encode(profiledto.getPassword()));
        profile.setGenstatus(GeneralStatus.IN_REGISTRATION);
        profile.setVisible(true);
        profile.setCreatedDate(LocalDateTime.now());

        profileRepository.save(profile);

        profileRoleService.create(profile.getId(), UserRoles.ROLE_USER);

        return "Registred Successfully";
    }
}
