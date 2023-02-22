import Component from '@/core/Component';

export class Footer extends Component {
  template(): string {
    return `
    <div class="container">
    <div class="row">
      <div class="col-1">
        <h6>About</h6>
        <p class="text-justify">
        TDD 서비스는 사용자들간 시승을 공유함으로서 차량에 대한 다채로운 경험을 제공하고자 기획되었습니다. 우리는 TDD서비스를 통해 사용자들에게 차량에 대한 우수한 접근성과 다양한 경험을 확대하는 것을 목표로 하고 있습니다. 사용자들간의 시승을 공유함으로서 차량에 대한 우리는 이 불편함들을 해결하고자 했습니다. 차를 구매하기 전 다양한 차를 직접 체험해 보고싶은 사용자, 실제 사용자의 실사용 후기를 듣고싶은 사용자를 매칭해줍니다. 우리는 주변에 시승할 수 있는 곳이 마땅치 않은 사람들에게 비교적 가까운 거리와 다양한 시간대에 시승할 수 있는 기회를 제공하고, 내가 원하는 옵션의 차량, 나와 비슷한 환경에서 생활하는 사람의 차량을 경험할 수 있으며, 실제 차량 소유자의 객관적인 리뷰를 얻을 수 있습니다.
        </p>
      </div>

      <div class="col-2">
        <h6>Developers</h6>
        <ul class="footer-links">
          <li><a href="https://github.com/2je0">2JE0</a></li>
          <li><a href="https://github.com/MyuB">MyuB</a></li>
          <li><a href="https://github.com/River_Mt">River_Mt</a></li>
          <li><a href="https://github.com/jieonkim23">jieonkim23</a></li>
          <li><a href="https://github.com/psm9718">psm9718</a></li>
        </ul>
      </div>

      <div class="col-2">
        <h6>Quick Links</h6>
        <ul class="footer-links">
          <li><a data-link href="/aboutus">About Us</a></li>
          <li><a data-link href="/experiencing">Experiencing</a></li>
          <li><a data-link href="/sharing">Sharing</a></li>
          <li><a data-link href="/mypage">My page</a></li>
        </ul>
      </div>
    </div>
    <hr>
  </div>
  <div class="container">
    <div class="row">
      <div >
        <p class="copyright-text">Copyright &copy; 2023 All Rights Reserved by 
     <a data-link href="/">Let's TDD</a>.
        </p>
      </div>

      <div class="logo">
        <img src="${process.env.VITE_IMAGE_URL}/MAIN_LOGO.png" style="width:35px" alt="tdd-logo" />
      </div>
    </div>
  </div>
    `;
  }
}
