import { qsa } from "./querySelector";

export function loadscript(url: string, initMap: Function) {
  const script = document.createElement("script");
  script.src = url;
  script.type = "text/javascript";
  script.async = true;
  script.defer = true;
  window.initMap = initMap;

  const scriptElem = qsa("script") as NodeListOf<HTMLScriptElement>;
  for (const script of scriptElem) {
    if (script.src.includes("google")) {
      initMap();
      return;
    }
  }

  document.body.appendChild(script);
}
