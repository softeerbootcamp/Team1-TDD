import styles from './Home.module.scss';
export const homeTemplate = (): string => {
  return `
    <div class="${styles['main-wrapper']} ${styles['animation-group']}">
      <div class="${styles['index-2']} ${styles['idxes']}" id="index">
        <div class="${styles['empty-wrapper']}"></div>
        <div class="${styles['second-top-wrapper']} ${styles['flex-box']}">
          <div class="${styles['helper-first']}"></div>
          <div class="${styles['second-first-text']}">
            <span class="${styles.tossblue}">We make your </span><br />
            <span class="${styles.darkblue}">Experiences </span><br />
            <span class="${styles.tossblue}">special to others </span><br />
          </div>
        </div>
        <div class="${styles['second-bottom-wrapper']} ${styles['flex-box']}">
          <div class="${styles['helper-second']}"></div>
          <div class="${styles['second-second-text']}">
            <span class="${styles['tossblue']}">Through </span>
            <span class="${styles.darkblue}">Sharing </span>
          </div>
        </div>
      </div>
      <div class="${styles['index-1']} ${styles['idxes']}" id="index">
        <div class="${styles['flex-box']}">
          <div class="${styles['helper-first']}"></div>
          <div class="${styles['first-top-wrapper']}">
            <span class="${styles['first-title']}">TDD</span>
          </div>
        </div>
        <div class="${styles['mid-wrapper']} ${styles['flex-box']}">
          <div class="${styles['helper-second']}"></div>
          <span class="${styles['second-title']} ">티디디</span>
        </div>
        <div class="${styles['last-wrapper']}">
          <div class="${styles['flex-box']}">
            <div class="${styles['helper-first']}"></div>
            <div class="${styles['text-container']}">
              <span class="${styles['third-title']}">Try</span> <br />
              <span class="${styles['third-title']}">Drive</span> <br />
              <span class="${styles['third-title']}">Delightly</span> 
            </div>
          </div>
        </div>
      </div>
      <div class="${styles['index-3']} ${styles['idxes']}" id="index">
        <div class="${styles['empty-wrapper']}"></div>
        <div class="${styles['third-top-wrapper']}">
          <div class="${styles['third-top-text']}>
            우리는 더 나은 <b class="${styles.darkblue}">사용자 경험</b>을 위해
            <br />
            끊임없이 고민하며 지속 발전을 추구합니다.
          </div>
        </div>
        <div class="${styles['third-bottom-wrapper']}">
          <img class="${styles['car-image']}" src="${
    process.env.VITE_IMAGE_URL
  }/share.png" alt="..."></img>
          <div class="${styles['third-bottom-text']}">
            <b class="${styles.darkblue} ${styles['bold']}">당신의 경험</b>이
            <b class="${styles.tossblue} ${styles['bold']}">타인의 경험</b>으로
          </div>
        </div>
      </div>
      <div class="${styles['index-4']} ${styles['idxes']}" id="index">
        <div class="${styles['fourth-top-wrapper']}">
          <div class="${styles['fourth-top-text']} ${styles.tossblue}">
            Try <br />
            Drive <br />
            Delightly
          </div>
        </div>
        <div class="${styles['fourth-bottom-wrapper']}">
          <div class="${['fourth-mid-text']}">
            <span class="${styles.darkblue} ${
    styles['big']
  }" style="font-size: 250%;">티디디</span>
            <span>는 다음과 같은 아이디어에서
            출발했습니다.</span>
          </div>
          <div class="${styles['fourth-bottom-text']}">
            현대자동차의 시승 사이트에 들어가면 시승 신청을 해볼 수 있습니다.
            하지만 아반떼의 경우 '드라이빙 라운지 강남'을 포함하여 전국 8개의
            라운지에서만 시승 신청을 할 수 있었습니다. 또한 드라이빙 라운지
            강남의 경우 이미 명월 주말은 예약이 꽉 차있는 상태였습니다. 직장인의
            경우 주말에 보통 시승 신청을 할 것인데 지역적 인프라의 부족뿐 아니라
            주말 시간이 비어있지 않은 문제 또한 사용자의 불편으로 느껴질 것
            같았습니다.
          </div>
        </div>
      </div>
      <div class="${styles['index-5']} ${styles['idxes']}" id="index">
        <div class="${styles['fifth-top-wrapper']} ${styles.tossblue}">
          <div class="${styles['fifth-title-text']} ${styles['level']}">
            <spsn>지금 바로</span> <br />
            <span>경험 시작.</span>
          </div>
        </div>
        <div class="${styles['fifth-bottom-wrapper']}">
          <a data-link href="/sharing" class="${
            styles['fifth-left-wrapper']
          }" data-animation="fadeInLeft">
            <img class="${styles['fifth-first-img']}" src="${
    process.env.VITE_IMAGE_URL
  }/sharing.png" alt="..."/>
            <div class="${styles['fifth-texts']}">공유하기</div>
          </a>
          <a data-link href="/experiencing" class="${
            styles['fifth-right-wrapper']
          }" data-animation="fadeInLeft">
            <img class="${styles['fifth-second-img']}" src="${
    process.env.VITE_IMAGE_URL
  }/use.png" alt="..."/>
            <div class="${styles['fifth-texts']}">시승하기</div>
          </a>
        </div>
      </div>
    </div>
  `;
};
