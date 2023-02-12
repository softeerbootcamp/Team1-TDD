import { LoginForm } from '@/components/LoginForm/LoginForm';
import { closeOverlay, openOverlay } from './overlay';
import { qs } from './querySelector';

const $modal = qs('#modal') as HTMLDivElement;
const loginModal = new LoginForm($modal);
export function openLoginModal() {
  openOverlay();
  loginModal.render();
}

export function closeLoginModal() {
  closeOverlay();
}
