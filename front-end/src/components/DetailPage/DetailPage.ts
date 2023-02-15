import Component from "@/core/Component";
import styles from "./DetailPage.module.scss";

const dummy_options = [
  "N / N Line",
  "가솔린",
  "주차 거리 경고-전방",
  "프리미엄 타이어",
  "앞좌석 열선시트",
  "서라운드 뷰 모니터",
  "버튼시동 스마트키 시스템",
];

export class DetailPage extends Component {
  setup(): void {
    this.state.data = [];
  }
  template(): string {
    return `
      <div class="${styles["container"]}">
        <div class="${styles["infos"]}">
          <div class="${styles["image-wrapper"]}">
            <img class="${styles["image"]}" src="${
      process.env.VITE_IMAGE_URL
    }/008_avanteN.png" />
          </div>
          <div class="${styles["text-wrapper"]}">
            <div class="${styles["car-name"]}">
              <div class="${styles["left"]}">차종</div>
              <div class="${styles["right"]}">Avante</div>
            </div>
            <div class="${styles["options"]}">
              <div class="${styles["left"]}">옵션</div>
              <div class="${styles["right"]}">${dummy_options
      .map((ele) => this.optionCreator(ele))
      .join("")}
            </div>
          </div>
          <div class="${styles["date"]}">
            <div class="${styles["left"]}">시승날짜</div>
            <div class="${styles["right"]}">2023-02-24</div>
          </div>
          <div class="${styles["location"]}">
            <div class="${styles["left"]}">시승위치</div>
            <div class="${styles["right"]}">코드스쿼드</div>
          </div>
        </div>
        </div>
        <div class="${styles["button-wrapper"]}">
          <div class="${styles["confirm"]}">확인</div>
          <div class="${styles["cancel"]}">취소</div>
        </div>
      </div>
    `;
  }

  optionCreator(option: string): string {
    const literal = `
      <div class="${styles["opt"]}">${option}</div>
    `;
    return literal;
  }
}
