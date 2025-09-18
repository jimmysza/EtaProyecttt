package main.service;

import java.util.List;

import main.entity.Actividad;
import main.repository.ActividadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;


    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;

    }



    public Page<Actividad> getActividadesWithPaginationMain(int page, int size, String filtroNombre) {
        Pageable pageable = PageRequest.of(page, size);

        if (filtroNombre != null && !filtroNombre.isEmpty()) {
            return actividadRepository.findByTituloContainingIgnoreCase(filtroNombre, pageable);
        }

        return actividadRepository.findAll(pageable);
    }


    public Page<Actividad> getActividadesConPaginacionDeColaborador(int page, int size, Long idColaborador, String filtroTitulo) {
        Pageable pageable = PageRequest.of(page, size);

        if (idColaborador != null && filtroTitulo != null && !filtroTitulo.isEmpty()) {
            return actividadRepository.findByColaborador_IdColaboradorAndTituloContainingIgnoreCase(idColaborador, filtroTitulo, pageable);
        }

        if (idColaborador != null) {
            return actividadRepository.findByColaborador_IdColaborador(idColaborador, pageable);
        }

        if (filtroTitulo != null && !filtroTitulo.isEmpty()) {
            return actividadRepository.findByTituloContainingIgnoreCase(filtroTitulo, pageable);
        }

        return actividadRepository.findAll(pageable);
    }


    public Page<Actividad> getActividadesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return actividadRepository.findAll(pageable);
    }

    public List<Actividad> listarActividades() {
        return actividadRepository.findAll();
    }


    public Actividad agregarActividad(Actividad actividad){
        return actividadRepository.save(actividad);
    }



    public void deleteActivity(long id){
        actividadRepository.deleteById(id);
    }


    public Actividad busActividadId(Long id){
        return actividadRepository.findById(id).orElse(null);
    }





}
