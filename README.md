# StantMobileChallenge
Stant Company Challenge, using The Movie Database API and more
Projeto destinado a um teste de uma vaga de desenvolvedor Android. 

Nesse projeto foi consumido a API do The Movie Database. 

O que foi feito:

- Listagem de filmes em cartaz, através da API.
- Opção de filtragem de filmes pelo titulo.
- Implementado banco de dados.
- Salvar os filmes já visualizados no banco de dados.
- Listagem dos filmes do banco de dados.

Bom, nesse projeto foi utilizado o conceito de clean architeture e também o padrão de arquitetura MVVM.

Algumas tecnologias utilizadas:

- Bibliotecas do Android Jetpack (Room, LiveData, ViewModel, Navigation, Databinding)...
- Injeção de dependência com Koin
- Retrofit para acessar a API e consumir os serviços
- Coroutines para trabalhar com funções assincronas
- Glide para carregar imagens através de urls e armazena-las em cache
- Material Design para conseguir utilizar componentes de UI mais atualizados
- ShimmerFrameLayout para adicionar um efeito de carregamento nas listagens do app
