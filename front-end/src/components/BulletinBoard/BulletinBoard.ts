import Component from "@/core/Component";
// import styles from "./BulletinBoard.module.scss";

export class BulletinBoard extends Component {
  setup(): void {
    this.state.styles = this.props.module.css;
  }

  template(): string {
    return `
      <div class="${this.state.styles["board"]}">
        <div class="${this.state.styles["card-wrapper"]}">
          <div class="${this.state.styles["image-wrapper"]}"></div>
          <div class="${this.state.styles["text-wrapper"]}"></div>
        </div>
      </div>
    `;
  }
}
