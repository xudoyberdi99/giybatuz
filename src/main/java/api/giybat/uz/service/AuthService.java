package api.giybat.uz.service;

import api.giybat.uz.dto.RegistrationDto;
import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.enums.GeneralStatus;
import api.giybat.uz.enums.UserRoles;
import api.giybat.uz.exps.AppBadException;
import api.giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private ProfileService profileService;

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
        //insert role
        profileRoleService.create(profile.getId(), UserRoles.ROLE_USER);
        //send email
        emailSendingService.sendRegistrationEmail(profile.getUsername(), profile.getId());
        return "Registred Successfully";
    }

    public String regVerification(Integer profileId) {
        ProfileEntity profile = profileService.getProfile(profileId);
        if (profile.getGenstatus().equals(GeneralStatus.IN_REGISTRATION)){
            profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
            return "Verefication Successfully";
        }
        throw new AppBadException("Verification Failed");
    }
}
