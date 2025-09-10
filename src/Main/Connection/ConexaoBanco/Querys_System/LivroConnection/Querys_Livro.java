package Main.Connection.ConexaoBanco.Querys_System.LivroConnection;

import Main.Connection.ConexaoBanco.DB_biblioteca_Conection;
import Main.Main;
import Main.Livro_Pack.Livro;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Main.ScannerUtil.getScanner;


public class Querys_Livro {

    public Querys_Livro() {
    }

    private Livro livroDD = new Livro();

    public void setLivroInstance(Livro livroInstance) {
        this.livroDD = livroInstance;
    }

    public void queryCadLivro() {


        try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO livro (Nome_do_Livro, Genero, Nome_do_Autor, Data_de_Lancamento, Quantidade) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, Livro.getNome_do_Livro());
            pstmt.setString(2, Livro.getGenero());
            pstmt.setString(3, Livro.getAutor());
            pstmt.setDate(4, Livro.getData_Lancamento());
            pstmt.setInt(5, Livro.getQuantidade());
            pstmt.executeUpdate();

            System.out.println("\nLivro cadastrado com sucesso\n\n");

            System.out.println("Deseja cadastrar outro livro?" + " 1 - Sim" + " 2 - Não");

            // Limpa o buffer do scanner

            int opcao = getScanner().nextInt();

            if (opcao == 1) {
                queryCadLivro();
            } else {
                System.out.println("Livro cadastrado com sucesso\n\n");
                Main.CaseValidacaoAdm();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            e.printStackTrace();
        }

    }


    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {


            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM livro ");
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\n ID: " + rs.getInt("id_livro") + " | Nome do Livro: " + rs.getString("Nome_do_Livro") + " | Autor: " + rs.getString("Nome_do_Autor") + " | Data de Lançamento: " + rs.getDate("Data_de_Lancamento") + " | Genero: " + rs.getString("Genero") + " | Quantidade: " + rs.getInt("Quantidade")+" |");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            }


        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
            e.printStackTrace();

        }
        return livros;

    }


    public void deletLivro() {

        int opcao = 0;

        do {
            listarLivros();

            System.out.println("Digite o id do livro que desejal deletar");
            int idLivro = getScanner().nextInt();

            try (Connection conn = DB_biblioteca_Conection.getInstance().connection();
                 // Cria uma conexão com o banco de dados
                 // e prepara a consulta SQL para deletar o livro
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM livro WHERE id_livro = ?"); PreparedStatement stmt = conn.prepareStatement("Set @autoid :=0"); PreparedStatement stmt1 = conn.prepareStatement("UPDATE livro SET id_livro = @autoid := (@autoid+1)"); PreparedStatement stmt2 = conn.prepareStatement("ALTER TABLE livro AUTO_INCREMENT = 1")) {

                // Prepara a consulta SQL para deletar o livro
                pstmt.setInt(1, idLivro);
                pstmt.executeUpdate();
                stmt.executeUpdate();
                stmt1.executeUpdate();
                stmt2.executeUpdate();


                //executa a consulta
                System.out.println("Livro deletado com sucesso!");


            } catch (SQLException e) {
                System.out.println("Erro ao deletar livro : " + e.getMessage());
                e.printStackTrace();
            }
            // Pergunta se o usuário deseja deletar outro livro
            System.out.println("\n");
            System.out.println("Deseja deletar outro livro?" + " 1 - Sim" + " 2 - Não");
            if (!getScanner().hasNextInt()) {
                getScanner().next();
                continue; // Volta para o início do loop
            }

            opcao = getScanner().nextInt();
        } while (opcao == 1);
        Main.CaseValidacaoAdm();
    }


    // Metodo para atualizar um livro
    public void atualizarLivro() {

        listarLivros();

        int opcao = 0;
        do {
            System.out.println("\nDigite o id do livro que deseja atualizar");
            while (!getScanner().hasNextInt()) {
                System.out.println("Valor inválido! Digite um número inteiro:");
                getScanner().next(); // Consome o valor inválido
            }
            int id = getScanner().nextInt();
            getScanner().nextLine(); // Limpa o buffer

            String Tabela = "";


            System.out.println("Digite qual campo deseja atualizar: 1 - Nome do Livro, 2 - Autor, 3 - Data de Lançamento, 4 - Genero, 5 - Quantidade, 6 - Sair");
            while (!getScanner().hasNextInt()) {
                System.out.println("Valor inválido! Digite um número inteiro:");
                getScanner().next(); // Consome o valor inválido
            }
            opcao = getScanner().nextInt();
            getScanner().nextLine();

            switch (opcao) {


                case 1:
                    Tabela = "Nome_do_Livro";
                    System.out.println("Digite o novo nome do livro");
                    break;

                case 2:
                    Tabela = "Nome_do_Autor";
                    System.out.println("Digite o novo nome do autor");

                    break;

                case 3:
                    Tabela = "Data_de_Lancamento";
                    System.out.println("Digite a nova data de lançamento (dd/MM/yyyy):\"");

                    break;

                case 4:
                    Tabela = "Genero";
                    System.out.println("Digite o novo genero do livro");

                    break;

                case 5:
                    Tabela = "Quantidade";
                    System.out.println("Digite a nova quantidade de livros");

                    break;

                case 6:

                    Main.CaseValidacaoAdm();
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente");
                    continue; // Volta para o início do loop

            }
            // Limpa o buffer do scanner
            if (opcao == 1 || opcao == 2 || opcao == 4 || opcao == 5) {

                if (!getScanner().hasNextLine()) {
                    System.out.println("Valor inválido! Digite novamente:");
                    getScanner().toString();
                    continue; // Volta para o início do loop
                }
                var update = getScanner().nextLine();


                try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE livro SET  " + Tabela + " = ? where id_livro = ?");
                    // Prepara a consulta SQL para atualizar o livro

                    pstmt.setString(1, update);
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();

                    System.out.println("\nLivro atualizado com sucesso\n\n");


                } catch (SQLException e) {
                    System.out.println("Erro ao atualizar livro: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (opcao == 3) {
                String dataLancamento = "";
                try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataLancamento = Date.valueOf(java.time.LocalDate.parse(getScanner().nextLine(), formatter)).toString();

                    PreparedStatement pstmt = conn.prepareStatement("UPDATE livro SET " + Tabela + " = ? where id_livro = ?");
                    pstmt.setDate(1, Date.valueOf(dataLancamento));
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();

                    System.out.println("\nLivro atualizado com sucesso\n\n");

                } catch (Exception e) {
                    System.out.println("Erro ao atualizar livro: " + e.getMessage());
                    e.printStackTrace();

                }
            }

            System.out.println("Deseja atualizar outro livro?" + " 1 - Sim" + " 2 - Não");

            while (!getScanner().hasNextInt()) {
                System.out.println("Valor inválido! Digite um número inteiro:");
                getScanner().next(); // Consome o valor inválido
            }
            var teste = getScanner().nextInt();
            // volta para a tela de cadastro caso deseje cadastrar outro livro, ou para a tela principal caso deseje sair.

            if (teste == 1) {
                atualizarLivro();
            } else if (teste == 2) {
                Main.CaseValidacaoAdm();
            }

        } while (opcao != 6);
    }
}