package app.service.useCase;

import app.model.enums.NivelSenioridade;
import app.service.interfaces.SenioridadeService;

public class SenioridadeUseCase implements SenioridadeService {

    @Override
    public NivelSenioridade buscaSenioridade(int id){
        for (NivelSenioridade nivel : NivelSenioridade.values()){
            if (nivel.getId() == id){
                return nivel;
            }
        }
        throw new IllegalArgumentException("Id inválido");
    }
}
