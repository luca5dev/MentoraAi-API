package app.service.interfaces;

import app.model.enums.NivelSenioridade;

public interface SenioridadeService {

    NivelSenioridade buscaSenioridade(int id);
}
