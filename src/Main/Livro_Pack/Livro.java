package Main.Livro_Pack;


import Main.Connection.ConexaoBanco.Querys_System.LivroConnection.Querys_Livro;

import java.sql.Date;


public class Livro {

    private Querys_Livro querysLivro;

        public Livro(Querys_Livro querysLivro) {
            this.querysLivro = querysLivro;
        }

        private int id_livro;
        private static String Nome_do_Livro;
        private static String Autor;
        private static Date Data_Lancamento;
        private static String Genero;
        private static int quantidade;

    public Livro() {

    }
    public Livro(int id_livro, String nome_do_Livro, String autor, Date data_Lancamento, String genero, int quantidade) {
        this.id_livro = id_livro;
        this.Nome_do_Livro = nome_do_Livro;
        this.Autor = autor;
        this.Data_Lancamento = data_Lancamento;
        this.Genero = genero;
        this.quantidade = quantidade;
    }

    public static String getAutor() {
        return Autor;
    }

    public  void setAutor(String autor) {
        this.Autor = autor;
    }

    public static Date getData_Lancamento() {
        formatData(Data_Lancamento);
        return Data_Lancamento;
    }

    public void setData_Lancamento(Date data_Lancamento) {

        this.Data_Lancamento = data_Lancamento;
    }

    private static String formatData(Date dataLancamento) {
        // formatar a data para o padrão dd/MM/yyyy
        String dataFormatada = String.format("%02d/%02d/%04d", dataLancamento.getDate(), dataLancamento.getMonth()  , dataLancamento.getYear() );
        return dataFormatada;
    }

    public static String getGenero() {
        return Genero;
    }

    public  void setGenero(String genero) {
        this.Genero = genero;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public static String getNome_do_Livro() {
        return Nome_do_Livro;
    }

    public  void setNome_do_Livro(String nome_do_Livro ) {
        this.Nome_do_Livro = nome_do_Livro;
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public  void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public void capturarDadosLivro() {
    }
}

