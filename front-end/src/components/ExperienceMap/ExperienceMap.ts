import Component from '@/core/Component';
import styles from './ExperienceMap.module.scss';
import { mapStyle } from '@/utils/mapStyle';
import { loadscript } from '@/utils/googleAPI';
import { qs } from '@/utils/querySelector';
import { mapInfo } from './interface';
import { markerController } from '@/store/MarkerController';
import { initAutocomplete } from '@/utils/autoCompletor';
import { showNotification } from '@/utils/notification';
import { CopyLinkBtn } from '../CopyLinkBtn/CopyLinkBtn';

interface Ilocation {
  lat: number;
  lng: number;
  id: number;
  marker?: google.maps.Marker;
}
export class ExperienceMap extends Component {
  setup() {
    this.state.locations = [];
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
    markerController.setListener(this.setMapPosition.bind(this));
  }

  template(): string {
    return `
    <div class=${styles.container}>
      <button class="${styles['find-my-position']} ${styles.jelly}">내 위치 찾기</button>
      <div id="copy-link-btn"></div>
      <span class="${styles.loader} ${styles.hidden}"></span>
    </div>
    <input
      id="pac-input"
      class="controls ${styles.search}"
      type="text"
      placeholder="Search Box"
    />
    <div id="googleMap" class="${styles.googleMap}"></div>
    `;
  }

  mounted(): void {
    this.init();
    const $copyLinkBtn = qs('#copy-link-btn', this.target);
    new CopyLinkBtn($copyLinkBtn as HTMLDivElement);
  }

  init() {
    loadscript(
      `https://maps.googleapis.com/maps/api/js?key=${process.env.VITE_API_KEY}&callback=initMap&libraries=places`,
      this.initMap.bind(this)
    );
  }

  initMap() {
    const map = new google.maps.Map(qs('#googleMap')!, {
      zoom: this.state.zoom,
      center: this.state.userLocation as google.maps.LatLng,
      styles: mapStyle() as object[],
      minZoom: 12,
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
    initAutocomplete(map);
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
          showNotification('moving map has failed');
          this.hideSpinner();
        },
        {
          enableHighAccuracy: false,
          timeout: 5000,
          maximumAge: Infinity,
        }
      );
    } else {
      showNotification('Geolocation is not supported by this browser.');
    }
  }

  moveMap() {
    this.state.map.panTo(this.state.userLocation);
  }

  updateMarkers() {
    const previousLocations: Ilocation[] = this.state.locations;
    const updatedLocations: { lat: number; lng: number; id: number }[] =
      this.props.store.getState().filteredPost.map((ele: any) => {
        return {
          lat: +ele.location.latitude.trim(),
          lng: +ele.location.longitude.trim(),
          id: ele.post.id,
        };
      });
    const toBeAdded = updatedLocations.filter(
      (elementB) =>
        !previousLocations.some((elementA) => elementA.id === elementB.id)
    );
    const toBeDeleted = previousLocations.filter(
      (elementB) =>
        !updatedLocations.some((elementA) => elementA.id === elementB.id)
    );
    const toBeStay = previousLocations.filter((elementA) =>
      updatedLocations.some((elementB) => elementA.id === elementB.id)
    );
    this.state.locations = [...toBeStay];
    this.addMarkers(toBeAdded);
    this.deleteMarkers(toBeDeleted);
  }

  addMarkers(locations: Ilocation[]) {
    const newLocations = locations.map((loc: Ilocation) => {
      return {
        ...loc,
        marker: new google.maps.Marker({
          position: { lat: loc.lat, lng: loc.lng },
          map: this.state.map,
          title: loc.id.toString(),
        }),
      };
    });
    newLocations.forEach((location: Ilocation) => {
      const { marker } = location;
      if (!(marker instanceof google.maps.Marker)) return;
      const infowindow = new google.maps.InfoWindow({
        content: `<a data-link href="/details/${marker.getTitle()}">상세 글 보기</a>`,
      });
      google.maps.event.addListener(marker, 'click', () => {
        const { map } = this.state;
        infowindow.open(map, marker);
      });
    });
    this.state.locations = [...this.state.locations, ...newLocations];
  }

  deleteMarkers(locations: Ilocation[]) {
    locations.forEach(({ marker }) => {
      if (!marker) return;
      marker.setMap(null);
    });
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
    const zoomNow = map.getZoom();
    const newCenter = { lat, lng };
    map.panTo(newCenter, 1000, google.maps.Animation.BOUNCE);
    zoom && map.setZoom(zoom, { animation: google.maps.Animation.BOUNCE });
    zoom ||
      map.setZoom(zoomNow + 2, { animation: google.maps.Animation.BOUNCE });
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
