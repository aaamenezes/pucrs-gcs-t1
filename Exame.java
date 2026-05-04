import java.time.LocalDate;
import java.time.temporal.ChronoUnit; 

public class Exame{
    
    private static int id = 1;

    private AutorizacaoExame autorizacao;
    private int idExame;
    private LocalDate dataRealizacao;
    private boolean realizado;
    private TipoExame tipoExame;

    public Exame(TipoExame tipoExame){
        dataRealizacao = null;
        realizado = false;
        this.tipoExame = tipoExame;
        idExame = id;
        id++;
    }

    public int getIdExame(){return this.idExame;}
    public TipoExame getTipoExame(){return this.tipoExame;}
    public boolean isRealizado(){return this.realizado;}
    public LocalDate getDataRealizacao(){return this.dataRealizacao;}


    public void realizarExame(LocalDate dataRealizacao, AutorizacaoExame autorizacao){
        if (dataRealizacao == null || autorizacao == null)
            throw new IllegalArgumentException("Erro: um dos parâmetros é nulo.");
        if (realizado)
            throw new IllegalStateException("Erro: Este exame já foi realizado.");

        LocalDate dataAutorizacao = autorizacao.getDataCadastro();
        long diferenca = ChronoUnit.DAYS.between(dataAutorizacao, dataRealizacao);
        
        if (diferenca < 0)
            throw new IllegalArgumentException("Erro: A data de realização do exame não pode ser anterior à data da autorização do exame.");
        if (diferenca > 30)
            throw new IllegalArgumentException("Erro: O exame só pode ser realizado dentro de 30 dias.");
        
        this.dataRealizacao = dataRealizacao;
        this.autorizacao = autorizacao;
        realizado = true;
    }

    public String toString(){
        String retorno = "Id: " + this.idExame + 
                         "; Tipo de Exame: " + this.tipoExame + 
                         "; Status: " + this.realizado;
        if (autorizacao == null)
            return retorno;
        else 
            return retorno + "; Data Realizacao: " + this.dataRealizacao +
                             "; Id da autorizacao: " + this.autorizacao.getCodigo();
    }
    
}