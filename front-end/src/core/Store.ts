export class Store {
  #state: object;
  #listeners: any[] = [];
  #reducer;

  constructor(
    state: object,
    reducer: (state: object, actionKey: string, payload: object) => object
  ) {
    this.#state = state;
    this.#reducer = reducer;
  }

  getState() {
    return { ...this.#state };
  }

  subscribe(func: any) {
    this.#listeners.push(func);
  }

  publish() {
    this.#listeners.forEach((listener) => listener());
  }

  async dispatch(actionKey: string, { ...payload }: object = {}) {
    this.#state = await this.#reducer(this.#state, actionKey, { ...payload });
    this.publish();
  }
}
