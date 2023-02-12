import styles from "./Clauses.module.scss";

export const literal = (welcomeMessage: string, status: string) => {
  return `
  <div class=${styles["main-wrapper"]}>
      <div class=${styles["welcome-wrapper"]}>
        <div class=${styles["welcome-helper"]}></div>
        <div class="${styles["welcome"]} ${styles.tossblue}">환영합니다!</div>
      </div>
      <div class=${styles["texts-wrapper"]}>
        <div class=${styles["text-helper"]}></div>
        <div class=${styles["text-wrapper"]}>
          <div class=${styles["text"]}>${welcomeMessage}</div>
          <div class=${styles["text"]}>
            ${status} 아래의 약관에 동의해주세요.
          </div>
        </div>
      </div>
      <div class=${styles["button-wrapper"]}>
        <div class=${styles["button-helper"]}></div>
        <label for="">
          <input type="checkbox" /> 
          (필수) 공유 시승차 이용 및 서비스 이용에 따른 주요 고지사항 및 이용사항 안내
        </label>
        <br />
      </div>
      <div class=${styles["clause-wrapper"]}>
        <div class=${styles["clause"]}>
          <span class=${styles["lightblue"]}>1. 유의사항</span>
          ①고객님의 시승차 운전이 불가하다고 판단 시 귀사 직원의
          판단에 따라 즉시 시승이 중단/취소될 수 있으며 향후 시승 이용이 제한될
          수 있습니다. (음주, 심신미약, 운전미숙, 과속 및 난폭운전, 기상악화 등
          안전상 위험하다고 판단되는 경우)  <br />
          ②시승서비스 이용을 위해서는 시승 전
          반드시 운전면허증을 제시해야 합니다. <br />
          ③시승차는 보험 적용 기준에 따라
          만 21세 이상만 운행 가능합니다. <br />
          ④다음 시승 신청 고객님들을 위해 시승
          신청시간을 준수해 주시기 바랍니다. 신청 변동사항이 있을 경우
          드라이빙라운지 혹은 담당 카마스터에게 사전 연락을 부탁드립니다. <br />
          <span class="${styles["lightblue"]} ${styles["margin-10"]}">2. 보험사항</span>
          상기 차량은 개인 운전자 보험 및 자차 보험에 가입되어 있으나, 보험 가액을 초과하는 부분 및 보험 미적용 부분 (운전자 연령 한정 운전 특약 위반, 차량 사고 시 자차 부담금 최대 10만원 고객 부담) 등에 대해서는 본인이 스스로 책임을 지는 동시에 차주에게 발생한 모든 손해를 배상할 것을 약속합니다.
          <span class="${styles["lightblue"]} ${styles["margin-10"]}">3. 금지사항</span>
          본인은 귀사가 제공한 서비스 차량을 상업적으로 이용하는 등 비정상적으로 운행하거나, 본인 외 제3자에게 운행, 양도, 대여, 담보의 목적으로 제공하는 등의 행위를 일절 하지 않을 것을 약속합니다. 또한 주행 보조 기능 (AEB 자동 긴급 제동 시스템, FCA 전방 충돌 방지 보조 등) 작동을 위하여 위험한 운전을 시도하지 않으며, 항상 안전하게 운전할 것을 약속합니다.
          <span class="${styles["lightblue"]} ${styles["margin-10"]}">4. 책임사항</span>
          ①본인은 위 보험 사항 및 금지사항, 유의사항을 위반하여 발생한 모든 민·형사상 책임을 부담합니다. <br />
          ②본인은 합의된 차량 반납 일시까지 상기 차량을 지정된 반납 장소 및 차주에게 반납하지 않거나 차량 반납 시 차량 상태가 변동된 경우, 이로 인해 발생한 모든 손해에 대한 배상 책임을 부담합니다. <br />
          ③본인은 교통법규 미준수로 인한 벌금, 과태료 및 시승 운행 시 발생한 도로교통비 등을 부담합니다. <br />
        </div>
      </div>
      <div class=${styles["button-wrapper"]}>
        <div class=${styles["button-helper"]}></div>
        <label for="">
          <input type="checkbox" /> 
             (필수) 시승신청 개인정보 수집 및 이용 동의
        </label>
        <br />
      </div>
      <div class=${styles["clause-wrapper"]}>
        <div class=${styles["clause"]}>
          <span class=${styles["lightblue"]}>1. 개인정보 수집 및 이용 목적</span>
          - 시승서비스 제공, 시승차량 사고 발생 시 보험처리 등 <br />
          - 사고 대응, 시승차량 도난 방지 및 운행 관리, 고객 불만 등 <br />
          - 민원사항 처리, 분쟁 발생 시 대응, 소비자 의견 조사,고객 관리 서비스 제공 <br />
          - 교통법규 미준수로 인한 벌금, 과태료처리 <br />
          <span class="${styles["lightblue"]} ${styles["margin-10"]}">2. 개인정보의 수집 항목</span>
          [필수 항목] <br />
          사용자 이름, 휴대전화 번호, 사용자 위치정보, 시승정보(시승차종, 시승일시), 시승차량 위치정보, 자동차 운전경력, 보유차종 및 연식, 기타 시승 관련 요청사항 <br />
          <span class="${styles["lightblue"]} ${styles["margin-10"]}">3. 개인정보의 보유 및 이용기간</span>
          서비스 이용일 기준 14일
          <span class="${styles["wraning"]} ${styles["margin-10"]}">고객님은 위의 개인정보 수집 이용에 대한 동의를 거부하실 수 있습니다. 그러나, 동의 거부 시 서비스 이용이 불가합니다.</span>
        </div>
      </div>
      <div class=${styles["next-wrapper"]}>
        <form action="/" method="POST" class=${styles["form-wrapper"]}>
          <button class=${styles["next-button"]} disabled>다음</button>
        </form>
      </div>
    </div>
  `;
};
