interface DynamicObject {
  [property: string]: any;
}

export class Store {
  #state: DynamicObject;
  #listeners: DynamicObject = {};
  #reducer: Function;

  constructor(state: object, reducer: Function) {
    this.#state = state;
    this.#reducer = reducer;
  }

  getState() {
    return { ...this.#state };
  }

  subscribe(func: Function, fnKey: string) {
    this.#listeners[fnKey] = func;
  }

  publish() {
    Object.values(this.#listeners).forEach((listener) => listener());
  }

  async dispatch(actionKey: string, { ...payload }: DynamicObject = {}) {
    this.#state = await this.#reducer(this.#state, actionKey, { ...payload });
    this.publish();
  }
}
