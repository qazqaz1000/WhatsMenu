# WhatsMenu
## Clean Architecture, MVVM, Dagger Hilt, DataBinding, Retrofit2, RxJava
## Kakao Search Api, Naver Search Api, KakaoMap, NaverMap
+ ### 출근전/퇴근후 시간날 때 조금씩 만들기
+ ### 위치기반으로 주변 맛집 검색하기

+ ### Presentation
  + 의존관계 : data, domain

  + di : ApiModule, DataSourceModule, NetworkModule, RepositoryModule, UseCaseModule 정의

  + view : Kakao, Naver Map 사용, DataBinding 사용

  + viewModel : 의존성 주입으로 kakaoPlaceUseCase.invoke(...) 와 같은 함수 호출로 데이터의 변화를 view에 알림



+ ### data
  + 의존관계 : domain

  + ApiInterface 정의

  + Repository 구현

+ ### domain
  + 의존관계 : X

  + Model 정의

  + UseCase 정의

  + Repository 정의


Naver의 Search 까지는 구현했으나 Map에 마커추가는 다음에


![whatsmenu](https://user-images.githubusercontent.com/23303189/160434193-2ceaeb49-9493-4d0e-82de-a867b15c1c17.gif)
