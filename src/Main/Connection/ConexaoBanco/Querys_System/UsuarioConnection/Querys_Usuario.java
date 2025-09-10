package Main.Connection.ConexaoBanco.Querys_System.UsuarioConnection;

import Main.Usuario_Pack.Cad_Login;
import Main.Usuario_Pack.Usuario;
import Main.Main;
import Main.Connection.ConexaoBanco.DB_biblioteca_Conection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Main.emprestimoLivro.EmprestimoOBJ;

import static Main.ScannerUtil.getScanner;


public class Querys_Usuario {

    static EmprestimoOBJ emprestimoUser = new EmprestimoOBJ();
    private Usuario CadUser;
    private Cad_Login Cadatramento;

    public Querys_Usuario() {
        // Inicialização sem criar uma nova instância de Usuario
    }

    public void setUsuario(Usuario usuario) {
        this.CadUser = usuario;
    }

    public static void Query_Cad_Usuario() {


        // Conexão com o banco de dados para inserir os dados do usuario

        try (Connection connCad = DB_biblioteca_Conection.getInstance().connection()) {
            // Query para inserir os dados do usuario no banco de dados
            String sql = "";

            if (Usuario.getCargo().equals("Administrador")) {

                sql = ("INSERT INTO  Funcionario (Nome, Login_Usuario, cargo, CPF, Data_Nascimento, Senha )" + " VALUES (?,?,?,?,?,?)");

            } else if (Usuario.getCargo().equals("Usuario")) {
                sql = ("INSERT INTO  usuarios (Nome, Login_Usuario, cargo, CPF, Data_Nascimento, Senha )" + " VALUES (?,?,?,?,?,?)");

            } else {
                throw new IllegalArgumentException("Cargo inválido");
            }
            try (PreparedStatement pstmt = connCad.prepareStatement(sql)) {

                pstmt.setString(1, Usuario.getNome());
                pstmt.setString(2, Usuario.getUserLogin());
                pstmt.setString(3, Usuario.getCargo());
                pstmt.setString(4, Usuario.getCpf());
                pstmt.setDate(5, Date.valueOf(Usuario.getDataNascimento()));
                pstmt.setString(6, Usuario.getSenha());
                pstmt.executeUpdate();

                System.out.println("\nCadastro efetuado com sucesso!\n");
                System.out.println("Por favor, faça o login para acessar a biblioteca\n");
            }

        }catch (SQLException e) {
            System.out.println();
            e.printStackTrace();
        }
        Query_Login();
    }

    public static void Query_Login() {

        // Conexão com o banco de dados para verificar se o usuario existe
        try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {


            // Query para verificar se o usuario existe
            String queryUsuario = "SELECT Login_Usuario, Senha, id_usuario FROM usuarios  WHERE Login_Usuario = ? AND Senha = ?";
            PreparedStatement stmtUsuario = conn.prepareStatement(queryUsuario);
            stmtUsuario.setString(1, Usuario.getNomeUsuariolog());
            stmtUsuario.setString(2, Usuario.getSenhaDeUsuariolog());

            // Executa a query para verificar se o usuario existe e se a senha está correta

            var rsUsuario = stmtUsuario.executeQuery();

            // Verifica se o usuario existe e se a senha está correta
            // Se o usuario existir e a senha estiver correta, faz o login
            // Se o usuario não existir ou a senha estiver incorreta, solicita o login novamente
            if (rsUsuario.next()) {
                Usuario.setIdUsuario(rsUsuario.getInt("id_usuario"));
                Usuario.setCargo("Usuario");
                System.out.println("Login efetuado com sucesso!\n");
                Main.CaseDeValidacaoUSer();

            } else {
                // Query para verificar se o funcionario existe

                String queryFuncionario = "SELECT Login_Usuario, Senha, id_funcionario FROM Funcionario WHERE Login_Usuario = ? AND Senha = ? ";
                PreparedStatement stmtFuncionario = conn.prepareStatement(queryFuncionario);
                stmtFuncionario.setString(1, Usuario.getNomeUsuariolog());
                stmtFuncionario.setString(2, Usuario.getSenhaDeUsuariolog());

                // Executa a query para verificar se o funcionario existe e se a senha está correta

                var rsFuncionario = stmtFuncionario.executeQuery();

                if (rsFuncionario.next()) {

                    Usuario.setIdUsuario(rsFuncionario.getInt("id_funcionario"));

                    System.out.println("\nLogin efetuado com sucesso!\n");
                    Usuario.setCargo("Administrador");
                    Main.CaseValidacaoAdm();
                } else {
                    System.out.println("Erro de acesso!\n");
                    Cad_Login.loginUsuario();
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static List<Usuario> listUsuario() {

        List<Usuario> Usuarios = new ArrayList<>();

        try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usuarios");
            var rs = pstmt.executeQuery();


            while (rs.next()) {
                for (String s : Arrays.asList("\nID: " + rs.getInt("id_usuario") + "  Nome : " + rs.getString("Nome") + " Login: " + rs.getString("Login_Usuario") +
                                "  Cargo: " + rs.getString("Cargo") + "  CPF " + rs.getString("CPF") + "  Data Nascimento: " + rs.getDate("Data_Nascimento") +
                                "  Senha: " + rs.getString("Senha"),
                        "---------------------------------------------------------------------------------------------------------------------------------------")) {
                    System.out.println(s);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        return Usuarios;
    }

        public static void atualizarUsuario() {

            int opcao;

            do {
                // Lista os usuarios cadastrados
                listUsuario();


                System.out.println("Digite o ID do usuario que deseja alterar");
                int idUsuario = getScanner().nextInt();

                System.out.println("Digite qual campo deseja alterar? 1 - Nome, 2 - Login, 3 - Senha, 4 - CPF, 5 - Data de Nascimento,  6 - Voltar para o menu anterior\n");
                opcao = getScanner().nextInt();
                var UpdateUsuario = "";
                String tabela = "";

                switch (opcao) {
                    case 1:
                        tabela = "Nome";
                        System.out.println("Digite o novo nome");

                        break;
                    case 2:
                        tabela = "Login_Usuario";
                        System.out.println("Digite o novo login");

                        break;
                    case 3:
                        tabela = "Senha";
                        System.out.println("Digite a nova senha");

                        break;
                    case 4:
                        tabela = "CPF";
                        System.out.println("Digite o novo CPF");

                        break;
                    case 5:
                        tabela = "Data_Nascimento";
                        System.out.println("Digite a nova data de nascimento");

                        break;
                    case 6:
                        Main.CaseValidacaoAdm();
                        break;
                }

                // Lê o novo valor para o campo selecionado
                // Conexão com o banco de dados para atualizar os dados do usuario


                if (opcao >= 1 && opcao <= 4) {

                    if (!getScanner().hasNextLine()) {
                        System.out.println("Valor inválido! Digite novamente:");
                        getScanner().toString();
                        continue; // Volta para o início do loop
                    }
                    getScanner().nextLine();
                    UpdateUsuario = getScanner().nextLine();


                    try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {

                        PreparedStatement pstm = conn.prepareStatement("UPDATE usuarios set  " + tabela + " = ?  Where id_usuario = ? ");
                        pstm.setString(1, UpdateUsuario);
                        pstm.setInt(2, idUsuario);
                        pstm.executeUpdate();
                        System.out.println("Usuario atualizado com sucesso!");


                    } catch (SQLException e) {
                        System.out.println("Erro ao atualizar usuario: " + e.getMessage());
                        e.printStackTrace();
                    }
                    Main.CaseValidacaoAdm();
                } else if (opcao == 5) {
                    try (Connection conn = DB_biblioteca_Conection.getInstance().connection()) {
                        PreparedStatement pstm = conn.prepareStatement("UPDATE usuarios SET Data_Nascimento = ? WHERE id_usuario = ?");
                        pstm.setDate(1, Date.valueOf(Usuario.getDataNascimento()));
                        pstm.setInt(2, idUsuario);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    System.out.println("Opção inválida, digite novamente");
                }
            } while (opcao >= 6);
        }

}
