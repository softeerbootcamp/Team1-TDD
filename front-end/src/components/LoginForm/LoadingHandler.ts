export class LoadingHandler {
  #loadingSpinner = '<div class="loader"></div>';
  #target;
  #targetContent;

  constructor($target: Element) {
    this.#target = $target;
    this.#targetContent = this.#target.innerHTML;
  }
  startRequest() {
    if (this.#target instanceof HTMLButtonElement) {
      this.#target.disabled = true;
    }
    this.#target.innerHTML = this.#loadingSpinner;
  }
  finishRequest() {
    if (this.#target instanceof HTMLButtonElement) {
      this.#target.disabled = false;
    }
    this.#target.innerHTML = this.#targetContent;
  }
  finishRegister() {
    if (this.#target instanceof HTMLButtonElement) {
      this.#target.disabled = true;
    }
    this.#target.innerHTML = '가입 완료!';
  }
  finishCheckEmail() {
    if (this.#target instanceof HTMLButtonElement) {
      this.#target.disabled = false;
    }
    this.#target.innerHTML = 'Sign Up';
  }
}
