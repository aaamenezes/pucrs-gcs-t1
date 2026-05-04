
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BancoAutorizacaoExames {

    private ArrayList<AutorizacaoExame> autorizacaoExames;

    public BancoAutorizacaoExames() {
        this.autorizacaoExames = new ArrayList<>();
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExamesPorTipo(TipoExame tipoExame) {
        ArrayList<AutorizacaoExame> lista = new ArrayList<>();

        for (AutorizacaoExame a : this.autorizacaoExames) {
            if (a.getExame().getTipoExame() == tipoExame) {
                lista.add(a);
            }
        }

        return lista;
    }

    public ArrayList<AutorizacaoExame> obterTodos() {
        return new ArrayList<>(this.autorizacaoExames);
    }

    public int obterTotal() {
        return this.autorizacaoExames.size();
    }

    public boolean marcarAutorizacaoExameComoRealizado(AutorizacaoExame autorizacaoExame, LocalDate data,
            Paciente paciente) {
        if (!autorizacaoExame.getPaciente().equals(paciente)) {
            return false;
        }

        LocalDate dataCadastro = autorizacaoExame.getDataCadastro();

        if (data.isBefore(dataCadastro)) {
            return false;
        }

        long dias = ChronoUnit.DAYS.between(dataCadastro, data);

        if (dias > 30) {
            return false;
        }

        autorizacaoExame.getExame().setRealizado(true);
        autorizacaoExame.getExame().setDataRealizacao(data);

        return true;
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExames(Usuario usuario) {
        ArrayList<AutorizacaoExame> lista = new ArrayList<>();

        for (AutorizacaoExame a : autorizacaoExames) {
            if (a.getPaciente().equals(usuario)) {
                lista.add(a);
            }
        }

        return lista;
    }

    public void adicionarAutorizacao(AutorizacaoExame autorizacao) {
        autorizacaoExames.add(autorizacao);
    }
}
