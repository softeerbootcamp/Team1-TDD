import styles from '@/styles/loadingSpinner.module.scss';
const loadingSpinner = document.createElement('span');
loadingSpinner.classList.add(styles.loader);
loadingSpinner.classList.add(styles.hidden);
document.body.appendChild(loadingSpinner);

export const startLoading = () => {
  loadingSpinner.classList.remove(styles.hidden);
};

export const finishLoading = () => {
  loadingSpinner.classList.add(styles.hidden);
};
