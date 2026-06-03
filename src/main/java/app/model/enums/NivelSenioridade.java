package app.model.enums;

public enum NivelSenioridade {

    /*
    * Como no AC1 é pedido que o cálculo do valor financeiro
    * seja feito com as horas investidas e com base no nível do funcionário
    * incluí um multiplicador para cada nível de senioridade
    */

    JUNIOR(1, 1.00), // Custo base
    PLENO(2, 1.15), // 15% acima do custo base
    SENIOR(3, 1.30), // 30% acima do custo base
    ESPECIALISTA(4, 1.50); // 50% acima do custo base

    private final int id;
    private final double fatorCusto;

    NivelSenioridade(int id, double fatorCusto) {
       this.id = id;
       this.fatorCusto = fatorCusto;
    }

    public int getId() {
        return id;
    }

    public double getFatorCusto() {
        return fatorCusto;
    }
}
