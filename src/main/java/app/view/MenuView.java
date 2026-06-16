package app.view;


public class MenuView {
    public void exibirOpcoes() {
        System.out.println("""
                \n----------------------
                Menu Principal
                ----------------------
                1- Cadastrar Participante
                2- Cadastrar Trilha
                3- Consultas
                4- Gerenciar (Editar/Excluir)
                5- Sair
                """);
        System.out.print("Opção: ");
    }

    public void consultas() {
        System.out.println("""
                \n----------------
                Consultas
                ----------------

                1- Listar todos Participantes
                2- Listar Mentores
                3- Listar Mentorados
                4- Listar Trilhas
                0- Voltar ao Menu Principal
                """);
        System.out.print("Opção: ");
    }

    public void gerenciar() {
        System.out.println("""
                \n------------------------
                Gerenciar (Editar/Excluir)
                ------------------------
                1- Editar Trilha
                2- Excluir Trilha
                3- Editar Participante (memória)
                4- Excluir Participante (memória)
                0- Voltar ao Menu Principal
                """);
        System.out.print("Opção: ");
    }
}
