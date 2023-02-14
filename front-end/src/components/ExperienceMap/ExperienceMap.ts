import Component from '@/core/Component';
import styles from './ExperienceMap.module.scss';
import { mapStyle } from '@/utils/mapStyle';
import { MarkerClusterer } from '@googlemaps/markerclusterer';
import { loadscript } from '@/utils/googleAPI';
import { qs } from '@/utils/querySelector';
import { mapInfo } from './interface';

export class ExperienceMap extends Component {
  setup() {
    this.state.markers = [];
    this.state.userLocation = {};
    this.state.map = null;
    if (this.props.hasOwnProperty('ends')) {
      this.state.userLocation = {
        lat: (this.props.ends.latHi + this.props.ends.latLo) / 2,
        lng: (this.props.ends.lngHi + this.props.ends.lngLo) / 2,
      };
    } else {
      this.state.userLocation = {
        lat: 37.56,
        lng: 127.0,
      };
    }
  }

  template(): string {
    return `
    <div class="${styles['desc']}">시승해보고 싶은 싶은 위치를 골라주세요!</div>
    <div id="googleMap" class="${styles['googleMap']}"></div>
    <button id="make-markers">make markers</button>
    <button id="move-my-location">move</button>
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
      zoom: 15,
      center: this.state.userLocation as google.maps.LatLng,
      styles: mapStyle() as object[],
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

    this.refreshMap();
  }

  moveToMyLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        ({ coords }) => {
          const { latitude, longitude } = coords;
          this.setMapPosition(latitude, longitude);
        },
        () => {
          console.log('moving map has failed');
        }
      );
    } else {
      console.log('Geolocation is not supported by this browser.');
    }
  }

  moveMap() {
    this.state.map.panTo(this.state.userLocation);
  }

  createMarkers() {
    const markers = this.props.locations.map(
      (loc: google.maps.LatLng) =>
        new google.maps.Marker({
          position: loc,
          map: this.state.map,
          animation: google.maps.Animation.DROP,
        })
    );
    this.state.markers = [...this.state.markers, ...markers];
    const { map } = this.state;
    new MarkerClusterer({ map, markers });
  }

  refreshMap() {
    this.state.markers = [];
    this.createMarkers();
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
    const lngHi = mapBounds.Ma.hi;
    const lngLo = mapBounds.Ma.lo;
    const latHi = mapBounds.Ya.hi;
    const latLo = mapBounds.Ya.lo;
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
    map.setCenter(newCenter);
    zoom && map.setZoom(zoom);
  }

  setEvent(): void {
    this.addEvent('click', `.${styles['desc']}`, () => {
      this.clearMarkers();
      console.log(this.state.markers);
    });
    this.addEvent('click', `#make-markers`, () => {
      this.createMarkers();
      console.log(this.state.markers);
    });
    this.addEvent('click', `#move-my-location`, () => {
      this.moveToMyLocation();
    });
  }

  clearMarkers() {
    this.state.markers.forEach((marker: google.maps.Marker) =>
      marker.setMap(null)
    );
    this.state.markers = [];
  }
}
