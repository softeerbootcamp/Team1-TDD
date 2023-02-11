import { LoginForm } from '@/components/LoginForm/LoginForm';
import { closeOverlay, openOverlay } from './overlay';
import { qs } from './querySelector';

export function openLoginModal() {
  openOverlay();
  const $modal = qs('#modal') as HTMLDivElement;
  new LoginForm($modal);
}

export function closeLoginModal() {
  closeOverlay();
}
