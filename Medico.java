import java.util.ArrayList;

public class Medico extends Usuario {

    public Medico(String nome) {
        super(nome);
    }

    public boolean incluirAutorizacaoExame(BancoAutorizacaoExames banco, AutorizacaoExame autorizacaoExame) {
        return banco.adicionarAutorizacao(autorizacaoExame);
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExames(
        BancoAutorizacaoExame banco,
        Paciente paciente
    ) {
        return banco.listarAutorizacaoExames(paciente);
    }

    public ArrayList<AutorizacaoExame> listarAutorizacaoExamesPorTipo(BancoAutorizacaoExame banco, TipoExame tipoExame) {
        return banco.listarAutorizacaoExamesPorTipo(tipoExame);
    }
}
