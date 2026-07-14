package app.domain.model;

public enum NivelSenioridade {

    JUNIOR(1, 1.00),
    PLENO(2, 1.15),
    SENIOR(3, 1.30),
    ESPECIALISTA(4, 1.50);

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
