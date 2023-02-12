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

  mounted(): void {
    const form = qs("form")! as HTMLFormElement;
    form.addEventListener("submit", (e: SubmitEvent) => e.preventDefault());
    const checkbox = qsa("input")! as NodeListOf<HTMLInputElement>;

    checkbox.forEach((box) => {
      box.addEventListener("click", (_) => {
        const nextBtn = qs(`.${styles["next-button"]}`)! as HTMLButtonElement;
        if (allChecked()) {
          nextBtn.disabled = false;
          nextBtn.classList.toggle("active");
        } else {
          nextBtn.disabled = true;
          nextBtn.classList.toggle("active");
        }
      });
    });

    function allChecked() {
      const checkbox = qsa("input")! as NodeListOf<HTMLInputElement>;
      for (const check of checkbox) {
        if (!check.checked) return false;
      }
      return true;
    }
  }
}
