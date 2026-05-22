package app.view;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.enums.Skill;

public class CadastroTrilha {
    public static TrilhaMentoriaDTO cadastrarTrilha(){

        // To do
        //criar uma condicional de lista de mentores e mentorados,
        // se estiver vazia não da pra criar a trilha
        TrilhaMentoriaDTO dto = new TrilhaMentoriaDTO();

        //ListarMentores
        //Ler o id do Mentor
        System.out.println("Mentor: ");
        dto.setIdMentor(ConsoleInput.lerNumero());

        System.out.print("Digite da trilha: ");
        dto.setNome(ConsoleInput.lerTexto());


        System.out.print("Duração em horas: ");
        dto.setDuracaoMeses(ConsoleInput.lerNumero());

        //falta criar metodo pra adicionar skills na lista
        // (limte: 3 Skills por trilha)
        System.out.println("Quais skills a trilha vai ter?");
        System.out.println("Skills: ");
        System.out.print("1- Java 2- Spring 3- SQL\n");
        System.out.print("4- Git 5- Docker 6- AWS\n");
        System.out.print("7- Angular 8- React 9- Postgresql\n");
        System.out.print("10- HTML 11- CSS\n");
        dto.addSkill(Skill.buscaSkill(ConsoleInput.lerNumero()));




        return dto;
    }



}
