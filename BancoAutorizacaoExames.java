
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
            System.out.println("Erro: A data inserida é anterior a solicitação ");
            return false;
        }

        long dias = ChronoUnit.DAYS.between(dataCadastro, data);

        if (dias > 30) {
            System.out.println("Erro: A data inserida é posterior a 30 dias de solicitação");
            return false;
        }

        autorizacaoExame.getExame().realizarExame(data, autorizacaoExame);

        System.out.println("Autorização do exame realizado com sucesso");
        return true;
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExamesPorPaciente(Paciente paciente) {
        ArrayList<AutorizacaoExame> lista = new ArrayList<>();

        for (AutorizacaoExame a : this.autorizacaoExames) {
            if (a.getPaciente().equals(paciente)) {
                lista.add(a);
            }
        }

        return lista;
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExamesPorMedico(Medico medico) {
        ArrayList<AutorizacaoExame> lista = new ArrayList<>();

        for (AutorizacaoExame a : this.autorizacaoExames) {
            if (a.getMedicoSolicitante().equals(medico)) {
                lista.add(a);
            }
        }

        return lista;
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExames() {
        ArrayList<AutorizacaoExame> lista = new ArrayList<>();

        for (AutorizacaoExame autorizacao : this.autorizacaoExames) {
            lista.add(autorizacao);
        }
        return lista;
    }

    public boolean adicionarAutorizacao(AutorizacaoExame autorizacao) {
        return this.autorizacaoExames.add(autorizacao);
    }
}
