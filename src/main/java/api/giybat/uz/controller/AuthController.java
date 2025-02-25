package api.giybat.uz.controller;

import api.giybat.uz.dto.RegistrationDto;
import api.giybat.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid  @RequestBody RegistrationDto dto){
        return ResponseEntity.ok().body(authService.registration(dto));
    }
}
