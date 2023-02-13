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
    this.state.userInfo = null;
  }

  template(): string {
    return literal();
  }

  mounted(): void {
    getUserInfo()
      .then((res) => {
        this.state.userInfo = res;
        console.log(this.state.userInfo);
      })
      .catch((err) => console.log(err));

    const expBoard = qs(`.${styles["exp-board"]}`) as HTMLDivElement;
    const shareBoard = qs(`.${styles["share-board"]}`) as HTMLDivElement;
  }

  generateCard(data: IUserData): HTMLDivElement {
    const cardWrapper = document.createElement("div");
    const imageWrapper = document.createElement("div");
    imageWrapper.classList.add(`${styles["image-wrapper"]}`);
    const img = document.createElement("img");
    img.classList.add(`${styles["imamge"]}`);
    img.src = `${process.env.VITE_IMAGE_URL}/001_ionic6.png`;
    imageWrapper.append(img);
    const textWrapper = document.createElement("div");
    textWrapper.classList.add(`${styles["text-wrapper"]}`);
    const carName = document.createElement("div");
    carName.classList.add(`${styles["car-name"]}`);
    const options = document.createElement("div");
    options.classList.add(`${styles["options"]}`);
    const date = document.createElement("div");
    date.classList.add(`${styles["date"]}`);
    const location = document.createElement("div");
    location.classList.add(`${styles["location"]}`);
    textWrapper.append(carName, options, date, location);
    cardWrapper.append(imageWrapper, textWrapper);

    return cardWrapper;
  }
}
