package app.view;

import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.service.interfaces.TrilhaService;
import app.util.ConfirmarContinuar;
import app.util.ConsoleInput;

import java.util.Optional;

public class GerenciarTrilha {

    private final TrilhaService trilhaService;
    private final ConsultaView consultaView;

    public GerenciarTrilha(TrilhaService trilhaService, ConsultaView consultaView) {
        this.trilhaService = trilhaService;
        this.consultaView = consultaView;
    }

    public void editarTrilha() {
        consultaView.consultarTrilhasPersistidas();
        System.out.println("Digite o ID da trilha que deseja editar: ");
        long id = ConsoleInput.lerId();

        Optional<TrilhaMentoria> optional = trilhaService.buscarTrilhaId(id);
        if (optional.isEmpty()) {
            System.out.println("Nenhum trilha encontrada com o ID informado.");
            return;
        }

        TrilhaMentoria trilhaMentoria = optional.get();

        System.out.println("Novo nome da trilha (ENTER para manter \"" + trilhaMentoria.getNomeDaTrilha() + "\"): ");
        String novoNome = lerTextoOptional();
        if (novoNome != null) {
            trilhaMentoria.setNomeDaTrilha(novoNome);
        }

        System.out.println("Nova duração em meses (0 para manter " + trilhaMentoria.getCicloEmMeses() + "): ");
        int novaDuracao = ConsoleInput.lerNumero();
        if (novaDuracao > 0) {
            trilhaMentoria.setCicloEmMeses(novaDuracao);
        }

        if (trilhaMentoria.getMentor() != null) {
            System.out.println("Novo nome do mentor (ENTER para manter \""
                    + trilhaMentoria.getMentor().getNome() + "\"): ");
            String nomeMentor = lerTextoOptional();
            if (nomeMentor != null) {
                trilhaMentoria.getMentor().setNome(nomeMentor);
            }
        }

        for (Mentorado mentorado : trilhaMentoria.getMentorados()) {
            System.out.println("Novo nome do mentorado " + mentorado.getNome() + " (ENTER para manter): ");
            String nomeMentorado = lerTextoOptional();
            if (nomeMentorado != null) {
                mentorado.setNome(nomeMentorado);
            }
        }

        trilhaService.editarTrilha(trilhaMentoria);
        System.out.println("Trilha editada com sucesso!");

    }

        public void excluirTrilha() {
            consultaView.consultarTrilhasPersistidas();
            System.out.println("Digite o ID da trilha que deseja excluir: ");
            long id = ConsoleInput.lerId();

            Optional<TrilhaMentoria> optional = trilhaService.buscarTrilhaId(id);
            if (optional.isEmpty()) {
                System.out.println("Trilha não encontrada com o ID informado.");
                return;
            }

            System.out.println("ATENÇÃO: Excluir a trilha também removerá o mentor e os mentorados vinculados.");
            if (!ConfirmarContinuar.confirmar()) {
                System.out.println("Exclusão cancelada!");
                return;
            }

            boolean removida = trilhaService.excluirTrilha(id);
            if (removida) {
                System.out.println("Trilha excluída com sucesso!");
            } else {
                System.out.println("Não foi possível excluir a trilha.");
            }
        }

        private String lerTextoOptional() {
            String entrada = ConsoleInput.sc.nextLine().trim();
            return entrada.isEmpty() ? null : entrada;
    }
}

