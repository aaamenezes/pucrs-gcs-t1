public class Administrador extends Usuario {

    public Administrador(String nome) {
        super(nome);
    }
    
    public void verEstatisticas() {
        // Buscar dados dos bancos
        Usuario[] todosUsuarios = BancoUsuarios.obterTodos();
        int totalU = BancoUsuarios.obterTotal();
        
        AutorizacaoExame[] todosExames = BancoAutorizacaoExames.obterTodos();
        int totalE = BancoAutorizacaoExames.obterTotal();
        
        // Contagem de tipos de usuários
        int medicos = 0;
        int pacientes = 0;
        int realizados = 0;

        for (Usuario usuario : todosUsuarios) {
            if (usuario instanceof Medico) {
                medicos++;
            } else if (usuario instanceof Paciente) {
                pacientes++;
            }
        }

        for (AutorizacaoExame autorizacaoExame : todosExames) {
            if (autorizacaoExame.isRealizado() realizados++;
        }

        double percentual = (totalE > 0) ? ((double) realizados / totalE) * 100 : 0;
        System.out.println("Médicos cadastrados: " + medicos);
        System.out.println("Pacientes cadastrados: " + pacientes);
        System.out.println("Total de autorizações: " + totalE);
        System.out.println("Exames realizados: " + percentual + "%");
    }

    public Medico buscarMedicoPeloNome(String nome) {
        Usuario[] todosUsuarios = BancoUsuarios.obterTodos();
        int total = BancoUsuarios.obterTotal();
        
        for (Usuario usuario : todosUsuarios) {
            if (usuario instanceof Medico && usuario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return (Medico) usuario;
            }
        }
        return null;
    }
    
    public Paciente buscarPacientePeloNome(String nome) {
        Usuario[] todosUsuarios = BancoUsuarios.obterTodos();
        int total = BancoUsuarios.obterTotal();
        
        for (Usuario usuario : todosUsuarios) {
            if (usuario instanceof Paciente && usuario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return (Paciente) usuario;
            }
        }
        return null;
    }
    
    public boolean incluirMedico(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        Medico novoMedico = new Medico(nome);
        return BancoUsuarios.adicionarUsuario(novoMedico);
    }
    
    public boolean incluirPaciente(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        Paciente novoPaciente = new Paciente(nome);
        return BancoUsuarios.adicionarUsuario(novoPaciente);
    }
    
    public boolean incluirAdministrador(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        Administrador novoAdministrador = new Administrador(nome);
        return BancoUsuarios.adicionarUsuario(novoAdministrador);
    }
}
