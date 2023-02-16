import styles from './MyPage.module.scss';

export function literal() {
  return `
      <div class="${styles['main-wrapper']}">
        <div class="${styles['user-box-wrapper']}">
          <div class="${styles['user-info']}">
            <div class="${styles['img-wrapper']}">
              <img
                src="https://w.namu.la/s/a4b8b7fa3ce77cae5845587199d433b974efbb6496ecdff536dcbf610041aa12723c48baf1160579bb4ce1ccefaa76d5e4040ac7bf2331a44e5dfadb8f0f5148f2fbe1c3a5d329ae5e392103eb6243eb7f7b5f1865da795bda0c7a491de0e469"
                alt="aa"
                style="width: 200px; height: 200px"
              />
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

/* 
<div class="${styles['card-wrapper']}">
                <div class=${styles['image-wrapper']}>
                  <div class=${styles['helper']}></div>
                  <img class="${styles['image']}" src="${process.env.VITE_IMAGE_URL}/018_sonata_Hybrid.png"/>
                </div>
                <div class="${styles['text-wrapper']}">
                  <div class='${styles['helper']}'></div>
                  <div class="${styles['car-name']}">소나타</div>
                  <div class="${styles['options']}">ㅇㅇㅇ</div>
                  <div class="${styles['date']}">싫다</div>
                  <div class="${styles['location']}"></div>
                </div>
              </div>

*/
