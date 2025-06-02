package api.giybat.uz.service;

import api.giybat.uz.dto.LoginDto;
import api.giybat.uz.dto.ProfileDto;
import api.giybat.uz.dto.RegistrationDto;
import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.enums.GeneralStatus;
import api.giybat.uz.enums.UserRoles;
import api.giybat.uz.exps.AppBadException;
import api.giybat.uz.repository.ProfileRepository;
import api.giybat.uz.repository.ProfileRoleRepository;
import api.giybat.uz.util.JwtUtil;
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
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

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

    public String regVerification(String token) {
        Integer profileId = JwtUtil.regVerifToken(token);
        ProfileEntity profile = profileService.getProfile(profileId);
        if (profile.getGenstatus().equals(GeneralStatus.IN_REGISTRATION)){
            profileRepository.changeStatus(profileId, GeneralStatus.ACTIVE);
            return "Verefication Successfully";
        }
        throw new AppBadException("Verification Failed");
    }

    public ProfileDto login(LoginDto logindto) {
        Optional<ProfileEntity> byUsernameAndVisibleTrue = profileRepository.findByUsernameAndVisibleTrue(logindto.getUsername());
        if(byUsernameAndVisibleTrue.isEmpty()){
            throw new AppBadException("Username or password is wrong");
        }
        ProfileEntity profile = byUsernameAndVisibleTrue.get();
        if (!bCryptPasswordEncoder.matches(logindto.getPassword(), profile.getPassword())){
            throw new AppBadException("Username or password is wrong");
        }
        if (!profile.getGenstatus().equals(GeneralStatus.ACTIVE)){
            throw new AppBadException("Wrong status");
        }

        //Response
        ProfileDto response = new ProfileDto();
        response.setUsername(profile.getUsername());
        response.setName(profile.getName());
        response.setRoleList(profileRoleRepository.gitAllRoleListProfileId(profile.getId()));
        //JWT

        return null;
    }
}
