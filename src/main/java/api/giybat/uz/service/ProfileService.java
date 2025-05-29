package api.giybat.uz.service;

import api.giybat.uz.entity.ProfileEntity;
import api.giybat.uz.exps.AppBadException;
import api.giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity getProfile(Integer profileid) {
        return (ProfileEntity) profileRepository.findByIdAndVisibleTrue(profileid).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
    }
}
