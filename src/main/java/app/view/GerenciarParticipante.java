package app.view;

import app.exception.CampoVazioException;
import app.exception.EntradaInvalidaException;
import app.exception.ParticipanteNaoEncontradoException;
import app.service.interfaces.ParticipanteService;
import app.util.ConfirmarContinuar;
import app.util.ConsoleInput;
import app.util.ConsoleUI;

public class GerenciarParticipante {

    private final ParticipanteService participanteService;
    private final ConsultaView consultaView;

    public GerenciarParticipante(ParticipanteService participanteService, ConsultaView consultaView) {
        this.participanteService = participanteService;
        this.consultaView = consultaView;
    }

    public void editarParticipante() {
        ConsoleUI.cabecalho("\n--- Editar Participante (memória) ---");
        System.out.println("""
                1- Editar Mentor
                2- Editar Mentorado
                0- Voltar
                """);
        System.out.print("Opção: ");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                editarMentor();
                break;
            case 2:
                editarMentorado();
                break;
            case 0: {
            }
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void editarMentor() {
        consultaView.consultarMentoresEmMemoria();
        if (participanteService.listarMentoresEmMemoria().isEmpty()) {
            return;
        }
        System.out.print("Digite o ID temporário do mentor que deseja editar: ");
        long id = ConsoleInput.lerId();

        System.out.print("Novo nome do mentor: ");
        try {
            String novoNome = ConsoleInput.lerTexto();
            participanteService.editarNomeMentorEmMemoria(id, novoNome);
        } catch (CampoVazioException | ParticipanteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarMentorado() {
        consultaView.consultarMentoradosEmMemoria();
        if (participanteService.listarMentoradosEmMemoria().isEmpty()) {
            return;
        }
        System.out.print("Digite o ID temporário do mentorado que deseja editar: ");
        long id = ConsoleInput.lerId();

        System.out.print("Novo nome do mentorado: ");
        try {
            String novoNome = ConsoleInput.lerTexto();
            participanteService.editarNomeMentoradoEmMemoria(id, novoNome);
        } catch (CampoVazioException | ParticipanteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirParticipante() {
        ConsoleUI.cabecalho("\n--- Excluir Participante (memória) ---");
        System.out.println("""
                1- Excluir Mentor
                2- Excluir Mentorado
                0- Voltar
                """);
        System.out.print("Opção: ");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                excluirMentor();
                break;
            case 2:
                excluirMentorado();
                break;
            case 0: {
            }
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void excluirMentor() {
        consultaView.consultarMentoresEmMemoria();
        if (participanteService.listarMentoresEmMemoria().isEmpty()) {
            return;
        }
        System.out.print("Digite o ID temporário do mentor que deseja excluir: ");
        long id = ConsoleInput.lerId();

        if (!ConfirmarContinuar.confirmar()) {
            System.out.println("Exclusão cancelada!");
            return;
        }

        boolean removido = participanteService.removerMentorEmMemoria(id);
        if (removido) {
            System.out.println(ConsoleUI.sucesso("Mentor removido com sucesso!"));
        } else {
            System.out.println(ConsoleUI.erro("Mentor não encontrado com o ID informado."));
        }
    }

    private void excluirMentorado() {
        consultaView.consultarMentoradosEmMemoria();
        if (participanteService.listarMentoradosEmMemoria().isEmpty()) {
            return;
        }

        System.out.print("Digite o ID temporário do mentorado que deseja excluir: ");
        long id = ConsoleInput.lerId();

        if (!ConfirmarContinuar.confirmar()) {
            System.out.println("Exclusão cancelada.");
            return;
        }

        boolean removido = participanteService.removerMentoradoEmMemoria(id);
        if (removido) {
            System.out.println(ConsoleUI.sucesso("Mentorado removido da memória com sucesso!"));
        } else {
            System.out.println(ConsoleUI.erro("Mentorado não encontrado com o ID informado."));
        }
    }

    private int lerOpcao() {
        try {
            return ConsoleInput.lerNumero();
        } catch (CampoVazioException | EntradaInvalidaException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
