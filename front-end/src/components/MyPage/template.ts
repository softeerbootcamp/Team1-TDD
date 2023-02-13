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
          <p class="${styles["title"]}">경험 신청 내역</p>
        </div>
        <div class="${styles["board-wrapper"]}">
          <div class="${styles["exp-board"]}">
            <div class=${styles["card-wrapper"]}>
              <div class=${styles["image-wrapper"]}>
                <img class="${styles["image"]}" src="${process.env.VITE_IMAGE_URL}/001_ionic6.png" />
              </div>
              <div class="${styles["text-wrapper"]}">
                <div class="${styles["car-name"]}">아이오닉 6</div>
                <div class="${styles["options"]}">수소/전기차, 전기, 주차거리 경보</div>
                <div class="${styles["date"]}">2023-02-20</div>
                <div class="${styles["location"]}">서울특별시 어쩌구 저쩌구</div>
              </div>
            </div>
            <div class=${styles["card-wrapper"]}>
              <div class=${styles["image-wrapper"]}>
                <img class="${styles["image"]}" src="${process.env.VITE_IMAGE_URL}/001_ionic6.png" />
              </div>
              <div class="${styles["text-wrapper"]}">
                <div class="${styles["car-name"]}">아이오닉 6</div>
                <div class="${styles["options"]}">수소/전기차, 전기, 주차거리 경보</div>
                <div class="${styles["date"]}">2023-02-20</div>
                <div class="${styles["location"]}">서울특별시 어쩌구 저쩌구</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="${styles["empty-wrapper"]}"></div>
      <div class="${styles["share-wrapper"]}">
        <div class="${styles["user-header-wrapper"]}">
          <p class="${styles["title"]}">공유한 차량 관리</p>
        </div>
        <div class="${styles["board-wrapper"]}">
          <div class="${styles["share-board"]}">
            <div class=${styles["card-wrapper"]}>
              <div class=${styles["image-wrapper"]}>
                <img class="${styles["image"]}" src="${process.env.VITE_IMAGE_URL}/001_ionic6.png" />
              </div>
              <div class="${styles["text-wrapper"]}">
                <div class="${styles["car-name"]}">아이오닉 6</div>
                <div class="${styles["options"]}">수소/전기차, 전기, 주차거리 경보</div>
                <div class="${styles["date"]}">2023-02-20</div>
                <div class="${styles["location"]}">서울특별시 어쩌구 저쩌구</div>
              </div>
            </div>
            <div class=${styles["card-wrapper"]}>
              <div class=${styles["image-wrapper"]}>
                <img class="${styles["image"]}" src="${process.env.VITE_IMAGE_URL}/001_ionic6.png" />
              </div>
              <div class="${styles["text-wrapper"]}">
                <div class="${styles["car-name"]}">아이오닉 6</div>
                <div class="${styles["options"]}">수소/전기차, 전기, 주차거리 경보</div>
                <div class="${styles["date"]}">2023-02-20</div>
                <div class="${styles["location"]}">서울특별시 어쩌구 저쩌구</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `;
}
