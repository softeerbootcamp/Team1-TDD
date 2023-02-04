import Component from "@/core/Component";

import { homeTemplate } from "./template";
export class Home extends Component {
  template(): string {
    return homeTemplate();
  }
}
