    package main.controller;

    import main.entity.Actividad;
    import main.service.ActividadService;
    import org.springframework.data.domain.Page;
    import org.springframework.security.core.authority.AuthorityUtils;
    import org.springframework.ui.Model;
    import main.entity.Usuario;
    import main.service.UsuarioService;
    import org.springframework.security.core.Authentication;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.Set;


    @Controller
    public class AllAcessController {

        private final UsuarioService usuarioService;
        private final ActividadService actividadService;





        public AllAcessController(UsuarioService usuarioService, ActividadService actividadService) {
            this.usuarioService = usuarioService;
            this.actividadService = actividadService;
        }

        // Página de login
        @GetMapping("/login")
        public String iniciarSesion(@RequestParam(value = "role", required = false) String role, Model model) {
            model.addAttribute("role", role);
            return "auth/login"; // vista login.html}
        }

        @GetMapping("/")
        public String landingPage(Model model, Authentication auth,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(required = false) String nombre) {
            if (auth != null && auth.isAuthenticated()) {
                String email = auth.getName(); // el email del login
                Usuario usuario = usuarioService.findByEmail(email)
                        .orElse(null);

                if (usuario != null) {
                    model.addAttribute("nombreUsuario", usuario.getNombre());
                } else {
                    model.addAttribute("nombreUsuario", email); // fallback
                }
            } else {
                model.addAttribute("nombreUsuario", null);
            }

            int pageSize = 4;

            Page<Actividad> actividadesPage = actividadService.getActividadesWithPaginationMain(page, pageSize, nombre);

            model.addAttribute("actividades", actividadesPage);
            model.addAttribute("actividad", new Actividad());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", actividadesPage.getTotalPages());
            model.addAttribute("filtroNombre", nombre); // para mantener el valor del input


            return "main";
        }



        // Página de inicio
        @GetMapping("/index")
        public String verPaginaDeInicio(Model model) {
            /*model.addAttribute("usuarios", usuarioService.listarUsuarios());*/

            return "admin/index";
        }


    }
