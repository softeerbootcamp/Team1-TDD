import Component from "@/core/Component";
import { qsa } from "@/utils/querySelector";
import { literal } from "./template";
import styles from "./MyPage.module.scss";
// import styles from "../BulletinBoard/BulletinBoard.module.scss";
import { BulletinBoard } from "../BulletinBoard/BulletinBoard";

export class MyPage extends Component {
  setup(): void {
    console.log(styles);
  }

  template(): string {
    return literal();
  }

  mounted(): void {
    const boards = qsa(`.${styles["board-wrapper"]}`);
    for (const board of boards) {
      const bd = board as HTMLElement;
      new BulletinBoard(bd, {
        module: {
          css: styles,
        },
      });
    }
  }
}
