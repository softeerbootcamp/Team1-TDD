import styles from './MyPage.module.scss';

export function literal(carNums: number) {
  return `
      <div class="${styles['main-wrapper']}">
        <div class="${styles['user-box-wrapper']}">
          <div class="${styles['user-info']}">
            <div class="${styles['register-wrapper']}">
              <div class="${styles['register']}">My Car 등록</div>
            </div>
            <div class="${styles['name-wrapper']} ${styles['flex']}">
              <div class="${styles['name']} ${styles['left']}">이름</div>
              <div class="${styles['user-name']} ${styles['right']}">티디디</div>
            </div>
            <div class="${styles['date-wrapper']} ${styles['flex']}">
              <div class="${styles['date']} ${styles['left']}">가입날짜</div>
              <div class="${styles['user-date']} ${styles['right']}">2023-02-25</div>
            </div>
            <div class="${styles['email-wrapper']} ${styles['flex']}">
              <div class="${styles['email']} ${styles['left']}">이메일</div>
              <div class="${styles['user-email']} ${styles['right']}">tdd@tdd.com</div>
            </div>
            <div class="${styles['exp-wrapper']} ${styles['flex']}">
              <div class="${styles['exp']} ${styles['left']}">경험횟수</div>
              <div class="${styles['user-exp']} ${styles['right']}">1</div>
            </div>
            <div class="${styles['share-wrapper']} ${styles['flex']}">
              <div class="${styles['share']} ${styles['left']}">공유횟수</div>
              <div class="${styles['user-share']} ${styles['right']}">2</div>
            </div>
            <div class="${styles['cars-wrapper']} ${styles['flex']}">
              <div class="${styles['cars']} ${styles['left']}">등록한 차</div>
              <div class="${styles['user-cars']} ${styles['right']}">${carNums}개</div>
            </div>
          </div>
         </div>
        <div class="${styles['box-wrapper']} ${styles['wrap']}">
          <div class="${styles['recent-share']} ${styles['recent-box']}">
            <div class="${styles['box-header']}">경험하기 목록</div>
            <div class="${styles['cards-wrapper']}" id="exp-card">
              
            </div>
          </div>
          <div class="${styles['recent-exp']} ${styles['recent-box']}">
            <div class="${styles['box-header']}">공유하기 목록</div>
            <div class="${styles['cards-wrapper']}" id="share-card">  
          </div>
        </div>
      </div>
    </div>
  `;
}
