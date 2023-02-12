import Component from "@/core/Component";
import { qsa } from "@/utils/querySelector";
import styles from "./Home.module.scss";

import { homeTemplate } from "./template";
export class Home extends Component {
  template(): string {
    return homeTemplate();
  }

  mounted(): void {
    const observer: IntersectionObserver = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            const target = entry.target as HTMLDivElement;
            target.style.opacity = "1";
          } else {
            const target = entry.target as HTMLDivElement;
            target.style.opacity = "0";
          }
        });
      }
    );
    const indexes = qsa(`.${styles.idxes}`);
    indexes.forEach((indexWrapper) => {
      observer.observe(indexWrapper);
    });

    const fadeInObserver = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        const animation = entry.target.getAttribute("data-animation") as string;
        if (entry.isIntersecting) {
          entry.target.classList.add(
            `${styles["animated"]}`,
            `${styles[animation]}`
          );
        } else {
          entry.target.classList.remove(
            `${styles["animated"]}`,
            `${styles[animation]}`
          );
        }
      });
    });

    const animatedEls = qsa("[data-animation]");
    animatedEls.forEach((el) => fadeInObserver.observe(el));
  }
}
