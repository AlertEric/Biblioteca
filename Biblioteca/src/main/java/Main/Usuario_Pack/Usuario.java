package Main.Usuario_Pack;

import Main.Connection.ConexaoBanco.Querys_System.UsuarioConnection.Querys_Usuario;
import java.time.LocalDate;


// Classe Usuario para cadastro e login
public class  Usuario {

    //
    private  Querys_Usuario querysUsuario;
    // Construtor que recebe uma instância de Querys_Usuario
    public Usuario(Querys_Usuario querysUsuario){
        this.querysUsuario= querysUsuario;
    }

    private static int idUsuario;
    private static String nome;
    private static String cpf;
    private static String userLogin;
    private static LocalDate dataNascimento;
    private static String senha;
    public  static String cargo;
    private static String nomeUsuariolog;
    private static String senhaDeUsuariolog;



// criando um construtor

   public  Usuario( int idUsuario, String nome, String cpf,String userLogin, LocalDate dataNascimento, String senha, String cargo, String nomeUsuariolog,String senhaDeUsuariolog ) {
        Usuario.idUsuario = idUsuario;
        Usuario.nome = nome;
        Usuario.cpf = cpf;
        Usuario.userLogin = userLogin;
        Usuario.dataNascimento = dataNascimento;
        Usuario.senha = senha;
        Usuario.cargo = cargo;
        Usuario.nomeUsuariolog = nomeUsuariolog;
        Usuario.senhaDeUsuariolog = senhaDeUsuariolog;
    }


    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int IdUsuario) {

    Usuario.idUsuario = IdUsuario;

    }

    public static String getNome() {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return nome;
    }

    public static void setNome(String nome) {
        Usuario.nome = nome;
    }
    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
    if (cpf == null|| cpf.length() != 11) {

    }
        Usuario.cpf = cpf;
    }

    public static LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public static void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }

        Usuario.dataNascimento = dataNascimento;
        dataNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Usuario.senha = senha;
    }


    public static String getCargo() {
        return cargo;
    }

    public static void setCargo(String cargo) {
        Usuario.cargo = cargo;
    }
    public static String getUserLogin() {
        return userLogin;
    }
    public static void setUserLogin(String userLogin) {
        Usuario.userLogin = userLogin;
    }

    public static String getNomeUsuariolog() {
        return nomeUsuariolog;
    }

    public void setNomeUsuariolog(String nomeUsuariolog) {
        Usuario.nomeUsuariolog = nomeUsuariolog;
    }

    public static String getSenhaDeUsuariolog() {
        return senhaDeUsuariolog;
    }

    public void setSenhaDeUsuariolog(String senhaDeUsuariolog) {
        Usuario.senhaDeUsuariolog = senhaDeUsuariolog;
    }

}