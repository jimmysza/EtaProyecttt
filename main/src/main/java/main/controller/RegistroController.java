package main.controller;

import main.entity.Cliente;
import main.entity.Usuario;
import main.service.ClienteService;
import main.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public String mostrarRegistroCliente(Model model) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(new Usuario());
        model.addAttribute("cliente", cliente);
        return "Auth/registro_cliente";
    }


    // Procesar formulario
    @PostMapping("/cliente")
    public String registrarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.registrarCliente(cliente);
        return "redirect:/clientes/listar";
    }

}
