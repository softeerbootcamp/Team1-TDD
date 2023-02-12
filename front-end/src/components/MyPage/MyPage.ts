import Component from "@/core/Component";
import { literal } from "./template";

export class MyPage extends Component {
  setup(): void {}

  template(): string {
    return literal();
  }

  mounted(): void {}
}
