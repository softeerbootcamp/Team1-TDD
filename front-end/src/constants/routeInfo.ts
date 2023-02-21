import { Home } from '@/components/Home/Home';
import { Experiencing } from '@/pages/experiencing';
import { Sharing } from '@/pages/sharing';
import { AboutUs } from '@/pages/aboutus';
import { NotFound } from '@/pages/notfound';
import { MyPage } from '@/components/MyPage/MyPage';
import { RegisterCar } from '@/components/RegisterCar/RegisterCar';
import { DetailPage } from '@/components/DetailPage/DetailPage';

interface IRoute {
  path: RegExp;
  element: Function;
}

export const routes: IRoute[] = [
  { path: /^\/$/, element: Home },
  { path: /^\/aboutus$/, element: AboutUs },
  { path: /^\/sharing$/, element: Sharing },
  { path: /^\/experiencing$/, element: Experiencing },
  //test용
  { path: /^\/addCar$/, element: RegisterCar },
  { path: /^\/mypage$/, element: MyPage },
  { path: /^\/details\/[\w]+$/, element: DetailPage },
  { path: /^\/post\/[\w]+$/, element: NotFound },
];
