package app.adapters.in.web;

import app.adapters.in.web.dto.CadastrarMentorRequest;
import app.adapters.in.web.dto.CadastrarMentoradoRequest;
import app.adapters.in.web.dto.MentorResponse;
import app.adapters.in.web.dto.MentoradoResponse;
import app.adapters.in.web.dto.WebEnumMapper;
import app.adapters.in.web.mapper.WebResponseMapper;
import app.domain.port.in.CadastrarMentorDados;
import app.domain.port.in.CadastrarMentorPort;
import app.domain.port.in.CadastrarMentoradoDados;
import app.domain.port.in.CadastrarMentoradoPort;
import app.domain.port.in.ListarParticipantesPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParticipanteController {

   private final CadastrarMentorPort cadastrarMentorPort;
   private final CadastrarMentoradoPort cadastrarMentoradoPort;
   private final ListarParticipantesPort listarParticipantesPort;

   public ParticipanteController(CadastrarMentorPort cadastrarMentorPort,
                                 CadastrarMentoradoPort cadastrarMentoradoPort,
                                 ListarParticipantesPort listarParticipantesPort) {
      this.cadastrarMentorPort = cadastrarMentorPort;
      this.cadastrarMentoradoPort = cadastrarMentoradoPort;
      this.listarParticipantesPort = listarParticipantesPort;
   }

   @PostMapping("/mentores")
   @ResponseStatus(HttpStatus.CREATED)
   public MentorResponse cadastrarMentor(@RequestBody CadastrarMentorRequest request) {
      var dados = new CadastrarMentorDados(
              request.getNome(),
              WebEnumMapper.toNivelSenioridade(request.getNivelSenioridade()),
              WebEnumMapper.toSkills(request.getSkills()),
              request.getValorHora(),
              request.getHorasDedicadas(),
              request.getMaximoMentorados()
      );
      return WebResponseMapper.toResponse(cadastrarMentorPort.cadastrar(dados));
   }

   @PostMapping("/mentorados")
   @ResponseStatus(HttpStatus.CREATED)
   public MentoradoResponse cadastrarMentorado(@RequestBody CadastrarMentoradoRequest request) {
      var dados = new CadastrarMentoradoDados(
              request.getNome(),
              WebEnumMapper.toNivelSenioridade(request.getNivelSenioridade()),
              WebEnumMapper.toSkills(request.getSkills()),
              request.getValorHora(),
              request.getHorasDedicadas(),
              WebEnumMapper.toSkills(request.getSkillsDesejadas())
      );
      return WebResponseMapper.toResponse(cadastrarMentoradoPort.cadastrar(dados));
   }

   @GetMapping("/mentores")
   public List<MentorResponse> listarMentores() {
      return listarParticipantesPort.listarMentores().stream()
              .map(WebResponseMapper::toResponse)
              .toList();
   }

   @GetMapping("/mentorados")
   public List<MentoradoResponse> listarMentorados() {
      return listarParticipantesPort.listarMentorados().stream()
              .map(WebResponseMapper::toResponse)
              .toList();
   }
}
