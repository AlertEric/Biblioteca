package Main;
import Main.Connection.ConexaoBanco.Querys_System.LivroConnection.Query_Emprestimo;
import Main.Connection.ConexaoBanco.Querys_System.LivroConnection.Querys_Livro;
import Main.Connection.ConexaoBanco.Querys_System.UsuarioConnection.Querys_Usuario;
import Main.Livro_Pack.Livro;
import Main.Livro_Pack.Cad_Livro;
import Main.Usuario_Pack.Cad_Login;
import Main.Usuario_Pack.Usuario;
import static Main.Connection.ConexaoBanco.Querys_System.LivroConnection.Query_Emprestimo.queryListEmprestimo;
import static Main.ScannerUtil.getScanner;
import static java.lang.System.exit;


public class Main {
    static Cad_Login CadAux = new Cad_Login();

    public static void main(String[] args) {

        Querys_Livro querysLivro = new Querys_Livro();
        Livro livroInstance = new Livro(querysLivro);
        querysLivro.setLivroInstance(livroInstance);

        Querys_Usuario querysUsuario = new Querys_Usuario();
        Usuario usuario = new Usuario(querysUsuario);
        querysUsuario.setUsuario(usuario);


        // Texto de boas vindas
        System.out.println("\nOlá bem-vindo a grande biblioteca!");
        System.out.println("\nDigite qual opção deseja:\n" + "1 - Login\n" + "2 - Cadastar\n" + "3 - Sair");

        // string de erro para não  repetir o texto
        String texto = "\nErro caracter não permitido \n" + "\nDigite qual opção deseja:" + "\n1 - Login\n" + "2 - Cadastar\n" + "3 - Sair";

        int opcao = 0;
        // Loop para verificar se a opção digitada é válida
        do {
            // Verifica se o valor digitado é um inteiro
            if (getScanner().hasNextInt()) {
                opcao = getScanner().nextInt();

                if (opcao < 0 || opcao > 3) {
                    System.out.println("Opção inválida, digite novamente");

                } else {

                    /*Switch case para verificar a opção digitada
                     *Nesse switch case é chamado o metodo login da classe Usuario, em caso de login
                     *Nesse switch case é chamado o metodo cadastroDados da classe Usuario, se o usuario desejar se cadastrar
                     */
                    switch (opcao) {

                        case 1:

                            // chama o metodo login da classe Usuario
                            Cad_Login.loginUsuario();

                            break;

                        case 2:

                            // chama o metodo cadastroDados da classe Usuario

                            CadAux.cadastroDados();

                            break;

                        case 3:
                            System.out.println(" Obrigado por acessa a nossa biblioteca");

                            break;

                        default:
                            System.out.println("Opção inválida, digite novamente");
                    }
                }

                // Caso o valor digitado não seja um inteiro
            } else {
                System.out.println(texto);
                getScanner().nextLine();
            }

            // Loop para verificar se a opção digitada é válida
        } while (opcao != 3);
    }

    public static void CaseValidacaoAdm() {
        Querys_Livro ExcQuery = new Querys_Livro();
        Cad_Livro cadastramento = new Cad_Livro();


        String texto = "1 - Cadastrar Livro\n" + "2 - Excluir Livro\n" + "3 - Atualizar Livro\n" + "4 - Emprestimos\n" + "5 - Atualizar Usuario\n" + "6 - Sair\n";

        System.out.println("Digite qual opção deseja:\n" + texto);

        int opcaoAdm = 0;
        do {

            if (getScanner().hasNextInt()) {
                opcaoAdm = getScanner().nextInt();

                if (opcaoAdm < 0 || opcaoAdm > 6) {

                    System.out.println("Opção inválida, digite novamente");

                } else {

                    switch (opcaoAdm) {
                        case 1:

                            cadastramento.Cad_Livro();

                            break;
                        case 2:

                            ExcQuery.deletLivro();

                            break;
                        case 3:

                            ExcQuery.atualizarLivro();

                            break;
                        case 4:
                            queryListEmprestimo();


                            break;
                        case 5:

                            Querys_Usuario.atualizarUsuario();

                            break;

                        case 6:
                            System.out.println("Obrigado por acessar a nossa biblioteca");
                            exit(0);
                            break;


                        default:
                            System.out.println("Opção inválida, digite novamente\n");
                    }
                }
            } else {
                System.out.println("Escolha entre:\n" + texto);

                getScanner().nextLine();
            }

        } while (opcaoAdm != 6);
    }

    public static void CaseDeValidacaoUSer() {


        System.out.println("\nOlá Caro Aluno");

        System.out.println("Digite qual opção deseja:\n" + "1 - Emprestimo de Livro\n" + "2 - Devolvolução Livro\n" + "3 - Sair");
        int opcaoUsuario = getScanner().nextInt();
        do {


            switch (opcaoUsuario) {
                case 1:

                    Query_Emprestimo.queryEmprestarLivroUser();

                    break;
                case 2:
                    Query_Emprestimo.devolverLivro();

                    break;
                case 3:

                    System.out.println("Obrigado por acessar a nossa biblioteca! \nBoa leitura!");
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente");
            }
        } while (true);

    }

}
