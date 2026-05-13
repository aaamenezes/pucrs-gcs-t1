import java.util.ArrayList;

public class Administrador extends Usuario {

    public Administrador(String nome) {
        super(nome);
    }

    public void verEstatisticas(BancoUsuarios bancoUsuarios, BancoAutorizacaoExames bancoExames) {
        ArrayList<Usuario> todosUsuarios = bancoUsuarios.obterTodos();
        ArrayList<AutorizacaoExame> todosExames = bancoExames.obterTodos();
        int totalE = bancoExames.obterTotal();

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

        for (AutorizacaoExame exame : todosExames) {
            if (exame.getExame().isRealizado()) {
                realizados++;
            }
        }

        double percentual = (totalE > 0) ? ((double) realizados / totalE) * 100 : 0;
        System.out.println("Médicos cadastrados: " + medicos);
        System.out.println("Pacientes cadastrados: " + pacientes);
        System.out.println("Total de autorizações: " + totalE);
        System.out.println("Exames realizados: " + percentual + "%");
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExames(Usuario usuario, BancoAutorizacaoExames bancoExames) {
        if (usuario instanceof Medico) {
            return bancoExames.listarAutorizacaoExamesPorMedico((Medico) usuario);
        } else if (usuario instanceof Paciente) {
            return bancoExames.listarAutorizacaoExamesPorPaciente((Paciente) usuario);
        }

        return null;
    }

    public Paciente buscarPacientePeloNome(String nome, BancoUsuarios bancoUsuarios) {
        ArrayList<Usuario> todos = bancoUsuarios.obterTodos();
        for (Usuario u : todos) {
            if (u instanceof Paciente && u.getNome().equalsIgnoreCase(nome)) {
                return (Paciente) u;
            }
        }
        return null;
    }

    public Medico buscarMedicoPeloNome(String nome, BancoUsuarios bancoUsuarios) {
        ArrayList<Usuario> todos = bancoUsuarios.obterTodos();
        for (Usuario u : todos) {
            if (u instanceof Medico && u.getNome().equalsIgnoreCase(nome)) {
                return (Medico) u;
            }
        }
        return null;
    }

    public boolean incluirAdministrador(String nome, BancoUsuarios bancoUsuarios) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        Administrador novoAdministrador = new Administrador(nome);
        return bancoUsuarios.adicionarUsuario(novoAdministrador);
    }

    public boolean incluirMedico(String nome, BancoUsuarios bancoUsuarios) {
        return bancoUsuarios.adicionarUsuario(new Medico(nome));
    }

    public boolean incluirPaciente(String nome, BancoUsuarios bancoUsuarios) {
        return bancoUsuarios.adicionarUsuario(new Paciente(nome));
    }
}
