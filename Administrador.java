
import java.util.ArrayList;

public class Administrador extends Usuario {

    public Administrador(String nome) {
        super(nome);
    }

    public String verEstatisticas(BancoUsuarios bancoUsuarios, BancoAutorizacaoExames bancoExames) {
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
        String estatisticas = "Médicos cadastrados: " + medicos;
        estatisticas += "\n";
        estatisticas += "Pacientes cadastrados: " + pacientes;
        estatisticas += "\n";
        estatisticas += "Total de autorizações: " + totalE;
        estatisticas += "\n";
        estatisticas += "Exames realizados: " + percentual + "%";
        return estatisticas;
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
