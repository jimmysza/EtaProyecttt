package main.controller;

import main.entity.Actividad;
import main.entity.Usuario;
import main.repository.UsuarioRepository;
import main.service.ActividadService;
import main.service.ColaboradorService;
import main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/as")
public class ActividadController {

    private final ActividadService actividadService;
    private final ColaboradorService colaboradorService;
    private final UsuarioService usuarioService;


    @Autowired
    public ActividadController(ActividadService actividadService, UsuarioService usuarioService,ColaboradorService colaboradorService) {
        this.actividadService = actividadService;
        this.usuarioService = usuarioService;
        this.colaboradorService = colaboradorService;
    }




    // Agregar una actividad desde formulario
    @PostMapping("/addAct")
    public String addActivity(@ModelAttribute("actividad") Actividad actividad) {
        actividadService.agregarActividad(actividad);
        return "redirect:/actividades";
    }

    // Buscar actividad por categoría y mostrar en vista
 /*    @GetMapping("/getActByCategorie/{categorie}")
    public String getActividadByCategorie(@PathVariable("categorie") String categorie, Model model) {
        Actividad actividad = actividadService.getActividadByCategoria(categorie);
        model.addAttribute("actividad", actividad);
        return "admin/detalle-actividad"; // crea una vista detalle si quieres mostrarla
    }

    // Eliminar actividad por ID
    @GetMapping("/eliminar/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        actividadService.deleteActivity(id);
        return "redirect:/actividades";
    }

    // Reservar actividad
    @PostMapping("/reservar/{idActividad}/{idCliente}")
    public String reservar(@PathVariable Long idActividad, @PathVariable Long idCliente) {
        var actividad = actividadService.busActividadId(idActividad);
        Usuario usuario = usuarioService.buscarPorId(idCliente);

        if (actividad != null && usuario instanceof Cliente cliente) {
            actividad.getClientes().add(cliente);
            cliente.getActividadesReservadas().add(actividad);
            actividadService.crearActividad(actividad);
        }

        return "redirect:/actividades";
    } */
}
