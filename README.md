# 포켓몬 앱

## 📝 프로젝트 소개
* 포켓몬 앱은 [PokeAPI](https://pokeapi.co/)를 활용하여 포켓몬 리스트와 상세정보를 보여주는 간단한 안드로이드 앱입니다.
* 전체 포켓몬 탭에서는 포켓몬 리스트를 보여줍니다.
* 히스토리 탭에서는 상세조회한 포켓몬 리스트를 최근 본 시간과 함께 보여줍니다. 히스토리 탭에서는 편집을 통해 조회했던 포켓몬을 삭제할 수 있습니다.

<br>

## 🤖 Android 기술 스택

| 카테고리         | 기술 스택                                         |
|----------------|------------------------------------------------|
| UI             | XML                                            |
| Architecture   | MVVM, Single Module                            |
| DI             | Hilt                                           |
| Network        | Retrofit                                       |
| Image          | Coil                                           |
| Asynchronous   | Coroutines, Flow                               |
| Jetpack        | ViewModel, Room                                |
| Logging        | Timber                                         |

<br>

## 📁 프로젝트 구조
이 프로젝트는 싱글 모듈(app 모듈)로 구성되어 있으며, 아래는 app 모듈 내부의 패키지 구조입니다.
```
app
├── build.gradle.kts
└── src
    └── main
        ├── AndroidManifest.xml
        └── java
            └── com.sdhong.pokemonapp
                ├── PokemonApplication.kt
                ├── base
                │   ├── BaseActivity.kt
                │   ├── BaseFragment.kt
                │   └── BaseViewHolder.kt
                ├── common
                │   ├── Formatter.kt
                │   └── IntentExtraKey.kt
                ├── datastore
                │   ├── datasource
                │   │   └── TimePreferenceDataSource.kt
                │   └── di
                │       └── DataStoreModule.kt
                ├── local
                │   ├── dao
                │   │   └── HistoryDao.kt
                │   ├── database
                │   │   └── HistoryDatabase.kt
                │   ├── di
                │   │   └── HistoryDatabaseModule.kt
                │   └── model
                │       ├── Pokemon.kt
                │       ├── PokemonDetail.kt
                │       └── MainTab.kt
                ├── remote
                │   ├── api
                │   │   └── PokemonApi.kt
                │   ├── di
                │   │   ├── PokemonApiModule.kt
                │   │   └── RetrofitModule.kt
                │   └── model
                │       ├── PokemonDetailResponse.kt
                │       └── PokemonListResponse.kt
                ├── repository
                │   ├── di
                │   │   └── PokemonRepositoryModule.kt
                │   ├── PokemonRepository.kt
                │   └── PokemonRepositoryImpl.kt
                ├── util
                │   ├── LifecycleExt.kt
                │   └── PokemonUtil.kt
                ├── view
                │   ├── AllPokemonFragment.kt
                │   ├── DetailActivity.kt
                │   ├── HistoryFragment.kt
                │   ├── HistoryViewHolder.kt
                │   ├── MainActivity.kt
                │   ├── MainAdapter.kt
                │   ├── NormalViewHolder.kt
                │   └── ViewPagerAdapter.kt
                └── viewmodel
                    ├── AllPokemonViewModel.kt
                    ├── DetailViewModel.kt
                    └── HistoryViewModel.kt
```


<br>

## 📷 스크린샷
|![Screenshot_20250501_224152_pokemon app](https://github.com/user-attachments/assets/68d49793-b99e-47b2-a7f0-1a739299eb63)|![Screenshot_20250501_224204_pokemon app](https://github.com/user-attachments/assets/eaab4977-70e5-421b-b4a8-e6556b80f593)|![Screenshot_20250501_224209_pokemon app](https://github.com/user-attachments/assets/e2c94b69-f31d-4168-aac1-28eed172bac0)|![Screenshot_20250501_224224_pokemon app](https://github.com/user-attachments/assets/bcf75392-203a-42a7-b58c-5accac98f3d9)|
|-|-|-|-|

<br>

## 📺 데모 영상
<div align="center">
  <video src="https://github.com/user-attachments/assets/4c47fb34-9783-46e8-b01f-0fb43a95337e" />
</div>
