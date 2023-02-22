import styles from './About.module.scss';

export function literal() {
  return `
    <div class="${styles['main-wrapper']}">
      <div class="${styles['welcome-wrapper']}">
        <div class="${styles['hi-wrapper']}">
          <div class="${styles['helper']}"></div>
          <div class="${styles['welcome']}">안녕하세요.</div>
        </div>
        <div class="${styles['there-wrapper']}">
          <div class="${styles['helper']}"></div>
          <div class="${styles['welcome-msg']}">사용자간 자동차 경험을 공유하는 웹 기반 C2C 시승 플랫폼 TDD입니다.</div>
        </div>
      </div>
      <div class="${styles['motivation-wrapper']}">
        <div class="${styles['top-wrapper']}">
          <div class="${styles['helper']}"></div>
          <div class="${styles['q1']}">왜 이 프로젝트를 기획했나요?</div>
        </div>
        <div class=${styles['second-wrapper']}>
          <div class="${styles['helper']}"></div>
          <div class="${styles['a1']}">현재의 시승서비스. 불편하다고 생각해보신 적 없으신가요? 전국에 몇개 없는 현대의 Driving Center. 그리고 주말 예약을 다 차버려서 예약도 할 수 없는 상황. 구매하고 싶은 모델은 확정했지만 옵션에 대한 경험을 하고 싶은 고민. 이러한 불편함들에서 TDD는 출발했습니다.</div>
        </div>
        <div class="${styles['third-wrapper']}">
          <div class="${styles['helper']}"></div>
          <div class="${styles['q1']}">이 프로젝트는 어떤 문제를 해결하나요?</div>
        </div>
        <div class=${styles['last-wrapper']}>
          <div class="${styles['helper']}"></div>
          <div class="${styles['a1']}">사용자들간의 시승을 공유함으로서 우리는 이 불편함들을 해결하고자 했습니다. 세부적인 고민 사항들을 혼자 가지고 있지 말고, 경험을 이용해보면서 시작해보세요.</div>
        </div>
      </div>
      <div class="${styles['logo-wrapper']}">
        <div class="${styles['image-wrapper']}">
          <img src="${process.env.VITE_IMAGE_URL}/tdd-logo.png" alt="tdd_logo" class="${styles['img']}"/>
        </div>
        <div class="${styles['text']}">Let’s TDD. Try, Drive, Delightly. 로고에 대한 설명을 하겠습니다. TDD에서 T와 D를 가지고 핸들을 추상화했습니다. 전체적인 모양은 공유 핀을 형상화하였습니다.</div>
      </div>
      <div class="${styles['stack-wrapper']}">
        <div class="${styles['header']}">FrontEnd</div>
        <div class="${styles['frontend']}">
          <img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=black" alt="..."/> 
          <img src="https://img.shields.io/badge/SCSS-CC6699?style=for-the-badge&logo=SASS&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/prettier-F7B93E?style=for-the-badge&logo=prettier&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/.env-ECD53F?style=for-the-badge&logo=.env&logoColor=black" alt="..."/>
          <img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/googlemaps-4285F4?style=for-the-badge&logo=googlemaps&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/vite-646CFF?style=for-the-badge&logo=vite&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/eslint-4B32C3?style=for-the-badge&logo=eslint&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=Axios&logoColor=white" alt="..."/> 
        </div>
        <div class="${styles['header']}">BackEnd</div>
        <div class="${styles['backend']}">
          <img src="https://img.shields.io/badge/java 11-007396?style=for-the-badge&logo=java&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/spring data jdbc-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="..."/> 
        </div>
        <div class="${styles['header']}">Deploy</div>
        <div class="${styles['deploy']}">
          <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/amazonAWS-232F3E?style=for-the-badge&logo=amazonAWS&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="..."/> 
          <img src="https://img.shields.io/badge/githubactions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" alt="..."/> 
        </div>
      </div>
    </div>
  `;
}
