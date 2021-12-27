StantMobileChallenge
Stant Company Challenge, using The Movie Database API and more Projeto destinado a um teste de uma vaga de desenvolvedor Android.

Nesse projeto foi consumido a API do The Movie Database.

O que foi feito:

- Listagem de filmes em cartaz, através da API.
- Opção de filtragem de filmes pelo titulo.
- Implementado banco de dados.
- Salvar os filmes já visualizados no banco de dados.
- Listagem dos filmes do banco de dados.

Bom, nesse projeto foi utilizado o conceito de clean architeture e também o padrão de arquitetura MVVM.

Algumas tecnologias utilizadas e o motivo:

- Bibliotecas do Android Jetpack (Room, LiveData, ViewModel, Navigation, Databinding)...
- Injeção de dependência com Koin
- Retrofit, para acessar a API e consumir os serviços
- Gson, para converter os JSON's das respostas dos serviços em entidades
- Coroutines, para trabalhar com funções assincronas
- Glide, para carregar imagens através de urls http
- Material Design, para utilizar componentes de UI mais atualizados
- ShimmerFrameLayout, para adicionar um efeito de carregamento nas listagens do app 
- Ativado a ofuscação de código e encolher recursos com o Proguard, quando gerado o APK em modo release

O APK se encontra em:
StantMobileChallenge/app/release/

<img src="/screenshots/sc1.png" width="200" height="400"/>
<img src="/screenshots/sc2.png" width="200" height="400"/>
<img src="/screenshots/sc3.png" width="200" height="400" />
<img src="/screenshots/sc4.png" width="200" height="400" />
<img src="/screenshots/sc5.png" width="200" height="400"/>
