import { Home } from '@/components/Home/Home';
import { Experiencing } from '@/pages/experiencing';
import { Sharing } from '@/pages/sharing';
import { AboutUs } from '@/pages/aboutus';
import { NotFound } from '@/pages/notfound';
interface IRoute {
  path: RegExp;
  element: Function;
}
export const BASE_URL = 'http://localhost:5173';

export const routes: IRoute[] = [
  { path: /^\/$/, element: Home },
  { path: /^\/aboutus$/, element: AboutUs },
  { path: /^\/sharing$/, element: Sharing },
  { path: /^\/experiencing$/, element: Experiencing },
  //test용
  { path: /^\/post\/[\w]+$/, element: NotFound },
];
