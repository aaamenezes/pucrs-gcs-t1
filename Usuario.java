public abstract class Usuario {
    private final String nome;
    private final String iniciais;
    private final int id;
    private static int incrementoId = 1;

    public Usuario(String nome) {
        this.nome = nome;
        this.iniciais = geradorIniciais(nome);
        this.id = incrementoId++; // id autoincrementado
    }

    private String geradorIniciais(String nome) {
        String[] partesNome = nome.split(" ");
        StringBuilder inicial = new StringBuilder();
        for (String parte : partesNome) {
            if (!parte.isEmpty()) {
                inicial.append(parte.charAt(0));
            }
        }
        return inicial.toString().toUpperCase();
    }

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
