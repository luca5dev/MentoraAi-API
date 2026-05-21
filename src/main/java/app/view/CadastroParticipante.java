package app.view;

import app.controller.menuController.MenuPrincipalController;
import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;

public class CadastroParticipante {

    public static UsuarioCadastroDTO cadastrarParticipante() {

        UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
        System.out.println("1- Mentor/2- Mentorado");
        dto.setOpcao(ConsoleInput.lerNumero());

        System.out.println("Nome: ");
        dto.setNome(ConsoleInput.lerTexto());
        System.out.println("Nivel de Senioridade (1- Junior 2- Pleno 3- Senior 4- Especialista): ");
        dto.setNivelSenioridade(NivelSenioridade.buscaId(ConsoleInput.lerNumero()));


        System.out.println("Skills: ");
        System.out.print("1- Java 2- Spring 3- SQL\n");
        System.out.print("4- Git 5- Docker 6- AWS\n");
        System.out.print("7- Angular 8- React 9- Postgresql\n");
        System.out.print("10- HTML 11- CSS\n");
        dto.adicionarSkill(Skill.buscaSkill(ConsoleInput.lerNumero()));

        return dto;
    }
}
