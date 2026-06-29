package app.adapters.in.web;

import app.adapters.in.web.dto.*;
import app.adapters.in.web.mapper.WebResponseMapper;
import app.domain.port.in.CadastrarMentorDados;
import app.domain.port.in.CadastrarMentorPort;
import app.domain.port.in.CadastrarMentoradoDados;
import app.domain.port.in.CadastrarMentoradoPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ParticipanteController {

   private final CadastrarMentorPort cadastrarMentorPort;
   private final CadastrarMentoradoPort cadastrarMentoradoPort;

   public ParticipanteController(CadastrarMentorPort cadastrarMentorPort, CadastrarMentoradoPort cadastrarMentoradoPort) {
      this.cadastrarMentorPort = cadastrarMentorPort;
      this.cadastrarMentoradoPort = cadastrarMentoradoPort;
   }

   @PostMapping("/mentores")
   @ResponseStatus(HttpStatus.CREATED)
   public MentorResponse cadastrarMentor(@RequestBody CadastrarMentorRequest request){
      var dados = new CadastrarMentorDados(
              //falta pegar os dados dos mentores com os gets e criar um web response no mapper para usar como retorno
      );
              return WebResponseMapper.toResponse(cadastrarMentoradoPort.cadastrar(dados)); // implementar o método cadastrar
   }

   @PostMapping("/mentorados")
   @ResponseStatus(HttpStatus.CREATED)
   public MentoradoResponse cadastrarMentorado(@RequestBody CadastrarMentoradoRequest request){
      var dados = new CadastrarMentoradoDados(
              // aqui também falta pegar os dados dos mentores com os gets e implementar o CadastrarMentoradoDados
              WebEnumMapper.toNivelSenioridade(request.getNivelSenioridade()),
              WebEnumMapper.toSkills(request.getSkills()),
              WebEnumMapper.toSkills(request.getSkillsDesejadas())
      );
              return WebResponseMapper.toResponse(cadastrarMentoradoPort.cadastrar(dados)); // implementar método cadastrar
   }
}
