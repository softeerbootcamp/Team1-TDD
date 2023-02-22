import { Home } from '@/components/Home/Home';
import { routes } from '@/constants/routeInfo';

export class Router {
  constructor(private $container: HTMLElement) {
    this.init();
    this.route();
  }

  private init() {
    window.addEventListener('historychange', (e: Event) => {
      e.preventDefault();
      const { to, isReplace } = (<CustomEvent>e).detail;
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
    const TargetPage = this.findMatchedRoute()?.element || Home;
    new (TargetPage as any)(this.$container);
  }
}
