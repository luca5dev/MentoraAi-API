package app.model.enums;

public enum NivelSenioridade {

    JUNIOR(1),
    PLENO(2),
    SENIOR(3),
    ESPECIALISTA(4);

    private final int id;

    NivelSenioridade(int id){
       this.id = id;
   }

    public int getId() {
        return id;
    }

}
