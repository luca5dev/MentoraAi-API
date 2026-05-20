package app.service.useCase;

import app.model.enums.NivelSenioridade;
import app.service.interfaces.BuscaSenioridadeService;

public class BuscaSenioridadeUseCase implements BuscaSenioridadeService {

    @Override
    public NivelSenioridade buscaSenioridade(int id){
      return NivelSenioridade.buscaId(id);
    }
}
