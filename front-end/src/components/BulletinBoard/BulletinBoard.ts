import Component from "@/core/Component";

export class BulletinBoard extends Component {
  template(): string {
    const styles = this.props.module.css;
    return `
      <div class="${styles["board"]}">
        <div class="${styles["card-wrapper"]}">
          <div class="${styles["image-wrapper"]}"></div>
          <div class="${styles["text-wrapper"]}"></div>
        </div>
      </div>
    `;
  }
}
