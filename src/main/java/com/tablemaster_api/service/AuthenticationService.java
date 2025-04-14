package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.UserRepository;
import com.tablemaster_api.dto.LoginResponseDto;
import com.tablemaster_api.dto.LoginUserDto;
import com.tablemaster_api.dto.RegisterUserDto;
import com.tablemaster_api.dto.VerifyUserDto;
import com.tablemaster_api.entity.User;
import jakarta.mail.MessagingException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticationService implements UserDetailsService {


    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                user.getAuthorities()
        );
    }

    public User registerUser(RegisterUserDto registerUserDto) throws MessagingException {
        User user = new User(registerUserDto.username(), registerUserDto.email(),
                passwordEncoder.encode(registerUserDto.password()),
                registerUserDto.roles());
        user.setVerificationCode(generateRandomSixNumber());
        user.setVerificationCodeExpiresAt(LocalTime.now().plusMinutes(15));
        user.setEnabled(false);
        sendVerificationEmail(user);
        return userRepository.save(user);
    }

    public LoginResponseDto loginUser(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.username(),
                        loginUserDto.password())
        );

        return new LoginResponseDto(
                jwtTokenService.generateToken(loadUserByUsername(loginUserDto.username())),
                jwtTokenService.getExpiration()
        );
    }

    public void verifyUser(VerifyUserDto verifyUserDto) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(verifyUserDto.email());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalTime.now())) {
                throw new AuthenticationException("Verification has expired. Please register again.");
            }
            if (user.getVerificationCode().equals(verifyUserDto.verificationCode())) {
                user.setEnabled(true);
                user.setVerificationCodeExpiresAt(null);
                user.setVerificationCode(null);
                userRepository.save(user);
            } else {
                throw new AuthenticationException("Invalid verification code.");
            }
        } else {
            throw new AuthenticationException(String.format("User with email %s not found", verifyUserDto.email()));
        }
    }

    public void resendVerificationEmail(String email) throws MessagingException, AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isEnabled()) {
                throw new AuthenticationException("Account already verified");
            }
            user.setVerificationCode(generateRandomSixNumber());
            user.setVerificationCodeExpiresAt(LocalTime.now().plusMinutes(15));
            sendVerificationEmail(user);
            userRepository.save(user);
        } else {
            throw new AuthenticationException(String.format("User with email %s not found", email));
        }
    }

    public void sendVerificationEmail(User user) throws MessagingException {
            emailService.sendEmail(user.getEmail(), "TableMasterAPI verification", "<!DOCTYPE html>\n" +
                    "<html lang=\"ru\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>Подтверждение регистрации</title>\n" +
                    "  <style>\n" +
                    "    body {\n" +
                    "      font-family: Arial, sans-serif;\n" +
                    "      background-color: #f6f6f6;\n" +
                    "      padding: 20px;\n" +
                    "    }\n" +
                    "    .email-container {\n" +
                    "      max-width: 500px;\n" +
                    "      margin: auto;\n" +
                    "      background-color: #ffffff;\n" +
                    "      border-radius: 8px;\n" +
                    "      padding: 30px;\n" +
                    "      box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                    "    }\n" +
                    "    .code {\n" +
                    "      font-size: 32px;\n" +
                    "      letter-spacing: 10px;\n" +
                    "      font-weight: bold;\n" +
                    "      color: #2c3e50;\n" +
                    "      text-align: center;\n" +
                    "      margin: 30px 0;\n" +
                    "    }\n" +
                    "    .footer {\n" +
                    "      font-size: 12px;\n" +
                    "      color: #777;\n" +
                    "      text-align: center;\n" +
                    "      margin-top: 40px;\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div class=\"email-container\">\n" +
                    "    <h2>Приветствуем!</h2>\n" +
                    "    <p>Вы зарегистрировались на нашем сайте. Для завершения регистрации введите код подтверждения:</p>\n" +
                    "\n" +
                    "    <div class=\"code\">" + user.getVerificationCode() + "</div>\n" +
                    "\n" +
                    "    <p>Если вы не регистрировались, просто проигнорируйте это письмо.</p>\n" +
                    "\n" +
                    "    <div class=\"footer\">\n" +
                    "      &copy; 2025 Ваш Сервис. Все права защищены.\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>\n");
    }

    public int generateRandomSixNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
