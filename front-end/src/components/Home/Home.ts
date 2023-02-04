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
            observer.unobserve(target);
          }
        });
      }
    );
    const indexes = qsa(`.${styles.idxes}`);
    indexes.forEach((indexWrapper) => {
      observer.observe(indexWrapper);
    });
  }
}
