import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import axios from 'axios';
import styles from './LoginForm.module.scss';
export class LoginForm extends Component {
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
                    <button type="submit" form="signup-form">Sign Up</button>
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
                    <button type="submit" form="signin-form">Sign In</button>
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
    this.addEvent('click', '#signUp', () => {
      const $container = qs('#container', this.target);
      $container?.classList.add(styles['right-panel-active']);
    });
    this.addEvent('click', '#signIn', () => {
      const $container = qs('#container', this.target);
      $container?.classList.remove(styles['right-panel-active']);
    });
    this.addEvent('click', '#api-test', () => {
      axios
        .get(`http://localhost:8080/options/sonata`)
        .then((data) => console.log(data))
        .catch((error) => console.log(error));
    });
    this.addEvent('submit', '#signin-form', (e) => {
      e.preventDefault();
      const $email = qs('#signin-email', this.target) as HTMLInputElement;
      const $password = qs('#signin-pwd', this.target) as HTMLInputElement;
      const enteredEmail = $email.value;
      const enteredPassword = $password.value;
      axios
        .post(`http://localhost:8080/login`, {
          email: enteredEmail,
          userPassword: enteredPassword,
        })
        .then((data) => console.log(data))
        .catch((error) => console.log(error));
    });

    this.addEvent('submit', '#signup-form', (e) => {
      e.preventDefault();
      const $email = qs('#signup-email', this.target) as HTMLInputElement;
      const $tel = qs('#signup-tel', this.target) as HTMLInputElement;
      const $name = qs('#signup-name', this.target) as HTMLInputElement;
      const $password = qs('#signup-pwd', this.target) as HTMLInputElement;
      const enteredEmail = $email.value;
      const enteredTel = $tel.value;
      const enteredName = $name.value;
      const enteredPassword = $password.value;
      axios
        .post('http://localhost:8080/users', {
          email: enteredEmail,
          phoneNumber: enteredTel,
          userName: enteredName,
          userPassword: enteredPassword,
        })
        .then((data) => console.log(data))
        .catch((error) => console.log(error));
    });
  }
}
