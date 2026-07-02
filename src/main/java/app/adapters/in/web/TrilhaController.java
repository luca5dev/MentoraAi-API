package app.adapters.in.web;

import app.adapters.in.web.dto.CriarTrilhaRequest;
import app.adapters.in.web.dto.TrilhaResponse;
import app.adapters.in.web.mapper.WebResponseMapper;
import app.domain.port.in.CriarTrilhaDados;
import app.domain.port.in.CriarTrilhaPort;
import app.domain.port.in.ListarTrilhasPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
public class TrilhaController {

   private final CriarTrilhaPort criarTrilhaPort;
   private final ListarTrilhasPort listarTrilhasPort;


   public TrilhaController(CriarTrilhaPort criarTrilhaPort, ListarTrilhasPort listarTrilhasPort) {
      this.criarTrilhaPort = criarTrilhaPort;
      this.listarTrilhasPort = listarTrilhasPort;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public TrilhaResponse criar(@RequestBody CriarTrilhaRequest request){
      var dadosCriar = new CriarTrilhaDados(request.getNome(), request.getCicloEmMeses(), request.getIdMentor(), request.getIdsMentorados()
      );
      return WebResponseMapper.toResponse(criarTrilhaPort.executar(dadosCriar));
   }

   @GetMapping
   public List<TrilhaResponse> listar(){
      return listarTrilhasPort.listar().stream().map(WebResponseMapper::toResponse).toList();
   }
}
