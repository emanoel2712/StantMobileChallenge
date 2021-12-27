# StantMobileChallenge
Stant Company Challenge, using The Movie Database API and more
Projeto destinado a um teste de uma vaga de desenvolvedor Android. 

(OBS: todas as alterações estão no branch de DEV)

Nesse projeto foi consumido a API do The Movie Database. 

O que foi feito:

- Listagem de filmes em cartaz, através da API.
- Opção de filtragem de filmes pelo titulo.
- Implementado banco de dados.
- Salvar os filmes já visualizados no banco de dados.
- Listagem dos filmes do banco de dados.

Bom, nesse projeto foi utilizado o conceito de clean architeture e também o padrão de arquitetura MVVM.

Algumas tecnologias utilizadas e o motivo:

- Bibliotecas do Android Jetpack (Room, LiveData, ViewModel, Navigation, Databinding, Animations)...
- Injeção de dependência com Koin
- Retrofit, para acessar a API e consumir os serviços
- Gson, para converter os JSON's das respostas dos serviços em entidades
- Coroutines, para trabalhar com funções assincronas
- Glide, para carregar imagens através de urls http e também usufruir do armazenamento em cache
- Material Design, para utilizar componentes de UI mais atualizados
- ShimmerFrameLayout, para adicionar um efeito de carregamento nas listagens do app

O APK se encontra em:
StantMobileChallenge/app/release/

<img src="/<screenshots/Screenshot_1640623320.png" width="200" height="400"/>
<img src="/<screenshots/Screenshot_1640623359.png" width="200" height="400"/>
<img src="/<screenshots/Screenshot_1640623363.png" width="200" height="400" />
<img src="/<screenshots/Screenshot_1640623367.png" width="200" height="400" />
<img src="/<screenshots/Screenshot_1640623376.png" width="200" height="400"/>
