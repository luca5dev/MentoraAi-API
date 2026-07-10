package app.domain.port.in;

import app.domain.model.Mentorado;

public interface CadastrarMentoradoPort {
	Mentorado cadastrar(CadastrarMentoradoDados dados);
}
