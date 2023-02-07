import Component from "@/core/Component";
import styles from "./SharingMap.module.scss";
import { mapStyle } from "@/utils/mapStyle";
import { loadscript } from "@/utils/googleAPI";

export class SharingMap extends Component {
  template(): string {
    return `
    <div class="${styles["desc"]}">공유하고 싶은 위치를 골라주세요!</div>
    <div id="googleMap" class="${styles["googleMap"]}"></div>`;
  }

  mounted(): void {
    loadscript(
      `https://maps.googleapis.com/maps/api/js?key=${process.env.VITE_API_KEY}&callback=initMap`,
      initMap
    );

    let userLocation: google.maps.LatLng;
    async function getCurrentLocation(map: google.maps.Map) {
      if (navigator.geolocation) {
        await navigator.geolocation.getCurrentPosition(function (position) {
          const lat = position.coords.latitude;
          const lng = position.coords.longitude;
          userLocation = new google.maps.LatLng(lat, lng);
          map.panTo(userLocation);
        });
      } else {
        alert("something wrong");
      }
    }

    function placeMarker(
      position: google.maps.LatLng,
      map: google.maps.Map,
      geocoder: google.maps.Geocoder
    ) {
      const marker = new google.maps.Marker({
        position: position,
        map: map,
      });
      map.panTo(position);
      let content;
      geocoder.geocode({ location: position }, (response) => {
        content = response[0].formatted_address;
        const infoWindow = new google.maps.InfoWindow({
          content: content,
        });
        infoWindow.open(map, marker);
      });
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
      const geocoder = new google.maps.Geocoder();
      getCurrentLocation(map);

      map.addListener("click", (mapsMouseEvent) => {
        placeMarker(mapsMouseEvent.latLng, map, geocoder);
      });
    }
  }
}
