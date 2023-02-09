import { qs } from './querySelector';

export function openOverlay() {
  const $overlay = qs('#overlay') as HTMLDivElement;
  $overlay.classList.remove('overlay-hidden');
  document.body.classList.add('block-scroll');
}

export function closeOverlay() {
  const $overlay = qs('#overlay') as HTMLDivElement;
  $overlay.classList.add('overlay-hidden');
  document.body.classList.remove('block-scroll');
}
