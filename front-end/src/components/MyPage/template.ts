import styles from "./MyPage.module.scss";

export function literal() {
  return `
    <div class="${styles["container"]}">
      <div class="${styles["empty-wrapper"]}"></div>
      <div class="${styles["user-wrapper"]}">
        <div class="${styles["user-header-wrapper"]}">
          <h1 class="${styles["title"]}">회원 정보</h1>
        </div>
        <div class="${styles["name-wrapper"]}">
          <div class="${styles["name"]} ${styles["left"]}">이름</div>
          <div class="${styles["user-name"]} ${styles["right"]}">티디디</div>
        </div>
        <div class="${styles["registered-wrapper"]}">
          <div class="${styles["date"]} ${styles["left"]}">가입 일자</div>
          <div class="${styles["user-date"]} ${styles["right"]}">2023-02-12</div>
        </div>
        <div class="${styles["email-wrapper"]}">
          <div class="${styles["email"]} ${styles["left"]}">이메일</div>
          <div class="${styles["user-email"]} ${styles["right"]}">qwerty@google.com</div>
        </div>
        <div class="${styles["expno-wrapper"]}">
          <div class="${styles["expno"]} ${styles["left"]}">경험횟수</div>
          <div class="${styles["user-expno"]} ${styles["right"]}">1</div>
        </div>
        <div class="${styles["shareno-wrapper"]}">
          <div class="${styles["shareno"]} ${styles["left"]}">공유횟수</div>
          <div class="${styles["user-shareno"]} ${styles["right"]}">2</div>
        </div>
      </div>
      <div class="${styles["empty-wrapper"]}"></div>
      <div class="${styles["exp-wrapper"]}">
        <div class="${styles["user-header-wrapper"]}">
          <h1 class="${styles["exp-title"]}">경험 신청 내역</h1>
        </div>
        <div class="${styles["board-wrapper"]}">
          <div class="${styles["exp-board"]}"></div>
          <div class="${styles["right-div"]}"></div>
        </div>
      </div>
      <div class="${styles["empty-wrapper"]}"></div>
      <div class="${styles["share-wrapper"]}">
        <div class="${styles["user-header-wrapper"]}">
          <h1 class="${styles["share-title"]}">공유한 차량 관리</h1>
        </div>
        <div class="${styles["board-wrapper"]}">
          <div class="${styles["share-board"]}"></div>
          <div class="${styles["right-div"]}"></div>
        </div>
      </div>
    </div>
  `;
}
