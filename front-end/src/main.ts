import { App } from '@/app';
import { qs } from '@/utils/querySelector';
import '@/styles/main.scss';
const $app = qs('#app') as HTMLDivElement;
new App($app);
