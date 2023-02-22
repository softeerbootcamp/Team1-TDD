import { qs } from './querySelector';

const notification = qs('#notification') as HTMLDivElement;

export function showNotification(message: string, duration: number = 1200) {
  notification.innerHTML = message;
  notification.classList.add('active');
  setTimeout(() => {
    notification.classList.remove('active');
  }, duration);
}
