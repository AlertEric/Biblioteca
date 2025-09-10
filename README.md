Projeto: Sistema de Gerenciamento de Biblioteca
Descrição
O sistema permitirá o gerenciamento básico de uma biblioteca, incluindo funcionalidades para adicionar livros, registrar usuários e realizar empréstimos de livros.
O foco será aplicar os pilares da POO: encapsulamento, herança, polimorfismo e abstração.
________________________________________
Requisitos Funcionais
1.	Gerenciar Livros
o	Adicionar novos livros ao sistema.
o	Consultar livros disponíveis.
o	Registrar a devolução de livros.
2.	Gerenciar Usuários
o	Cadastrar novos usuários (alunos e professores).
o	Consultar usuários cadastrados.
3.	Empréstimos
o	Permitir que usuários emprestem livros (limitado a 3 livros por vez).
o	Registrar a devolução dos livros emprestados.
o	Listar os empréstimos ativos.
________________________________________
Requisitos Não Funcionais
•	O sistema deve ser implementado com foco em boas práticas de POO.
•	Utilizar coleções Java (como ArrayList) para armazenar os dados.
•	Adicionar tratamento básico de erros (por exemplo, evitar empréstimos acima do limite ou devoluções de livros que não foram emprestados).
________________________________________
Requisitos Técnicos
1.	Criar classes que representem os objetos principais:
o	Livro (atributos como título, autor, ISBN, status de disponibilidade).
o	Usuário (atributos como nome, tipo de usuário, limite de empréstimos). 
	Aluno e Professor (exemplos de herança).
o	Empréstimo (relaciona livros e usuários com data de empréstimo).
2.	Aplicar os pilares da POO:
o	Encapsulamento: Utilizar modificadores de acesso (private, protected) e métodos getters/setters.
o	Herança: Criar uma classe base Usuario e especializar para Aluno e Professor.
o	Polimorfismo: Sobrescrever métodos, como calcular multas (professores podem ter isenção, enquanto alunos não).
o	Abstração: Definir uma interface ou classe abstrata para operações genéricas (como cadastrar e listar).
3.	Implementar uma classe Main com um menu simples para interagir com o sistema:
o	Adicionar livros.
o	Cadastrar usuários.
o	Registrar empréstimos e devoluções.
o	Listar livros e usuários.
________________________________________
Entrega Esperada
•	O código organizado em pacotes (ex.: model, service, main).
•	Utilizar comentários explicativos e seguir convenções de nomenclatura.
•	Um arquivo README explicando o funcionamento do sistema e as instruções para rodar o código.
________________________________________
Dicas para o Estagiário
•	Comece pelo diagrama de classes para planejar o sistema.
•	Desenvolva de forma iterativa: implemente uma funcionalidade por vez.
•	Use testes manuais no início (pode adicionar JUnit no futuro, se for adequado).
Se precisar de ajuda com o detalhamento do diagrama de classes ou exemplos de código, é só pedir! 😊
