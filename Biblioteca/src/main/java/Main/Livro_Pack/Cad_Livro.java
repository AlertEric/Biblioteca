package Main.Livro_Pack;
import  Main.Connection.ConexaoBanco.Querys_System.LivroConnection.Querys_Livro;
import  Main.ScannerUtil;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Main.ScannerUtil.getScanner;


public class Cad_Livro {

    Querys_Livro Querys_Livro = new Querys_Livro();
    Livro livro = new Livro();

public void Cad_Livro()  {
    System.out.println("Cadastro de Livro:");

    System.out.println("Digite o nome do livro: ");
    ScannerUtil.getScanner().nextLine(); // Limpa o buffer do scanner
    livro.setNome_do_Livro(getScanner().nextLine());

    System.out.println("Digite o nome do autor: ");
    livro.setAutor(getScanner().nextLine());

    System.out.println("Digite o genero do livro: ");
    livro.setGenero(getScanner().nextLine());

    System.out.println("Digite a data de lançamento do livro: ( dd/MM/yyyy ) ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    livro.setData_Lancamento(Date.valueOf(LocalDate.parse(getScanner().nextLine(), formatter)));

    System.out.println("Digite a quantidade de Livros: ");
    livro.setQuantidade(Integer.parseInt(getScanner().nextLine()));

    Querys_Livro.queryCadLivro();


    }


}
