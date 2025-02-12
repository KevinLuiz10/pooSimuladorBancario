Este projeto foi desenvolvido durante a matéria de Programação Orientada a Objetos da Universidade Tecnológica Federal do Paraná - Campus Cornélio Procópio.

A linguagem utilizada foi Java e o desenvolvimento se deu através da IDE Apache Netbeans 24.

O projeto possui como objetivo simular o funcionamento de um banco, permitindo o cadastro de clientes.<br>
É possível a criação de até 1 conta de cada tipo para cada cliente. Neste projeto existem 3 tipos de conta bancária, sendo elas:<br>
  .Conta Corrente: Saques e depósitos ilimitados, não possui taxa administrativa;<br>
  .Conta Poupança: Depósitos ilimitados, 3 saques diários, não possui taxa administrativa, possui um rendimento mensal;<br>
  .Conta Investimento: Saques e depósitos ilimitados, possui taxa administrativa, não possui rendimento mensal.<br>

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
EXPLICAÇÃO DO QUE É UMA CONTA INVESTIMENTO E O COMPORTAMENTO ADOTADO PARA A ELABORAÇÃO DESTE PROJETO

Uma conta do tipo investimento, na vida real, pode se referir a vários
tipos de investimento, como: ações, fundos de investimento, derivativos, etc.
Para a elaboração deste sistema, será considerado o comportamento de um investimento em ações.

Existem vários tipos de ações, porém, de forma simplista, tem-se lucro neste tipo de investimento através da valorização das ações.

Fórmula geral para valorização:<br>
Rendimento=Quantidade de Ações × (Preç​o Atual − Preç​o de Compra)<br>
Para ter este rendimento, o cliente estaria vendendo suas ações pelo preço atual delas<br>
Desta forma, seu lucro é exatamente a diferença entre o valor pelo qual ele venderá suas ações e o <br>
valor investido originalmente.<br>


Para este sistema, em carater ilustrativo, o valor da ação terá uma valorização de 0,05% ao mês.
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

As funcionalidades do sistema são:<br>
  .Cadastro de clientes;<br>
  .Criação de contas bancárias para cada cliente;<br>
  .consulta, alteração e exclusão de dados dos clientes cadastrados;<br>
  .Saque, depósito, compra e venda de ações;<br>
  .Relatório de todos os clientes cadastrados e seus respectivos dados pessoais.<br>

Os dados cadastrados são armazenados localmente através de um Array List presente na classe GerCliente.java.<br>
A classe GerCliente e as classes que se referem aos JFrame Form (elementos criados com o auxílio da IDE para o funcionamento da interface gráfica) apresentam o <br>
padrão de design singleton, para que sejam instanciados apenas uma vez durante o uso da aplicação.<br>

abaixo se encontra o diagrama de classes criado no início do projeto. Nesta etapa, não havia sido incluída a funcionalidade de gerenciamento local dos dados
(classe GerCliente) e nem as classes referentes aos elementos da interface gráfica.

![Diagrama de classes](https://github.com/user-attachments/assets/36f6a065-998c-40f0-a59d-e344e2c30cb8)

