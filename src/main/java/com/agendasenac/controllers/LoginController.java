import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder; // Adicionar o encoder para comparar a senha
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agendasenac.modells.LoginRequest;
import com.agendasenac.modells.UserSistema;
import com.agendasenac.repository.UserSistemaRepository;
import com.agendasenac.services.AuthenticationService;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserSistemaRepository usersistema;

    @Autowired
    private PasswordEncoder passwordEncoder; // Adiciona o PasswordEncoder

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest authRequest) {
        try {
            // Autentica o usuário e gera o token
            String token = authenticationService.authenticate(authRequest.getUserEmail(), authRequest.getUserSenha());

            // Busca o usuário no banco de dados apenas pelo e-mail
            Optional<UserSistema> optionalUsuario = usersistema.findByimailUser(authRequest.getUserEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("Token", token);

            // Verifica se o usuário foi encontrado
            if (optionalUsuario.isPresent()) {
                UserSistema usuario = optionalUsuario.get();

                // Compara a senha não codificada com a senha armazenada (hash)
                if (passwordEncoder.matches(authRequest.getUserSenha(), usuario.getSenhaAcessoUser())) {
                    response.put("DadosUser", usuario);
                } else {
                    response.put("DadosUser", "Senha incorreta");
                }
            } else {
                response.put("DadosUser", "Usuário não encontrado");
            }

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // Se houver erro de autenticação, retorne 401 Unauthorized
            Map<String, Object> response = new HashMap<>();
            response.put("Acesso", "Negado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
