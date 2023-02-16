export function setQueryStringParameter(key: string, value: any) {
  const params = new URLSearchParams(window.location.search);
  params.set(key, value);
  window.history.replaceState(
    {},
    '',
    decodeURIComponent(`${window.location.pathname}?${params}`)
  );
}

export function getQueryStringParameter(key: string) {
  const urlParams = new URL(location.href).searchParams;
  return urlParams.get(key);
}
