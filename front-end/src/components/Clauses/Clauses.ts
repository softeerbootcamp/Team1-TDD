import Component from "@/core/Component";
import { literal } from "./template";
import styles from "./Clauses.module.scss";
import { qs, qsa } from "@/utils/querySelector";

export class Clauses extends Component {
  setup(): void {
    const status = document.location.href as string;
    if (status.includes("sharing")) {
      this.state.welcomeMessage = "소중한 경험을 공유해주셔서 감사합니다.";
      this.state.status = "공유해주시기에 앞서, ";
    } else {
      this.state.welcomeMessage = "Experiences Begin Here";
      this.state.status = "경험하기에 앞서, ";
    }
  }
  template(): string {
    return literal(this.state.welcomeMessage, this.state.status);
  }

  setEvent(): void {
    this.addEvent("submit", "form", (e) => e.preventDefault());
    this.addEvent("click", "input", (_) => {
      const nextBtn = qs(`.${styles["next-button"]}`)! as HTMLButtonElement;
      if (this.isAllChecked()) {
        nextBtn.disabled = false;
        nextBtn.classList.toggle("active");
      } else {
        nextBtn.disabled = true;
        nextBtn.classList.toggle("active");
      }
    });
  }

  isAllChecked() {
    const checkbox = qsa("input")! as NodeListOf<HTMLInputElement>;
    for (const check of checkbox) {
      if (!check.checked) return false;
    }
    return true;
  }
}
