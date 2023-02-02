export function qs(selector: string, target: HTMLElement = document.body) {
  return target.querySelector(selector);
}

export function qsa(selector: string, target: HTMLElement = document.body) {
  return target.querySelectorAll(selector);
}
