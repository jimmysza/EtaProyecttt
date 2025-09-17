    package main.controller;

    import org.springframework.ui.Model;
    import main.entity.Usuario;
    import main.service.UsuarioService;
    import org.springframework.security.core.Authentication;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;


    @Controller
    public class AllAcessController {

        private final UsuarioService usuarioService;


        public AllAcessController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }

        // Página de login
        @GetMapping("/login")
        public String iniciarSesion() {
            return "auth/login"; // vista login.html
        }

        @GetMapping("/")
        public String LandingPageAccesso(Model model) {
            return "main";
        }

        // Página de inicio
        @GetMapping("/index")
        public String verPaginaDeInicio(Model model) {
            /*model.addAttribute("usuarios", usuarioService.listarUsuarios());*/

            return "admin/index";
        }

    }
