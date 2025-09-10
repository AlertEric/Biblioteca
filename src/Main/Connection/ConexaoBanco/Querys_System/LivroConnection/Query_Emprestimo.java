package Main.Connection.ConexaoBanco.Querys_System.LivroConnection;
import Main.Connection.ConexaoBanco.DB_biblioteca_Conection;
import static Main.ScannerUtil.getScanner;


import java.sql.PreparedStatement;
import Main.Livro_Pack.Livro;
import Main.Usuario_Pack.Usuario;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Main.Main;
import Main.emprestimoLivro.EmprestimoOBJ;


public class Query_Emprestimo {

    static Querys_Livro querysLivroObj = new Querys_Livro();



    public static void devolverLivro() {



        try (Connection connDevolucao = DB_biblioteca_Conection.getInstance().connection()) {



                // Query para deletar o emprestimo do livro
            queryListEmprestimo();
                System.out.println("Digite o ID do emprestimo que deseja devolver: ");
                int idEmprestimo = getScanner().nextInt();

                // Query para pegar o id do livro que está sendo devolvido
                PreparedStatement pstmtLivro = connDevolucao.prepareStatement("select id_livro from emprestimo where id_emprestimo = ? and id_usuario = ?;");
                pstmtLivro.setInt(1, idEmprestimo);
                pstmtLivro.setInt(2, Usuario.getIdUsuario());
                ResultSet rsLivro = pstmtLivro.executeQuery();
                rsLivro.next();
                // Query para atualizar a quantidade do livro no banco de dados
                PreparedStatement pstmtUpdateLivro = connDevolucao.prepareStatement("UPDATE livro SET Quantidade = Quantidade - 1 WHERE id_livro = ?");
                pstmtUpdateLivro.setInt(1, rsLivro.getInt("id_livro"));
                pstmtUpdateLivro.executeUpdate();

                // Executa a query de deleção do emprestimo
                PreparedStatement stmtDevolucao = connDevolucao.prepareStatement("delete  from emprestimo where id_emprestimo = ? and id_usuario = ?;");
                stmtDevolucao.setInt(1, idEmprestimo);
                stmtDevolucao.setInt(2, Usuario.getIdUsuario());
                stmtDevolucao.executeUpdate();

                PreparedStatement stmtAtualizacao = connDevolucao.prepareStatement("Set @autoid :=0 ");
                PreparedStatement stmtAtualizacaoLivro = connDevolucao.prepareStatement(" Update emprestimo set id_emprestimo = @autoid := (@autoid+1)");
                PreparedStatement stmtFinal = connDevolucao.prepareStatement("ALTER TABLE emprestimo AUTO_INCREMENT = 1");

                stmtAtualizacao.executeUpdate();
                stmtAtualizacaoLivro.executeUpdate();
                stmtFinal.executeUpdate();


                System.out.println("Livro devolvido com sucesso!");
                Main.CaseDeValidacaoUSer();

            } catch (SQLException e){
        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        e.printStackTrace();
    }
    }

    public static List<EmprestimoOBJ> queryListEmprestimo() {

        List<EmprestimoOBJ> listarEmprestimos = new ArrayList<>();

        if ("Usuario".equals(Usuario.getCargo())) {
            try (Connection ListaremprestimoUsuario = DB_biblioteca_Conection.getInstance().connection()) {
                PreparedStatement pstmtListEmprestimo = ListaremprestimoUsuario.prepareStatement(
                        "select  emp.id_emprestimo, usr.Nome as Nome_Usuario, liv.Nome_do_Livro,liv.Nome_do_Autor as Nome_Autor, liv.Genero  ,Data_de_Emprestimo,Data_de_Devolucao" +
                                " from emprestimo emp" +
                                " join usuarios usr on emp.id_usuario = usr.id_usuario " +
                                " join livro liv on emp.id_livro = liv.id_livro " +
                                " where usr.id_usuario= ?;");
                pstmtListEmprestimo.setInt(1, Usuario.getIdUsuario());
                ResultSet rsListEmprestimo = pstmtListEmprestimo.executeQuery();

                while (rsListEmprestimo.next()) {
                    System.out.println("Id do emprestimo: " + rsListEmprestimo.getInt("id_emprestimo"));
                    System.out.println("Nome do usuario: " + rsListEmprestimo.getString("Nome_Usuario"));
                    System.out.println("Nome do livro: " + rsListEmprestimo.getString("Nome_do_Livro"));
                    System.out.println("Nome do autor: " + rsListEmprestimo.getString("Nome_Autor"));
                    System.out.println("Genero do livro: " + rsListEmprestimo.getString("Genero"));
                    System.out.println("Data de emprestimo: " + rsListEmprestimo.getDate("Data_de_Emprestimo"));
                    System.out.println("Data de devolução: " + rsListEmprestimo.getDate("Data_de_Devolucao"));
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
            return listarEmprestimos;
        } else if ("Administrador".equals(Usuario.getCargo())) {
            try (Connection connAdm = DB_biblioteca_Conection.getInstance().connection()) {
                PreparedStatement pstmtAdmEmprestimo = connAdm.prepareStatement("   select  emp.id_emprestimo, usr.Nome as Nome_Usuario, liv.Nome_do_Livro,liv.Nome_do_Autor as Nome_Autor, liv.Genero  ,Data_de_Emprestimo,Data_de_Devolucao from emprestimo emp" +
                        " join usuarios usr on emp.id_usuario = usr.id_usuario " +
                        "join livro liv on emp.id_livro  = liv.id_livro" +
                        " ;");
                ResultSet rsAdmEmprestimo = pstmtAdmEmprestimo.executeQuery();
                while (rsAdmEmprestimo.next()) {
                    System.out.println("Id do emprestimo: " + rsAdmEmprestimo.getInt("id_emprestimo"));
                    System.out.println("Nome do usuario: " + rsAdmEmprestimo.getString("Nome_Usuario"));
                    System.out.println("Nome do livro: " + rsAdmEmprestimo.getString("Nome_do_Livro"));
                    System.out.println("Nome do autor: " + rsAdmEmprestimo.getString("Nome_Autor"));
                    System.out.println("Genero do livro: " + rsAdmEmprestimo.getString("Genero"));
                    System.out.println("Data de emprestimo: " + rsAdmEmprestimo.getDate("Data_de_Emprestimo"));
                    System.out.println("Data de devolução: " + rsAdmEmprestimo.getDate("Data_de_Devolucao"));
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                }

            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
                e.printStackTrace();
            }

            return listarEmprestimos;
        }

        return listarEmprestimos;
    }


    // verr pq o retorno não está indo 

    public static void queryEmprestarLivroUser() {


        try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {

            // -- Query para validar se o usuario já possui 3 livros emprestados
            PreparedStatement validaEmprestimo = conn.prepareStatement("SELECT COUNT(id_usuario)  Livro_Emprestimo  FROM emprestimo WHERE id_usuario = ?;");

            validaEmprestimo.setInt(1, Usuario.getIdUsuario());
            validaEmprestimo.executeQuery();
            ResultSet rs = validaEmprestimo.executeQuery();

            if (rs.next() ) {


                // -- Query para verificar se o livro está disponível para emprestimo
                if (rs.getInt(1) < 3) {
                    // Se o usuário não possui 3 livros emprestados, ele pode pegar outro livro
                    querysLivroObj.listarLivros();

                    System.out.println(" Digite o Numero do ID do livro que deseja emprestar: ");

                    int idLivro = getScanner().nextInt();

                    PreparedStatement pstm = conn.prepareStatement("Select Quantidade  from livro where id_livro =? ");
                    pstm.setInt(1, idLivro);
                    ResultSet rst = pstm.executeQuery();

                    if (rst.next()) {

                        if (rst.getInt("Quantidade") == 0) {
                            // Se a quantidade for 0, o livro não está disponível para emprestimo
                            System.out.println("Livro não se encontra disponível para emprestimo");
                            System.out.println("\n Por gentileza, selecione outro livro ou aguarde a devolução do mesmo!");


                        } else if (rst.getInt("Quantidade") > 0) {

                            try (Connection connEmprestimo = DB_biblioteca_Conection.getInstance().connection()) {
                                // Query para inserir o emprestimo do livro
                                PreparedStatement pstmtEmprestimo = connEmprestimo.prepareStatement("Insert into emprestimo (id_usuario,id_livro,Data_de_Emprestimo,Data_de_Devolucao) values (?,?,?,?)");
                                pstmtEmprestimo.setInt(1, Usuario.getIdUsuario());
                                pstmtEmprestimo.setInt(2, idLivro);
                                pstmtEmprestimo.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                                pstmtEmprestimo.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(25)));
                                pstmtEmprestimo.executeUpdate();

                                // Query para atualizar a quantidade do livro no banco de dados
                                PreparedStatement pstmtUpdateLivro = connEmprestimo.prepareStatement("UPDATE livro SET Quantidade = Quantidade - 1 WHERE id_livro = ?");
                                pstmtUpdateLivro.setInt(1, idLivro);
                                pstmtUpdateLivro.executeUpdate();

                                System.out.println("Livro separado para emprestimo com sucesso!");
                                System.out.println(" Por gentileza, retire o livro com a blibliotecaria, e lembre-se de devolver o livro em 25 dias! ");

                                Main.CaseDeValidacaoUSer();
                            }
                        }
                    }
                } else if (3 == rs.getInt(1)) {
                    System.out.println("\nVocê já possui 3 livros emprestados, devolva um livro para poder pegar outro!");
                    Main.CaseDeValidacaoUSer();
                }
            }


        } catch (SQLException e) {
            System.out.println("Erro ao conectaar ao banco de dados " + e.getMessage());
            e.printStackTrace();
        }

    }

}
