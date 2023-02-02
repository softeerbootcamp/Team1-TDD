interface IRoute {
  path: RegExp;
  element: Function;
}

export class Router {
  constructor(private routes: IRoute[]) {
    this.init();
    this.route();
  }
  private init() {
    window.addEventListener('historychange', ({ detail }) => {
      const { to, isReplace } = detail;

      if (isReplace || to === location.pathname)
        history.replaceState(null, '', to);
      else history.pushState(null, '', to);

      this.route();
    });

    window.addEventListener('popstate', () => {
      this.route();
    });
  }

  private findMatchedRoute(this: Router) {
    return this.routes.find((route) => route.path.test(location.pathname));
  }

  private route(this: Router) {
    this.findMatchedRoute()?.element();
  }

  navigate(to: string, isReplace: boolean = false): void {
    const historyChangeEvent = new CustomEvent('historychange', {
      detail: {
        to,
        isReplace,
      },
    });
    dispatchEvent(historyChangeEvent);
  }
}
