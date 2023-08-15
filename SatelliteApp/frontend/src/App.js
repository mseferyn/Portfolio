import './App.css';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import 'bootstrap/dist/css/bootstrap.min.css';
import L from 'leaflet';
import icon from './icon.png';
import Leaflet, { bounds } from 'leaflet';
import Button from '@mui/material/Button';
import { useEffect } from 'react';
import axios from 'axios'

function App() {

  useEffect(() => {
    let map = new L.Map('map', {
      center: [54.21, 18.38],
      zoom: 6,
    });

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap</a> contributors',
      minZoom: 3
    }).addTo(map);

    var southWest = L.latLng(-89.98155760646617, -180),
      northEast = L.latLng(89.99346179538875, 180);

    var bounds = L.latLngBounds(southWest, northEast);

    map.setMaxBounds(bounds);
    map.on('drag', function () {
      map.panInsideBounds(bounds, { animate: false });
    });

    const handlePopupClick = () => {
      console.log("XDDDDD")
    }

    function addPopup (name, id) {
      let btnSubscribe = document.createElement('button');
      btnSubscribe.innerHTML = "<h5>Subscribe!</h5>"
      btnSubscribe.onclick = function () {
            console.log("Subscribe " + id)
            let request = id;
            axios.post('http://localhost:8080/user/favs/' + request)
              .then(res => {
                console.log(res);
              }
              )
          }

      let nameDiv = document.createElement('div')
      nameDiv.innerHTML = "<h6> Name: " + name + "</h6>"

      let idDiv = document.createElement('div')
      idDiv.innerHTML = "<h6> NORAD ID:" + id + "</h6>"
          
      let btnDiv = document.createElement('div')
      btnDiv.append(nameDiv)
      btnDiv.append(idDiv)
      btnDiv.append(btnSubscribe)
      
      return (btnDiv)
    }

    let mapping = []
    const fetchData = async () => {
      await axios.post('http://localhost:8080/sat/complex/' + "NORAD_ID:&COUNTRY:&LAUNCH_DATE:")
      .then(res => {
        console.log(res)
        mapping = (res.data)
        mapping.map(m => (
          L.marker([m.latitude, m.longitude], { icon: greenIcon })
          .bindPopup(addPopup(m.name, m.noradcatId)).openPopup()
          .addTo(map)
        ))
      }
      )
    }
    fetchData();
    // console.log("MAPPING " + mapping)
    // mapping.map(m => (
    //   L.marker([m.latitude, m.longitude], { icon: greenIcon })
    //   .bindPopup(addPopup(m.name, m.noradcatId)).openPopup()
    //   .addTo(map)
    // ))
  });

  var greenIcon = L.icon({
    iconUrl: icon,

    iconSize: [30, 30], // size of the icon
    shadowSize: [50, 64], // size of the shadow
    iconAnchor: [10, 10], // point of the icon which will correspond to marker's location
    shadowAnchor: [4, 62],  // the same for the shadow
    popupAnchor: [12, -12] // point from which the popup should open relative to the iconAnchor
  });

  return (
    <div id="map"
    >
      {/* {satellites.map(sat => (
          <Marker
            icon={greenIcon}
            position={[sat.gps.longitude, sat.gps.latitude]}>
            <Popup>
              <div>
                {sat.name}
              </div>
              <div>
                {"NORAD ID: " + sat.id}
              </div>
              <Button>
                Subscribe!
              </Button>
            </Popup>
          </Marker>
        ))} */}
    </div>
  );
}

export default App;
