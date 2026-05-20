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

    public static NivelSenioridade buscaId(int id){
       for (NivelSenioridade nivel : NivelSenioridade.values()){
           if (nivel.getId() == id){
               return nivel;
           }
       }
       throw new IllegalArgumentException("Id inválido");
    }
}
