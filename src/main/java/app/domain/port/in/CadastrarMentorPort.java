package app.domain.port.in;

import app.domain.model.Mentor;

public interface CadastrarMentorPort {
	Mentor cadastrar(CadastrarMentorDados dados);
}
