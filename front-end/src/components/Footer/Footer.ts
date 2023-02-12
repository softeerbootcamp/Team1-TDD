import Component from '@/core/Component';

export class Footer extends Component {
  template(): string {
    return `
    <div class="container">
    <div class="row">
      <div class="col-1">
        <h6>About</h6>
        <p class="text-justify">열매를 불러 따뜻한 새가 그들의 부패를 원대하고, 있는가? 날카로우나 곳으로 목숨을 그들의 이상은 힘차게 봄바람이다. 역사를 주며, 보는 것은 뿐이다. 평화스러운 그들의 우리의 어디 관현악이며, 싶이 할지니, 그들의 교향악이다. 피가 꽃이 뭇 그들은 할지니, 그와 있음으로써 심장은 힘있다.

        뜨거운지라, 희망의 천하를 우리 투명하되 구하기 위하여서, 위하여 대중을 약동하다. 별과 길을 옷을 있으랴? 품고 이상이 그들의 끓는다. 광야에서 속에서 낙원을 뭇 시들어 황금시대다. 인도하겠다는 피에 능히 우리의 얼마나 그들은 것이다. 꾸며 투명하되 우리 약동하다. 싹이 힘차게 가치를 것은 하는 생생하며, 시들어 것이다.</p>
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
          TDD LOGO
      </div>
    </div>
  </div>
    `;
  }
}
