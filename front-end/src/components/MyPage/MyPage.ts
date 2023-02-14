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
    const expBoard = qs(`.${styles["exp-board"]}`) as HTMLDivElement;
    const shareBoard = qs(`.${styles["share-board"]}`) as HTMLDivElement;
  }

  setUserInfo() {
    const {
      userName,
      createdAt,
      email,
      phoneNumber,
      drivingCount,
      sharingCount,
    } = this.state.user;
    console.log(
      userName,
      createdAt,
      email,
      phoneNumber,
      drivingCount,
      sharingCount
    );
  }

  generateCard(data: IUserData): string {
    return `
    
    `;
  }
}
