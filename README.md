<p align=center>
<a href="https://letstdd.site"><img src="https://capsule-render.vercel.app/api?type=waving&&color=timeGradient&height=300&section=header&text=Try,%20Drive,%20Delightly!&fontSize=90" /></a>
</p>

<p align=center>🚘사용자간 차량 경험을 공유하는 웹 기반 C2C 시승 플랫폼 TDD입니다.(https://letstdd.site)</p>

## 소개
TDD 서비스는 사용자들간 시승을 공유함으로서 차량에 대한 다채로운 경험을 제공하고자 기획되었습니다.

우리는 TDD서비스를 통해 사용자들에게 차량에 대한 우수한 접근성과 다양한 경험을 확대하는 것을 목표로 하고 있습니다.

사용자들간의 시승을 공유함으로서 차량에 대한 우리는 이 불편함들을 해결하고자 했습니다. 세부적인 고민 사항들을 혼자 가지고 있지 말고, 경험을 이용해보면서 시작해보세요.

자동차 시승을 해보고 싶은 유저와 자동차를 대여해주고 이득을 취하고 싶은 유저를 매칭해주는 플랫폼입니다. 차를 구매하기 전 다양한 차를 직접 체험해 보고싶은 사용자, 실제 사용자의 실사용 후기를 듣고싶은 사용자를 매칭해줍니다.

### 누가 이용하나요?

- 주변에 시승할 곳이 마땅히 없는 사용자
- 다양한 차를 체험해보고 싶은 사용자
- 실제 사용자의 객관적인 리뷰를 듣고싶은 사용자

### 장점

- 주변에 시승할 수 있는 곳이 마땅치 않은 사람들에게 비교적 가까운 거리와 다양한 시간대에 시승할 수 있는 기회를 제공합니다.
- 내가 원하는 옵션의 차량, 나와 비슷한 환경에서 생활하는 사람의 차량을 경험할 수 있습니다.
- 실제 차량 소유자의 객관적인 리뷰를 얻을 수 있습니다.

### 확장성

지금은 "차"라는 도메인, "시승"이라는 서비스에 한정되어 있지만 이를 확장시켜 모든 물건에 대해 사용자들끼리 체험할 수 있도록 매칭시켜주는 플랫폼으로 발전할 수 있습니다.


![tdd](https://user-images.githubusercontent.com/60373714/219529450-d7e2e556-dc80-4a29-8782-46723678bac7.png)


Let’s TDD. Try, Drive, Delightly. TDD에서 T와 D를 가지고 핸들을 추상화했습니다. 전체적인 모양은 공유 핀을 형상화하였습니다.


## 주요 기능 캡쳐
(시연 영상)


## Database ERD

![image](https://user-images.githubusercontent.com/59179386/219434168-ee5f00ce-b38f-40db-b190-3a3016ed12dc.png)

## 프로젝트 특징

### Github Action과 Docker로 구성한 CI/CD

dev 브랜치에 merge되는 혹은 push되는 모든 내용은 빌드/테스트를 자동으로 수행하여 클라우드 서버(aws ec2)에 배포되고 있습니다.

Docker를 이용해 이미지 단위로 Github Action을 통해 배포 파이프라인을 구성했으며 클라우드 서버에서 컨테이너를 띄우는 것으로 배포했습니다.

→ [Github action 보러가기](https://github.com/softeerbootcamp/Team1-TDD/tree/dev/.github/workflows)

### Swagger와 함께한 프론트와의 협업

API 문서 자동화 도구인 Swagger를 이용하여 프론트엔드 개발자 분들과 협업했습니다. 

다음과 같이 코드기반 API Docs 자동생성을 통해 프론트엔드 개발자분들이 일일이 json을 면대면 혹은 코드로 확인해 볼 필요없이 협업할 수 있었습니다.

→ [swagger API Docs 보러가기](http://letstdd.site:8080/swagger-ui/index.html)

![image](https://user-images.githubusercontent.com/59179386/219435036-d79ad6c3-1b9e-47f6-9488-7133d3da8c31.png)

### Code convention과 함께하는 협업

코딩 컨벤션을 준수하면 가독성이 좋아지고 성능에 영향을 주거나 오류를 발생시키는 잠재적인 위험 요소를 줄여줘 유지보수 비용을 줄일 수 있습니다.

IntelliJ의 Code style Formatter를 이용하여 코드 컨벤션을 준수할 수 있도록 강제했고 IntelliJ의 Plugin 중 하나인 Check style 정적 코드 분석 도구를 이용하여 지정된 규칙에 어긋나는 경우 commit 시 경고를 통해 코드 컨벤션을 준수했습니다.

![image](https://user-images.githubusercontent.com/59179386/219434976-db364650-ad04-4643-82c3-1c1e49a3b697.png)

### 코드 리뷰와 함께하는 개발

코드 리뷰없이는 개발 브랜치인 dev 에 merge 할 수 없도록 강제했습니다. 

코드 리뷰를 통해 코드의 품질을 개선하고 다른 팀원들이 개발한 로직을 이해하고 협업할 수 있습니다.

→ [코드리뷰 보러가기](https://github.com/softeerbootcamp/Team1-TDD/pulls?q=is%3Apr+is%3Aclosed+label%3A%F0%9F%A6%A6back-end)

### HTTPS `SSL`

SSL 인증서는 클라이언트와 서버간의 통신을 제3자가 보증해주는 전자화된 문서입니다.

SSL프로토콜을 이용하여 서버와 클라이언트 사이의 모든데이터를 암호화 했습니다.
 
![image](https://user-images.githubusercontent.com/59179386/219434882-f944db5d-1f68-4e26-9c61-d9504929811c.png)

## ⚙️ 배포 아키텍쳐
![image](https://user-images.githubusercontent.com/59179386/219247646-fdb88bc6-32cc-45ed-a4d3-4ddf2cc2df74.png)

## 🛠 기술 스택  
### ✔️ Frond-end
<img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=black"> <img src="https://img.shields.io/badge/SCSS-CC6699?style=for-the-badge&logo=SASS&logoColor=white"> <img src="https://img.shields.io/badge/prettier-F7B93E?style=for-the-badge&logo=prettier&logoColor=white"> <img src="https://img.shields.io/badge/.env-ECD53F?style=for-the-badge&logo=.env&logoColor=black">
<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white"> <img src="https://img.shields.io/badge/googlemaps-4285F4?style=for-the-badge&logo=googlemaps&logoColor=white"> <img src="https://img.shields.io/badge/vite-646CFF?style=for-the-badge&logo=vite&logoColor=white"> <img src="https://img.shields.io/badge/eslint-4B32C3?style=for-the-badge&logo=eslint&logoColor=white"> <img src="https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=Axios&logoColor=white"> 
### ✔️ Back-end
<img src="https://img.shields.io/badge/java 11-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring data jdbc-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> 

### ✔️ Deploy
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/amazonAWS-232F3E?style=for-the-badge&logo=amazonAWS&logoColor=white"> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"> <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/githubactions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"> 

### 사용한 기술과 이유

- Typescript: Typescript는 Object의 타입을 명시할 수 있습니다. 그래서 자바스크립트를 실제로 사용하기 전에 있을만한 타입 에러들을 미리잡기 위해 사용합니다. 또 개발도구에게 개발자가 의도한 변수나 함수등의 목적을 더욱 명확하게 전달 가능하고 전달된 정보를 기반으로 코드 자동완성이나 잘못된 변수/함수 사용에 대한 에러 알림 같은 풍부한 피드백을 받을 수 있게 되므로 순수 자바스크립트에 비해 생상성 향상을 꾀할 수 있었습니다.
- SCSS: CSS는 HTML 태그를 꾸미거나 효과를 넣어 주는 등 디자인 요소를 추가할 때 사용하는 전처리 과정입니다. 프로젝트 규모가 커지면 CSS는 불가피하게 가독성이 떨어지는 등 유지보수의 어려움을 주는 요소가 됩니다. 코드의 재활용성을 올리고, 가독성을 올리는 등 CSS에서 보이던 단점을 보완하고, 개발의 효율을 올리기 위해 SCSS를 사용했습니다. 대표적인 장점으로는 변수 사용 가능, nesting(중첩) 가능, mixin, import, export 사용 가능, 흐름 제어 가능의 편리한 기능들이 있습니다.
- Axios:  response timeout (fetch에는 없는 기능) 처리 방법이 존재합니다. Promise 기반으로 만들어졌기 때문에 데이터 다루기 편리합니다. 또한크로스 브라우징 최적화로 브라우저 호환성(구형 브라우저 지원)이 뛰어나기에 채택했습니다.
- Google Maps API: 초기 렌더링 시 빠르다는 장점이 있습니다. 평균 렌더링 시간이 38.6ms로 지도에서 검색 기능을 지원하지 않기 때문에 속도 면에서 여타 다른 지도 API에 비해 장점이 있습니다. Google Maps API는 해외의 지도까지 지원해주기 때문에 서비스의 확장성까지 고려했고, 국내에서 제공해주는 지도에 비해 세계적으로 사용하는 지도이기 때문에 관련 Reference가 많아 이를 사용했습니다.
- JWT: 사용자의 로그인 인증 및 권한 유지 기술로 JWT토큰 방식을 선택하였습니다. [JWT기반 사용자 인증 및 권한 유지](https://github.com/softeerbootcamp/Team1-TDD/wiki/JWT%EA%B8%B0%EB%B0%98-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%9D%B8%EC%A6%9D-%EB%B0%8F-%EA%B6%8C%ED%95%9C-%EC%9C%A0%EC%A7%80)
### 당면했던 문제

- CORS
- Cookie
- Geolocation, Reverse Geocoder
- 컴포넌트 방식 new 키워드와 subscribe
- SCSS module hashing Collision
- 나중에 추가하고 싶은 기능

## 4. 프로젝트 설치 및 실행 방법

### FE

```shell
cd ./frontend
npm install
npm run dev //localhost:5173
```
### BE

```shell
cd ./backend
./gradlew bootJar
java -jar ./build/libs/back-end-0.0.1-SNAPSHOT.jar 
```
## 5. 프로젝트 사용 방법

- 사용자/기여자들이 프로젝트를 이용할 수 있는 방법과 예시
- 예상 문제에 대한 참고사항
- 프로젝트 실행 예시 화면의 스크린샷
- 프로젝트에서 사용된 구조나 디자인 원칙을 추가할 수 있습니다.

# 🤝 팀원

## FE
|```이민재```|```이제영```|
|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/52685740?v=4" width=130>|<img src="https://avatars.githubusercontent.com/u/82891332?v=4" width=130>|
|[@MyuB](https://github.com/MyuB)|[@2je0](https://github.com/2je0)|
## BE
|```김강산```|```김지언```|```박승민```|
|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/80745404?v=4" width=130>|<img src="https://avatars.githubusercontent.com/u/59179386?v=4" width=130>|<img src="https://avatars.githubusercontent.com/u/60373714?v=4" width=130>|
|[@River-Mt](https://github.com/River_Mt)|[@jieonkim23](https://github.com/jieonkim23)| [@psm9718](https://github.com/psm9718) |

# 프로젝트에 기여하는 방법
- 다른 개발자들이 프로젝트에 어떻게 기여할 수 있는지에 대한 가이드라인

# 테스트
- 애플리케이션의 테스트를 위해 예제 코드와 실행 방식
