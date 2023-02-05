export const navigate = (to: string, isReplace: boolean = false) => {
  const historyChangeEvent = new CustomEvent('historychange', {
    detail: {
      to,
      isReplace,
    },
  });

  dispatchEvent(historyChangeEvent);
};

export function attachRouterToAnchor(e: Event) {
  const $clicked = e.target as HTMLElement;
  const $anchorTarget = $clicked.closest('a');
  if (!($anchorTarget instanceof HTMLAnchorElement)) return;
  if (!$anchorTarget.matches('[data-link]')) return;
  e.preventDefault();

  const targetURL = $anchorTarget.getAttribute('href');
  if (targetURL) navigate(targetURL);
}
