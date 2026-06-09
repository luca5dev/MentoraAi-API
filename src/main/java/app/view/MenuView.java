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
                4- Sair
                """);
        System.out.print("Opção: ");
    }

    public void consultas(){
        System.out.println("""
                \n----------------
                Consultas
                ----------------

                1- Listar Participantes
                2- Listar Mentores
                3- Listar Mentorados
                4- Listar Trilhas
                """);
        System.out.print("Opção: ");
    }
}
