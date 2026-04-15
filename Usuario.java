public abstract class Usuario {
    private final String nome; //deixei final pelo o que entendi nao vamos alterar cadastro.
    private final String iniciais;
    private final int id;
    private static int incrementoId= 1;
   
    public Usuario(String nome) {
        this.nome = nome;
        this.iniciais = geradorIniciais(nome);
        this.id = incrementoId++; //id autoincrementado
    }

    
    private String geradorIniciais(String nome) {
        String[] partesNome = nome.split(" ");
        StringBuilder inicial = new StringBuilder();
        for (String parte : partesNome) {
            if (!parte.isEmpty()) {
                inicial.append(parte.charAt(0)); //pega a primeira letra de cada parte do nome
            }
        }
        return inicial.toString().toUpperCase(); //retorna as iniciais em maiúsculo
    }

    //deixei uns gets aqui caso fosse necessario, qualquer coisa apaga
    public String getNome() {
        return this.nome;
    }

    public String getIniciais() {
        return this.iniciais;
    }

    public int getId() {
        return this.id;
    }

}
