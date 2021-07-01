Este é o meu aplicativo mais completo. Fiz ele me baseando no desafio em Android da empresa ioasys (https://bitbucket.org/ioasys/empresas-android/src/master/) Ele é composto por 3 telas:

1. Tela login: 
Para fazer login no aplicativo utilize o email: testeapple@ioasys.com.br e senha: 12341234. 
Nessa tela foram tratados os casos de erro em que o email e a senha foram inválidos. Foi utilizado o padrão OAuth 2.0 e no caso de sucesso do login a api retornou 3 custom headers (access-token, uid, client).

2. Tela de listagem das empresas: 
Nessa tela foi feita uma requisição para obter a lista de empresas e utilizado uma endpoint para filtrar a mesma. 
Foram utilizados os headers obtidos da tela anterior para autenticar essa requisição.

3. Tela de descrição de uma empresa: 
Nessa tela são exibidos em detalhe as informações de uma empresa clicada na lista da tela anterior.
