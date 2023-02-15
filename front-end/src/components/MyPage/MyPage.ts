import { getUserInfo } from "@/apis/mypage";
import Component from "@/core/Component";
import { qs } from "@/utils/querySelector";
import { literal } from "./template";
import styles from "./MyPage.module.scss";

interface IUserData {
  carName: string;
  options: object;
  date: string;
  location: string;
}

export class MyPage extends Component {
  setup(): void {
    getUserInfo()
      .then((res) => {
        this.state.driving = res.data.driving;
        this.state.sharing = res.data.sharing;
        this.state.user = res.data.user;
        console.log(this.state.user);
        console.log(res);
        console.log("hi");
      })
      .catch((err) => console.log(err));
  }

  template(): string {
    return literal();
  }

  mounted(): void {
    const $expBoard = qs(`.${styles["exp-board"]}`) as HTMLDivElement;
    const $shareBoard = qs(`.${styles["share-board"]}`) as HTMLDivElement;
  }

  setUserInfo() {
    const $name = qs(`.${styles["user-name"]}`) as HTMLDivElement;
    $name.innerText = this.state.user.userName;
    const $date = qs(`.${styles["user-date"]}`) as HTMLDivElement;
    $date.innerText = this.state.user.createdAt;
    const $email = qs(`.${styles["user-email"]}`) as HTMLDivElement;
    $email.innerText = this.state.user.email;
    const $expno = qs(`.${styles["user-expno"]}`) as HTMLDivElement;
    $expno.innerText = this.state.user.sharingCount;
    const $shrno = qs(`.${styles["user-shareno"]}`) as HTMLDivElement;
    $shrno.innerText = this.state.user.createdAt;
  }

  generateCard(data: IUserData): string {
    return `
    
    `;
  }
}
