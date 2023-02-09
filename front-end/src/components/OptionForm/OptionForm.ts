import Component from '@/core/Component';
import styles from './OptionForm.module.scss';
import { OptionStore } from '@/store/OptionStore/OptionStore';
const DUMMY_DATA = [
  {
    category: '차종',
    options: [
      { category: '차종', name: '수소 / 전기차' },
      { category: '차종', name: 'N / N Line' },
      { category: '차종', name: '승용' },
      { category: '차종', name: 'SUV' },
      { category: '차종', name: 'MPV' },
      { category: '차종', name: '소형트럭&택시' },
      { category: '차종', name: '트럭' },
      { category: '차종', name: '버스' },
    ],
  },

  {
    category: '엔진',
    options: [
      { category: '엔진', name: '디젤' },
      { category: '엔진', name: '전기' },
      { category: '엔진', name: '가솔린' },
      { category: '엔진', name: '하이브리드' },
      { category: '엔진', name: '수소' },
      { category: '엔진', name: 'LPG' },
    ],
  },
  {
    category: '안전/성능',
    options: [
      { category: '안전/성능', name: '주차 거리 경고-전방 ' },
      { category: '안전/성능', name: '주차 거리 경고-후방 ' },
      { category: '안전/성능', name: '진동경고 스티어링 휠 ' },
      { category: '안전/성능', name: '후륜 멀티링크 서스펜션 ' },
      { category: '안전/성능', name: '랙 구동형 전동식 파워스티어링(RMDPS) ' },
    ],
  },
  {
    category: '지능형 안전기술',
    options: [
      { category: '지능형 안전기술', name: '후측방 충돌방지 보조 ' },
      { category: '지능형 안전기술', name: '안전 하차 보조 ' },
      { category: '지능형 안전기술', name: '전방 충돌방지 보조 ' },
      { category: '지능형 안전기술', name: '차로 이탈방지 보조 ' },
      { category: '지능형 안전기술', name: '스마트 크루즈 컨트롤 ' },
      { category: '지능형 안전기술', name: '원격 스마트 주차 보조 ' },
      { category: '지능형 안전기술', name: '후석 승객 알림 ' },
    ],
  },
  {
    category: '내장/외장',
    options: [
      { category: '내장/외장', name: 'LED 헤드램프' },
      { category: '내장/외장', name: 'LED 리어콤비램프 ' },
      { category: '내장/외장', name: '선루프' },
      { category: '내장/외장', name: '루프랙 ' },
      { category: '내장/외장', name: '프리미엄 타이어' },
      { category: '내장/외장', name: '클러스터(컬러 LCD)' },
      { category: '내장/외장', name: '전자식 변속버튼 ' },
      { category: '내장/외장', name: '열선 스티어링 휠 ' },
      { category: '내장/외장', name: '앰비언트 무드램프 ' },
    ],
  },
  {
    category: '시트',
    options: [
      { category: '시트', name: '동승석 통풍시트 ' },
      { category: '시트', name: '2열 통풍시트 ' },
      { category: '시트', name: '운전석 자세 메모리 시스템 ' },
      { category: '시트', name: '동승석 릴렉션 컴포트 시트 ' },
      { category: '시트', name: '운전석 파워시트 ' },
      { category: '시트', name: '동승석 파워시트 ' },
      { category: '시트', name: '동승석 워크인 스위치 ' },
      { category: '시트', name: '운전석 통풍시트 ' },
      { category: '시트', name: '뒷좌석 열선시트 ' },
      { category: '시트', name: '앞좌석 열선시트 ' },
    ],
  },
  {
    category: '편의',
    options: [
      { category: '편의', name: '서라운드 뷰 모니터 ' },
      { category: '편의', name: '후방모니터 ' },
      { category: '편의', name: '후측방모니터 ' },
      { category: '편의', name: '버튼시동 스마트키 시스템 ' },
      { category: '편의', name: '하이패스 시스템 ' },
      { category: '편의', name: '스마트폰 무선 충전 ' },
      { category: '편의', name: '헤드업 디스플레이 ' },
      { category: '편의', name: '전동식 트렁크' },
      { category: '편의', name: '디지털 키 ' },
      { category: '편의', name: '2열 커튼' },
      { category: '편의', name: '듀얼 풀오토 에어컨 ' },
      { category: '편의', name: '2열 에어벤트 ' },
    ],
  },
  {
    category: '멀티미디어',
    options: [
      { category: '멀티미디어', name: '디스플레이 오디오 ' },
      { category: '멀티미디어', name: '내비게이션' },
      { category: '멀티미디어', name: '프리미엄 사운드 시스템 ' },
    ],
  },
];
interface ICategory {
  category: string;
  options: IOptions[];
}
interface IOptions {
  category: string;
  name: string;
}

export class OptionForm extends Component {
  setup(): void {
    OptionStore.subscribe(this.render.bind(this), this.constructor.name);
  }
  template(): string {
    const options = JSON.stringify(OptionStore.getState().options);
    const data = DUMMY_DATA;
    return `
    <div class="${styles['form-container']}">
      ${data.map((ele) => this.categoryTemplate(ele)).join('')}
    </div>
    `;
  }

  categoryTemplate({ category, options }: ICategory) {
    return `
    <div class="${styles.container}">
        <div class="${styles.container__title}">${category}</div>      
        <div class="${styles['filter-container']}">
            ${options.map((ele) => this.optionBtnTemplate(ele)).join('')}
        </div>
    </div>    
    `;
  }

  optionBtnTemplate({ category, name }: IOptions) {
    const isActive =
      OptionStore.getState().options.findIndex((ele: IOptions) => {
        return (
          ele.category.trim() === category.trim() &&
          ele.name.trim() === name.trim()
        );
      }) !== -1;
    const buttonClass = isActive ? styles['is-active'] : '';
    const buttonState = isActive ? 'active' : 'inactive';
    return `<button class="${styles['filter-button']} ${buttonClass}" data-state="${buttonState}">${name}</button>`;
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['filter-button']}`, ({ target }) => {
      const $button = target as HTMLButtonElement;
      const buttonState = $button.getAttribute('data-state');
      if (buttonState === 'inactive') this.activeBtn($button);
      if (buttonState === 'active') this.inactiveBtn($button);
    });

    this.addEvent('click', `.${styles.container__title}`, ({ target }) => {
      const $target = target as HTMLDivElement;
      $target.nextElementSibling?.classList.toggle(styles.show);
    });
  }

  activeBtn($button: HTMLButtonElement) {
    const $category = $button.parentElement
      ?.previousElementSibling as HTMLDivElement;
    $button.classList.add(styles['is-active']);
    $button.setAttribute('data-state', 'active');
    OptionStore.dispatch('ADD_CAR_OPTION', {
      option: { name: $button.innerText, category: $category.innerText },
    });
  }

  inactiveBtn($button: HTMLButtonElement) {
    const $category = $button.parentElement
      ?.previousElementSibling as HTMLDivElement;
    $button.classList.remove(styles['is-active']);
    $button.setAttribute('data-state', 'inactive');
    OptionStore.dispatch('DELETE_CAR_OPTION', {
      option: { name: $button.innerText, category: $category.innerText },
    });
  }
}
