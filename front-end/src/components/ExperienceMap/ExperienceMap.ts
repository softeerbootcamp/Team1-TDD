import Component from '@/core/Component';
import styles from './ExperienceMap.module.scss';
import { mapStyle } from '@/utils/mapStyle';
import { loadscript } from '@/utils/googleAPI';
import { qs } from '@/utils/querySelector';
import { mapInfo } from './interface';

export class ExperienceMap extends Component {
  setup() {
    const location = this.props.store.getState().mapInfo;
    const initLocation = { lat: location?.centerLat, lng: location?.centerLng };
    const initZoom = location?.zoom;

    this.state.markers = [];
    this.state.userLocation = location
      ? initLocation
      : {
          lat: 37.5326,
          lng: 127.024612,
        };
    this.state.zoom = initZoom || 15;
    this.state.map = null;

    this.props.store.subscribe(
      this.updateMarkers.bind(this),
      this.constructor.name
    );
  }

  template(): string {
    return `
    <div class=${styles.container}>
      <button class="${styles['find-my-position']} ${styles.jelly}">내 위치를 찾아줘..!</button>
      <span class="${styles.loader} ${styles.hidden}"></span>
    </div>
    <div id="googleMap" class="${styles.googleMap}"></div>
    `;
  }

  mounted(): void {
    this.init();
  }

  init() {
    loadscript(
      `https://maps.googleapis.com/maps/api/js?key=${process.env.VITE_API_KEY}&callback=initMap`,
      this.initMap.bind(this)
    );
  }

  initMap() {
    const map = new google.maps.Map(qs('#googleMap')!, {
      zoom: this.state.zoom,
      center: this.state.userLocation as google.maps.LatLng,
      styles: mapStyle() as object[],
      disableDefaultUI: true,
    });
    this.state.map = map;
    this.moveMap();

    google.maps.event.addListener(
      map,
      'bounds_changed',
      this.handleDebounce(() => {
        this.props.changePositionHandler(this.getMapInfo());
      }, 500)
    );

    this.updateMarkers();
  }

  moveToMyLocation() {
    this.showSpinner();

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        ({ coords }) => {
          const { latitude, longitude } = coords;
          this.setMapPosition(latitude, longitude, 17);
          this.hideSpinner();
        },
        () => {
          console.log('moving map has failed');
          this.hideSpinner();
        },
        {
          enableHighAccuracy: false,
          timeout: 5000,
          maximumAge: Infinity,
        }
      );
    } else {
      console.log('Geolocation is not supported by this browser.');
    }
  }

  moveMap() {
    this.state.map.panTo(this.state.userLocation);
  }

  updateMarkers() {
    this.showSpinner();
    const locations = this.props.store
      .getState()
      .filteredPost.map((ele: any) => {
        return {
          lat: +ele.location.latitude.trim(),
          lng: +ele.location.longitude.trim(),
        };
      });
    this.clearMarkers();
    this.createMarkers(locations);
    this.hideSpinner();
  }

  createMarkers(locations: google.maps.LatLng[]) {
    this.state.markers = locations.map(
      (loc: google.maps.LatLng) =>
        new google.maps.Marker({
          position: loc,
          map: this.state.map,
        })
    );
  }

  handleDebounce(callback: Function, limit: number) {
    let timeout: ReturnType<typeof setTimeout>;
    return (...args: any) => {
      clearTimeout(timeout);
      timeout = setTimeout(() => {
        callback.apply(this, args);
      }, limit);
    };
  }

  getMapInfo(): mapInfo {
    const { map } = this.state;
    const mapBounds = map.getBounds();
    const lngHi = mapBounds.Ia.hi;
    const lngLo = mapBounds.Ia.lo;
    const latHi = mapBounds.Ua.hi;
    const latLo = mapBounds.Ua.lo;
    return {
      centerLat: map.getCenter().lat(),
      centerLng: map.getCenter().lng(),
      zoom: map.getZoom(),
      latHi,
      latLo,
      lngHi,
      lngLo,
    };
  }

  setMapPosition(lat: number, lng: number, zoom: number | null = null) {
    const { map } = this.state;
    const newCenter = { lat, lng };
    map.panTo(newCenter, 1000, google.maps.Animation.BOUNCE);
    zoom && map.setZoom(zoom, { animation: google.maps.Animation.BOUNCE });
  }

  clearMarkers() {
    this.state.markers.forEach((marker: google.maps.Marker) =>
      marker.setMap(null)
    );
    this.state.markers = [];
  }

  setEvent(): void {
    this.addEvent(
      'click',
      `.${styles['find-my-position']}`,
      this.moveToMyLocation.bind(this)
    );
  }

  showSpinner() {
    const loader = qs(`.${styles.loader}`, this.target);
    loader?.classList.remove(styles.hidden);
  }
  hideSpinner() {
    const loader = qs(`.${styles.loader}`, this.target);
    loader?.classList.add(styles.hidden);
  }
}
