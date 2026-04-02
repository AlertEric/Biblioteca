package Main.emprestimoLivro;

import java.time.LocalDate;

public class EmprestimoOBJ {

    private  String idLivroEmprestimo;
    private  String idCliente;
    private  String dataEmprestimo;
    private  String dataDevolucao;
    private  String devolvido;
    private  int emprestimo;
    private  int quantidadeEmprestimo;

    public EmprestimoOBJ(String idLivroEmprestimo, String idCliente, String dataEmprestimo, String dataDevolucao, String devolvido, int emprestimo, int quantidadeEmprestimo) {
        this.idLivroEmprestimo = idLivroEmprestimo;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
        this.emprestimo = emprestimo;
        this.quantidadeEmprestimo = quantidadeEmprestimo;
    }

    public EmprestimoOBJ() {

    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        if(dataEmprestimo.equals(LocalDate.now())){
            this.dataEmprestimo = dataEmprestimo;
        }else {
            throw new IllegalArgumentException(" Validar data emprestimo!");
        }

    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {

        this.dataDevolucao = dataDevolucao;
    }

    public String getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(String devolvido) {
        this.devolvido = devolvido;
    }

    public String getEmprestimo() {
        return String.valueOf(emprestimo);
    }

    public void setEmprestimo(int emprestimo) {

        this.emprestimo = emprestimo;
    }

    public int getIdCliente() {
        return Integer.parseInt(idCliente);
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdLivroEmprestimo() {

        return idLivroEmprestimo;
    }

    public void setIdLivroEmprestimo(String idLivroEmprestimo) {
        this.idLivroEmprestimo = idLivroEmprestimo;
    }

    public int getQuantidadeEmprestimo() {
        return quantidadeEmprestimo;
    }

    public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
        this.quantidadeEmprestimo = quantidadeEmprestimo;
    }
}
