import { App } from '@/app';
import { qs } from '@/utils/querySelector';
import '@/styles/main.scss';
import { closeLoginModal } from './utils/loginModal';
const $app = qs('#app') as HTMLDivElement;
new App($app);

qs('#overlay')?.addEventListener('click', ({ target }) => {
  if (!(target instanceof HTMLDivElement)) return;
  if (target.id !== 'overlay') return;
  closeLoginModal();
});
