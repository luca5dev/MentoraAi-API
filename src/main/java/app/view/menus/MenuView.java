package app.view.menus;


public class MenuView {
    public void exibirOpcoes() {
        System.out.println("----------------");
        System.out.println("Menu Principal");
        System.out.println("----------------");
        System.out.println("1- Cadastrar Participante");
        System.out.println("2- Cadastrar trilha de mentoria");
        System.out.println("3- Consultas");
        System.out.println("4- Sair");
    }

    public void consultas(){
        System.out.println("----------------");
        System.out.println("Consultas");
        System.out.println("----------------");
        System.out.println("1- Listar Participantes");
        System.out.println("2- Listar Mentores");
        System.out.println("3- Listar Mentorados");
    }
}
