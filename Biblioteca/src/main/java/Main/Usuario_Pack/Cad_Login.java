package Main.Usuario_Pack;
import Main.Connection.ConexaoBanco.Querys_System.UsuarioConnection.Querys_Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Main.ScannerUtil.getScanner;

public class Cad_Login {

    static Querys_Usuario querysUsuario = new Querys_Usuario();

    private static Usuario usuario;

    public Cad_Login() {
        this.usuario = new Usuario(querysUsuario);
    }

    public  void loginUsuario() {
      System.out.println("Digite seu login de usuario: ");
        usuario.setNomeUsuariolog(getScanner().next());
      System.out.println("Digite sua senha: ");
        usuario.setSenhaDeUsuariolog(getScanner().next());
        querysUsuario.Query_Login();
    }


    // metodo para cadastro de usuario
    public  void cadastroDados()  {

        System.out.println("\n\nCadastro do Acesso : \n");

        System.out.println("Digite seu Nome");
        usuario.setNome(getScanner().next());

        System.out.println("Digite seu CPF");
        usuario.setCpf(getScanner().next());

        System.out.print("Digite sua data de nascimento\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        usuario.setDataNascimento(LocalDate.parse(getScanner().next(), formatter));

        System.out.println("Digite seu login");
        usuario.setUserLogin(getScanner().next());

        System.out.println("Digite sua senha");
        usuario.setSenha(getScanner().next());

        System.out.println("Digite seu cargo: 1 - Administrador, 2 - Usuario");
        usuario.setCargo(getScanner().next());

        // if para verificar o cargo digitado
        if (Usuario.getCargo().equals("1")) {
            Usuario.setCargo("Administrador");

            // else if para verificar o cargo digitado
        } else if (Usuario.getCargo().equals("2")) {
            Usuario.setCargo("Usuario");
        } else {
            System.out.println("Opção inválida, digite novamente");
            System.out.println("Digite seu cargo: 1 - Administrador, 2 - Usuario");
            // repeti o loop de if
            getScanner().next();
        }


        querysUsuario.Query_Cad_Usuario();
    }
}
