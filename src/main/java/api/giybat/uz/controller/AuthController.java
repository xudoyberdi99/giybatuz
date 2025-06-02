package api.giybat.uz.controller;

import api.giybat.uz.dto.LoginDto;
import api.giybat.uz.dto.RegistrationDto;
import api.giybat.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid  @RequestBody RegistrationDto dto){
        return ResponseEntity.ok().body(authService.registration(dto));
    }

    @GetMapping("/registration/verification/{token}")
    public ResponseEntity<?> regVerification(@PathVariable("token") String token){
        return ResponseEntity.ok().body(authService.regVerification(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginDto dto){
        return ResponseEntity.ok().body(authService.login(dto));
    }
}
