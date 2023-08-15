import React, { Component } from 'react';
import { useRef } from 'react';
import { useState } from 'react';
import '../App';
import { useDetectOutsideClick } from "./useDetectOutsideClick";
import axios from 'axios'
import Button from '@mui/material/Button';

export default function AdvancedSearch() {
  const dropdownRef = useRef(null);
  const [isActive, setIsActive] = useDetectOutsideClick(dropdownRef, false);
  const [noradId, setNoradId] = useState()
  const [country, setCountry] = useState()
  const [launchDate, setLaunchDate] = useState()
  const [searched, setSearched] = useState(false)
  const [open, setOpen] = React.useState(false);
  const [received, setReceived] = useState([]);

  const mapRef = useRef();

  const onClickDropdown = () => {
    setIsActive(!isActive)
  };

  function handleNoradIdChange(event) {
    setNoradId(event.target.value);
  }

  function handleCountryChange(event) {
    setCountry(event.target.value);
  }

  function handleLaunchDateChange(event) {
    setLaunchDate(event.target.value);
  }

  function handleSubscribe(id) {
    console.log("Subscribe: " + id)
    let request = id;
    axios.post('http://localhost:8080/user/favs/' + request)
      .then(res => {
        console.log(res);
      }
      )
  }

  const handleSearch = () => {
    console.log(received)
    let request = "NORAD_ID:" + noradId + "&COUNTRY:" + country + "&LAUNCH_DATE:" + launchDate;
    axios.post('http://localhost:8080/sat/complex/' + request)
      .then(res => {
        console.log(res);
        console.log(res.data);
        setReceived(res.data)
      }
      )
    setSearched(true)
    setNoradId("")
    setCountry("")
    setLaunchDate("")
  };

  function ShowSatellites() {
    if (searched === true) {
      if (received.length === 0 ) {
        return (
          <div>
            Please wait...
          </div>
        )
      }
      else {
        return (
          <div>
            Satellites found:
            {received.map(sat => (
              <div className="single-satellite">
                <div>
                  {sat.noradcatId}
                </div>
                <div>
                  {sat.name}
                </div>
                <Button
                  onClick={() => handleSubscribe(sat.noradcatId)}
                  style={{
                    color: 'white',
                    borderRadius: 15,
                    backgroundColor: "#21b6ae",
                    padding: "10px 20px",
                    fontSize: "12px",
                    margin: "4px"
                  }}
                >
                  Subscribe!
                </Button>
              </div>
            ))}
          </div>
        )
      }
    }
    else {
      return (null)
    }
  }

  return (
    <div className="AdvancedSearchButton">
      <div className="menu-container">
        <button onClick={onClickDropdown} className="menu-trigger">
          <span>Advanced Search</span>
        </button>
        <nav
          ref={dropdownRef}
          className={`menu ${isActive ? "active" : "inactive"}`}
        >
          <ul>
            <li>
              NORAD-ID
              <input type="text" name="Name" value={noradId} onChange={handleNoradIdChange} />
              Country
              <input type="text" name="Country" value={country} onChange={handleCountryChange} />
              Launch date
              <input type="text" name="Date" value={launchDate} onChange={handleLaunchDateChange} />
              <Button onClick={handleSearch}
                style={{
                  color: 'white',
                  borderRadius: 15,
                  backgroundColor: "#21b6ae",
                  padding: "12px 24px",
                  fontSize: "16px",
                  margin: "4px"
                }}>Search</Button>
            </li>
            <ShowSatellites />
          </ul>
        </nav>
      </div>
    </div>
  );
}
