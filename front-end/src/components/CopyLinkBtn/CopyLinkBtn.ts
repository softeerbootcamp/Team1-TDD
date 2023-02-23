import Component from '@/core/Component';
import { OptionStore } from '@/store/OptionStore/OptionStore';
import { showNotification } from '@/utils/notification';
import styles from './CopyLinkBtn.module.scss';
export class CopyLinkBtn extends Component {
  template(): string {
    return `
    <button id="copy-link" class="${styles['copy-link']} ${styles['copy-link-flip-down']}">이 검색결과를 공유하기
        <div class="${styles.front}">이 검색결과를 공유하기</div>
        <div class="${styles.back}">CLICK!</div>
      </button>
        `;
  }
  setEvent(): void {
    this.addEvent('click', `.${styles.back}`, () => {
      const state = OptionStore.getState();
      const { carModel, options, dates, mapInfo } = state;
      const carUrl = carModel ? `car=${carModel}` : '';
      const optionUrl = options.length
        ? `&options=${JSON.stringify(options)}`
        : '';
      const dateUrl = dates.length ? `&dates=${JSON.stringify(dates)}` : '';
      const mapnUrl = mapInfo ? `&location=${JSON.stringify(mapInfo)}` : '';
      const params = encodeURI(`${carUrl}${optionUrl}${dateUrl}${mapnUrl}`);
      const link = `${location.origin}/experiencing?${params}`;

      const input = document.createElement('input');
      input.style.position = 'fixed';
      input.style.opacity = '0';
      input.value = link;
      document.body.appendChild(input);

      input.select();

      document.execCommand('copy');

      document.body.removeChild(input);

      showNotification('링크가 복사 되었습니다.');
    });
  }
}
