import Component from '@/core/Component';
import { qs } from '@/utils/querySelector';
import axios from 'axios';
import styles from './LoginForm.module.scss';
export class LoginForm extends Component {
  template(): string {
    return `
        <div class="${styles.container}" id="container">
            <div class="${styles['form-container']} ${styles['sign-up-container']}">
                <form action="#">
                    <h1>Create Account</h1>
                    <div class="${styles['social-container']}">
                        <a href="#" class="${styles.social}"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Password" />
                    <button>Sign Up</button>
                </form>
            </div>
            <div class="${styles['form-container']} ${styles['sign-in-container']}">
                <form id="loginForm">
                    <h1>Sign in</h1>
                    <div class="${styles['social-container']}">
                        <a href="#" class="${styles.social}"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="${styles.social}"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input type="email" id="email" placeholder="Email" />
                    <input type="password" id="password" placeholder="Password" />
                    <a href="#">Forgot your password?</a>
                    <button type="submit" form="loginForm">Sign In</button>
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
        
        <footer>
            <p>
                Created with <i class="fa fa-heart"></i> by
                <a target="_blank" href="https://florin-pop.com">Florin Pop</a>
                - Read how I created this and how you can join the challenge
                <a target="_blank" href="https://www.florin-pop.com/blog/2019/03/double-slider-sign-in-up-form/">here</a>.
            </p>
        </footer>
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

    this.addEvent('submit', 'form', (e) => {
      e.preventDefault();
      const $email = qs('#email', this.target) as HTMLInputElement;
      const $password = qs('#password', this.target) as HTMLInputElement;
      const enteredEmail = $email.value;
      const enteredPassword = $password.value;
      axios
        .post('/login', {
          email: enteredEmail,
          userPassword: enteredPassword,
        })
        .then((data) => console.log);
    });
  }
}
