import Component from "@/core/Component";
import { literal } from "./template";
import styles from "./Clauses.module.scss";
import { qs, qsa } from "@/utils/querySelector";

export class Clauses extends Component {
  template(): string {
    return literal();
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
