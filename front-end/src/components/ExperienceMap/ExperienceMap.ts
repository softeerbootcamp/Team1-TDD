import Component from "@/core/Component";
import styles from "./ExperienceMap.module.scss";
import { mapStyle } from "@/utils/mapStyle";
import { seoulLocations } from "./dummyData";
import { MarkerClusterer } from "@googlemaps/markerclusterer";
import { loadscript } from "@/utils/googleAPI";

export class ExperienceMap extends Component {
  template(): string {
    return `
    <div class="${styles["desc"]}">시승해보고 싶은 싶은 위치를 골라주세요!</div>
    <div id="googleMap" class="${styles["googleMap"]}"></div>`;
  }

  mounted(): void {
    let markers: google.maps.Marker[] = [];
    let markerCluster: MarkerClusterer;

    loadscript(
      `https://maps.googleapis.com/maps/api/js?key=${process.env.VITE_API_KEY}&callback=initMap`,
      initMap
    );

    function refreshMap(map: google.maps.Map) {
      if (markerCluster instanceof MarkerClusterer) {
        markerCluster.clearMarkers();
      }
      markers = [];
      createMarkers(map);
    }

    function createMarkers(map: google.maps.Map) {
      for (let i = 0; i < seoulLocations.length; i++) {
        let mker = new google.maps.Marker({
          position: seoulLocations[i] as google.maps.LatLng,
          map,
          animation: google.maps.Animation.DROP,
        });
        markers.push(mker);
      }
      markerCluster = new MarkerClusterer({ markers, map });
    }

    function initMap() {
      const myLatlng = { lat: 37.4419, lng: 126.67581 };
      const map = new google.maps.Map(
        document.getElementById("googleMap")! as HTMLElement,
        {
          zoom: 15,
          center: myLatlng,
          styles: mapStyle() as object[],
        }
      );
      refreshMap(map);
    }
  }
}
