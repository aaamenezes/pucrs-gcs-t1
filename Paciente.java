import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Usuario {

    public Paciente(String nome) {
        super(nome);
    }

    public boolean marcarAutorizacaoExameComoRealizado(AutorizacaoExame autorizacaoExame, LocalDate data) {
        if (autorizacaoExame == null) {
            return false;
        }
        autorizacaoExame.getExame().realizarExame(data, autorizacaoExame);
        return true;
    }

    // Listar autorizações exames
      public ArrayList<AutorizacaoExame> listarAutorizacaoExames(BancoAutorizacaoExames banco) {
        ArrayList<AutorizacaoExame> lista = banco.listarAutorizacaoExamesPorPaciente(this);
        lista.sort((a, b) -> a.getDataCadastro().compareTo(b.getDataCadastro()));
        return lista;
    }
}
