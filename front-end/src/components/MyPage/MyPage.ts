import { getUserInfo, getMyCar } from '@/apis/mypage';
import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import { literal } from './template';
import styles from './MyPage.module.scss';
import { routeGaurd } from '@/apis/login';
import { goto } from '@/utils/navigatator';

interface IAppointment {
  date: string;
  id: number;
  status: string;
}

interface IOptions {
  category: string;
  name: string;
}

interface ILocation {
  latitude: string;
  longitude: string;
}

interface ISharing {
  appointments: IAppointment[];
  post: {
    carName: string;
    id: number;
    imageUrl: string;
    location: ILocation;
    options: IOptions[];
    requirement: string;
    rideOption: string;
  };
}

interface IDriving {
  date: string;
  post: {
    carName: string;
    id: number;
    imageUrl: string;
    location: ILocation;
    options: IOptions[];
    requirement: string;
    rideOption: string;
  };
}

interface IUser {
  createdAt: string;
  drivingCount: number;
  email: string;
  phoneNumber: string;
  sharingCount: number;
  userName: string;
}

export class MyPage extends Component {
  setup() {
    this.state.login = false;
    routeGaurd(
      () => {
        this.setState({ login: true });
      },
      () => {
        goto('/');
      }
    );

    getUserInfo().then((res) => {
      this.setState({ res });
    });
    getMyCar().then((res) => {
      this.state.myCars = res.data;
    });
  }

  template(): string {
    const len = this.state.myCars ? this.state.myCars.length : 0;
    return literal(len);
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['register']}`, () => {
      goto('/addCar');
    });
  }

  mounted(): void {
    const shareCardWrapper = qs(`#${'share-card'}`);
    const expCardWrapper = qs(`#${'exp-card'}`);

    if (!this.state.res) return;

    const drivingList = this.state.res.data.driving as IDriving[];
    const sharingList = this.state.res.data.sharing as ISharing[];
    const user = this.state.res.data.user as IUser;

    this.setUserInfo(user);
    drivingList?.forEach((element: IDriving) => {
      expCardWrapper!.innerHTML += this.generateDrivingCard(element);
    });
    sharingList.forEach((element: ISharing) => {
      shareCardWrapper!.innerHTML += this.generateSharingCard(element);
    });
  }

  setUserInfo(user: IUser) {
    const userInfo = qs(`.${styles['user-info']}`) as HTMLDivElement;
    const $name = qs(`.${styles['user-name']}`, userInfo) as HTMLDivElement;
    $name.innerText = user.userName;
    const $date = qs(`.${styles['user-date']}`, userInfo) as HTMLDivElement;
    $date.innerText = user.createdAt;
    const $email = qs(`.${styles['user-email']}`, userInfo) as HTMLDivElement;
    $email.innerText = user.email;
    const $expno = qs(`.${styles['user-exp']}`, userInfo) as HTMLDivElement;
    $expno.innerText = user.drivingCount as unknown as string;
    const $shrno = qs(`.${styles['user-share']}`, userInfo) as HTMLDivElement;
    $shrno.innerText = user.sharingCount as unknown as string;
  }

  generateDrivingCard(data: IDriving): string {
    const lat = +data.post.location.latitude;
    const lng = +data.post.location.longitude;
    const location = `https://www.google.co.kr/maps?&z=18.5&q=${lat},${lng}&ll=${lat},${lng}z`;
    const carName = data.post.carName;
    const carImage = data.post.imageUrl;
    const options = data.post.options;
    const date = data.date;
    return `
    <div class="${styles['card-wrapper']}">
      <div class=${styles['image-wrapper']}>
        <div class="${styles['helper']}"></div>
        <img class="${styles['image']}" src="${carImage}" alt="..."/>
      </div>
      <div class="${styles['text-wrapper']}">
        <div class="${styles['helper']}"></div>
        <div class="${styles['car-name']}">${carName}</div>
        <div class="${styles['options']}">${options
      .map((ele) => ele.name)
      .join(', ')}</div>
        <div class="${styles['date']}">${date}</div>
        <div class="${styles['location']}">
          <a href="${location}" target="_blank">위치</a>
        </div>
      </div>
    </div>
    `;
  }

  generateSharingCard(data: ISharing): string {
    const carName = data.post.carName;
    const carImage = data.post.imageUrl;
    const options = data.post.options;
    const lat = +data.post.location.latitude;
    const lng = +data.post.location.longitude;
    const appointments = data.appointments;
    const location = `https://www.google.co.kr/maps?&z=18.5&q=${lat},${lng}&ll=${lat},${lng}z`;
    return `
    <div class=${styles['card-wrapper']}>
      <div class=${styles['image-wrapper']}>
        <div class=${styles['helper']}></div>
        <img class="${styles['image']}" src="${carImage}" alt="..."/>
      </div>
      <div class="${styles['text-wrapper']}">
        <div class="${styles['helper']}"></div>
        <div class="${styles['car-name']}">${carName}</div>
        <div class="${styles['options']}">${options
      .map((ele) => ele.name)
      .join(', ')}</div>
        <div class="${styles['date']}">${appointments
      .map((ele) => ele.date)
      .join(', ')}</div>
        <div class="${styles['location']}">
          <a href="${location}" target="_blank">위치</a>
        </div>
      </div>
    </div>
    `;
  }
}
