import {
  checkEmailValidationRequest,
  sendLogInRequest,
  sendRegisterRequest,
} from '@/apis/login';
import Component from '@/core/Component';
import { AuthStore } from '@/store/AuthStore';
import { qs } from '@/utils/querySelector';
import { LoadingHandler } from './LoadingHandler';
import styles from './LoginForm.module.scss';

interface ISignUpData {
  email: string;
  phoneNumber: string;
  userName: string;
  userPassword: string;
}

export class LoginForm extends Component {
  setup(): void {
    this.state.isEmailValid = false;
  }
  template(): string {
    return `
        <div class="${styles.container}" id="container">
            <div class="${styles['form-container']} ${styles['sign-up-container']}">
                <form id="signup-form" >
                    <h1>Create Account</h1>                 
                    <span>or use your email for registration</span>
                    <input id="signup-email" type="email" placeholder="Email" />
                    <input id="signup-tel" type="tel" placeholder="010-1234-5678" />
                    <input id="signup-name" type="text" placeholder="홍길동" />
                    <input id="signup-pwd" type="password" placeholder="Password" />
                    <button id="sign-up-button">Check Email</button>
                    
                </form>
            </div>
            <div class="${styles['form-container']} ${styles['sign-in-container']}">
                <form id="signin-form">
                    <h1>Sign in</h1>
                    <div class="${styles['social-container']}">
                        <a href="#" class="${styles.social}"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input id="signin-email" type="email" placeholder="Email" />
                    <input id="signin-pwd" type="password" placeholder="Password" />
                    <a  id="api-test">Forgot your password?</a>
                    <button id="sign-in-button">Sign In</button>
                </form>
            </div>
            <div class="${styles['overlay-container']}">
                <div class="${styles.overlay}">
                    <div class="${styles['overlay-panel']} ${styles['overlay-left']}">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="${styles.ghost}" id="signIn">Sign In</button>
                    </div>
                    <div class="${styles['overlay-panel']} ${styles['overlay-right']}">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="${styles.ghost}" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        `;
  }
  setEvent(): void {
    this.addEvent('click', '#signUp', this.enterSignUpMode.bind(this));
    this.addEvent('click', '#signIn', this.enterSignInMode.bind(this));
    this.addEvent('click', '#sign-in-button', this.onClickSignIn.bind(this));
    this.addEvent('click', '#sign-up-button', this.onClickSignUp.bind(this));
    this.addEvent('input', '#signup-email', this.onChangeEmail.bind(this));
  }

  enterSignUpMode() {
    const $container = qs('#container', this.target);
    $container?.classList.add(styles['right-panel-active']);
  }

  enterSignInMode() {
    const $container = qs('#container', this.target);
    $container?.classList.remove(styles['right-panel-active']);
  }

  onClickSignIn(e: Event) {
    e.preventDefault();
    const $button = e.target as HTMLButtonElement;
    const $email = qs('#signin-email', this.target) as HTMLInputElement;
    const $password = qs('#signin-pwd', this.target) as HTMLInputElement;
    const inputEls = [$email, $password];

    const email = $email.value;
    const password = $password.value;
    this.resetErrorClass(inputEls);

    if (!email || !password) {
      this.addErrorClassToBlank(inputEls);
      return;
    }
    this.sendSignInRequest({ email, password }, inputEls, $button);
  }

  sendSignInRequest(
    userData: { email: string; password: string },
    inputEls: HTMLInputElement[],
    $button: HTMLButtonElement
  ) {
    const loadingHandler = new LoadingHandler($button);
    loadingHandler.startRequest();

    sendLogInRequest(userData.email, userData.password)
      .then(this.loginSuccess)
      .catch(() => {
        this.resetErrorClass(inputEls);
        this.addErrorClass(inputEls);
        loadingHandler.finishRequest();
      });
  }

  loginSuccess({ data }: any) {
    localStorage.setItem('accessToken', data.accessToken);
    AuthStore.dispatch('LOGIN');
    location.reload();
  }

  loginFail(inputEls: HTMLInputElement[], finishRequest: Function) {
    this.resetErrorClass(inputEls);
    this.addErrorClassToBlank(inputEls);
    finishRequest();
  }

  onClickSignUp(e: Event) {
    e.preventDefault();
    const $button = e.target as HTMLButtonElement;
    const $email = qs('#signup-email', this.target) as HTMLInputElement;
    const $tel = qs('#signup-tel', this.target) as HTMLInputElement;
    const $name = qs('#signup-name', this.target) as HTMLInputElement;
    const $password = qs('#signup-pwd', this.target) as HTMLInputElement;
    const inputEls = [$email, $tel, $name, $password];

    const email = $email.value;
    const phoneNumber = $tel.value;
    const userName = $name.value;
    const userPassword = $password.value;
    const userData = { email, phoneNumber, userName, userPassword };
    if (this.state.isEmailValid) {
      this.resetErrorClass(inputEls);
      if (!email || !phoneNumber || !userName || !userPassword) {
        this.addErrorClassToBlank(inputEls);
        return;
      }
    } else {
      this.resetErrorClass([$email]);
      if (!email) {
        this.addErrorClassToBlank([$email]);
        return;
      }
    }

    this.sendSignUpRequest(userData, inputEls, $button);
  }

  sendSignUpRequest(
    userData: ISignUpData,
    inputEls: HTMLInputElement[],
    $button: HTMLButtonElement
  ) {
    const loadingHandler = new LoadingHandler($button);
    loadingHandler.startRequest();
    if (this.state.isEmailValid) {
      sendRegisterRequest(userData)
        .then(() => {
          loadingHandler.finishRegister();
        })
        .catch(() => {
          this.resetErrorClass(inputEls);
          this.addErrorClassToBlank(inputEls);
          loadingHandler.finishRequest();
        });
    } else {
      checkEmailValidationRequest(userData.email)
        .then(() => {
          this.state.isEmailValid = true;
          this.state.validEmail = userData.email;
          loadingHandler.finishCheckEmail();
        })
        .catch(() => {
          const [$email] = inputEls;
          this.resetErrorClass(inputEls);
          this.addErrorClass([$email]);
          loadingHandler.finishRequest();
        });
    }
  }

  onChangeEmail({ target }: Event) {
    const $button = qs('#sign-up-button', this.target) as HTMLButtonElement;
    if (!(target instanceof HTMLInputElement)) return;
    if (this.state.validEmail !== target.value) {
      this.state.isEmailValid = false;
      $button.textContent = 'Check Email';
    } else {
      this.state.isEmailValid = true;
      $button.textContent = 'Sign Up';
    }
  }

  addErrorClassToBlank($targets: HTMLInputElement[]) {
    $targets.forEach((ele) => {
      if (!ele.value.trim().length) ele.classList.add(styles.error);
    });
  }
  addErrorClass($targets: HTMLInputElement[]) {
    $targets.forEach((ele) => {
      ele.classList.add(styles.error);
    });
  }

  resetErrorClass($targets: HTMLInputElement[]) {
    $targets.forEach((ele) => {
      ele.classList.remove(styles.error);
    });
    void $targets[0]?.offsetWidth;
  }
}
