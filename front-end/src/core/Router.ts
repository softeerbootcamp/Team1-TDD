import { routes } from '@/constants/routeInfo';
import { NotFound } from '@/pages/notfound';

export class Router {
  constructor(private $container: HTMLElement) {
    this.init();
    this.route();
  }

  private init() {
    window.addEventListener('historychange', (e) => {
      e.preventDefault();
      const { to, isReplace } = e.detail;

      if (isReplace || to === location.pathname)
        history.replaceState(null, '', to);
      else history.pushState(null, '', to);

      this.route();
    });

    window.addEventListener('popstate', () => {
      this.route();
    });
  }

  private findMatchedRoute() {
    return routes.find((route) => route.path.test(location.pathname));
  }

  private route() {
    const TargetPage = this.findMatchedRoute()?.element || NotFound;
    new TargetPage(this.$container);
  }
}
