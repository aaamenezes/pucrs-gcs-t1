public abstract class Usuario {
    private final String nome; //deixei final pelo o que entendi nao vamos alterar cadastro.
    private final String iniciais;
    private int id= 1;
   
    public Usuario(String nome, String iniciais) {
        this.nome = nome;
        this.iniciais = iniciais;
        this.id = id++; //id autoincrementado
    }

    //deixei uns gets aqui caso fosse necessario, qualquer coisa apaga
    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    public int getId() {
        return id;
    }

}
