package br.com.abruzzo.med.voll.dominio.paciente;

import br.com.abruzzo.med.voll.api.BaseApi;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pacientes")
@RestController
@RequiredArgsConstructor
public class PacienteApi implements BaseApi {

    private final PacienteService pacienteService;

    private final PacienteMapper pacienteMapper;

    @GetMapping
    public void listarAtivos(
            @PageableDefault(sort = "pessoa.nome",direction = Sort.Direction.ASC, page = 0, size = 20)
            @ParameterObject Pageable pageable,
            @RequestBody PacienteDto filtro){
        this.pacienteService.listarPaginado(pageable, this.pacienteMapper.toEntity(filtro));
    }

}
