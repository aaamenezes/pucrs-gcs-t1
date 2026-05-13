import java.util.ArrayList;

public class BancoUsuarios {

    private ArrayList<Usuario> usuarios;

    public BancoUsuarios() {
        usuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> obterTodos() {
        return new ArrayList<>(this.usuarios);
    }

    public int obterTotal() {
        return this.usuarios.size();
    }

    public boolean adicionarUsuario(Usuario u) {
        return this.usuarios.add(u);
    }

    public boolean incluirMedico(String nome) {
        Medico m = new Medico(nome);
        usuarios.add(m);
        return true;
    }

    public boolean incluirPaciente(String nome) {
        Paciente p = new Paciente(nome);
        usuarios.add(p);
        return true;
    }

    public boolean incluirAdministrador(String nome) {
        Administrador a = new Administrador(nome);
        usuarios.add(a);
        return true;
    }

    public Medico buscarMedicoPeloNome(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Medico) {
                Medico m = (Medico) usuarios.get(i);

                if (m.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    return m;
                }
            }
        }
        return null;
    }

    public Paciente buscarPacientePeloNome(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Paciente) {
                Paciente p = (Paciente) usuarios.get(i);

                if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    return p;
                }
            }
        }
        return null;
    }

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> aux = new ArrayList<>();
        for (Usuario u : usuarios) {
            aux.add(u);
        }
        return aux;
    }
}
