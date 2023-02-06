import Component from '@/core/Component';
import styles from './OptionForm.module.scss';
import { OptionStore } from '@/store/OptionStore/OptionStore';
const DUMMY_DATA = {
  options: [
    {
      category: '차종',
      option: [
        '수소 / 전기차',
        'N / N Line',
        '승용',
        'SUV',
        'MPV',
        '소형트럭&택시',
        '트럭',
        '버스',
      ],
    },

    {
      category: '엔진',
      option: ['디젤', '전기', '가솔린', '하이브리드', '수소', 'LPG'],
    },
    {
      category: '안전/성능',
      option: [
        '주차 거리 경고-전방 ',
        '주차 거리 경고-후방 ',
        '진동경고 스티어링 휠 ',
        '후륜 멀티링크 서스펜션 ',
        '랙 구동형 전동식 파워스티어링(RMDPS) ',
      ],
    },
    {
      category: '지능형 안전기술',
      option: [
        '후측방 충돌방지 보조 ',
        '안전 하차 보조 ',
        '전방 충돌방지 보조 ',
        '차로 이탈방지 보조 ',
        '스마트 크루즈 컨트롤 ',
        '원격 스마트 주차 보조 ',
        '후석 승객 알림 ',
      ],
    },
    {
      category: '내장/외장',
      option: [
        'LED 헤드램프',
        'LED 리어콤비램프 ',
        '선루프',
        '루프랙 ',
        '프리미엄 타이어',
        '클러스터(컬러 LCD)',
        '전자식 변속버튼 ',
        '열선 스티어링 휠 ',
        '앰비언트 무드램프 ',
      ],
    },
    {
      category: '시트',
      option: [
        '동승석 통풍시트 ',
        '2열 통풍시트 ',
        '운전석 자세 메모리 시스템 ',
        '동승석 릴렉션 컴포트 시트 ',
        '운전석 파워시트 ',
        '동승석 파워시트 ',
        '동승석 워크인 스위치 ',
        '운전석 통풍시트 ',
        '뒷좌석 열선시트 ',
        '앞좌석 열선시트 ',
      ],
    },
    {
      category: '편의',
      option: [
        '서라운드 뷰 모니터 ',
        '후방모니터 ',
        '후측방모니터 ',
        '버튼시동 스마트키 시스템 ',
        '하이패스 시스템 ',
        '스마트폰 무선 충전 ',
        '헤드업 디스플레이 ',
        '전동식 트렁크',
        '디지털 키 ',
        '2열 커튼',
        '듀얼 풀오토 에어컨 ',
        '2열 에어벤트 ',
      ],
    },
    {
      category: '멀티미디어',
      option: ['디스플레이 오디오 ', '내비게이션', '프리미엄 사운드 시스템 '],
    },
  ],
};
interface ICategory {
  category: string;
  option: string[];
}

export class OptionForm extends Component {
  template(): string {
    const data = DUMMY_DATA;
    return `
    <div class="${styles.container}">
      ${data.options.map((ele) => this.categoryTemplate(ele)).join('')}
    </div>
    `;
  }
  mounted(): void {
    OptionStore.dispatch('OPTION_INIT');
  }
  categoryTemplate({ category, option }: ICategory) {
    return `
    <div>
        <div class="${styles.container__title}">${category}</div>    
        <div id="post-categories" class="${styles['filter-container']}">
            ${option.map((option) => this.optionBtnTemplate(option)).join('')}
        </div>
    </div>
    `;
  }

  optionBtnTemplate(option: string) {
    return `<button class="${styles['filter-button']}" data-state="inactive">${option}</button>`;
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['filter-button']}`, ({ target }) => {
      const $button = target as HTMLButtonElement;
      const $category = $button.parentElement
        ?.previousElementSibling as HTMLDivElement;
      const buttonState = $button.getAttribute('data-state');

      if (buttonState == 'inactive') {
        $button.classList.add(styles['is-active']);
        $button.setAttribute('data-state', 'active');
        OptionStore.dispatch('ADD_CAR_OPTION', {
          option: { name: $button.innerText, category: $category.innerText },
        });
      } else {
        $button.classList.remove(styles['is-active']);
        $button.setAttribute('data-state', 'inactive');
        OptionStore.dispatch('DELETE_CAR_OPTION', {
          option: { name: $button.innerText, category: $category.innerText },
        });
      }
    });
  }
}
