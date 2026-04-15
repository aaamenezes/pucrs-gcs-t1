public class Administrador extends Usuario {

    public Administrador(String nome) {
        super(nome);
    }
    public void verEstatisticas(Usuario[] todosUsuarios, int totalU, AutorizacaoExame[] todosExames, int totalE) {
        int medicos = 0;
        int pacientes = 0;
        int realizados = 0;

        for (int i = 0; i < totalU; i++) { // contagem do tipo de usuarios que a gente tem
            if (todosUsuarios[i] instanceof Medico) medicos++;
            else if (todosUsuarios[i] instanceof Paciente) pacientes++;
        }

        for (int i = 0; i < totalE; i++) { // contagem tb só que pros exames
            if (todosExames[i].isRealizado()) realizados++;
        }

        double percentual = (totalE > 0) ? ((double) realizados / totalE) * 100 : 0;
        System.out.println("Médicos cadastrados: " + medicos);
        System.out.println("Pacientes cadastrados: " + pacientes);
        System.out.println("Total de autorizações: " + totalE);
        System.out.println("Exames realizados: " + percentual + "%");
    }

    public void buscarUsuario(Usuario[] lista, int total, String termoBusca) { //busca de pacientes e medicos pelos nomes 
        System.out.println("Resultados para: " + termoBusca);
        for (int i = 0; i < total; i++) {
            if (lista[i].getNome().toLowerCase().contains(termoBusca.toLowerCase()) && 
               !(lista[i] instanceof Administrador)) {
                
                System.out.println("ID: " + lista[i].getId() + " | Nome: " + lista[i].getNome());
            }
        }
    }
}
