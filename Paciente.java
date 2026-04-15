import java.util.ArrayList;
import java.util.Date;
 
public class Paciente extends Usuario {
 
    public Paciente(String id, String nome, String iniciais) {
        super(id, nome, iniciais);
    }
 
    public boolean marcarAutorizacaoExameComoRealizado(AutorizacaoExame autorizacaoExame, Date data) {
        if (autorizacaoExame == null) {
            return false;
        }
        autorizacaoExame.getExame().setRealizado(true);
        autorizacaoExame.getExame().setDataRealizacao(data);
        return true;
    }
 
    // Listar autorizações exames
    public ArrayList<AutorizacaoExame> listarAutorizacaoExames(BancoAutorizacaoExames banco) {
        return banco.listarAutorizacaoExamesPorPaciente(this);
    }
}