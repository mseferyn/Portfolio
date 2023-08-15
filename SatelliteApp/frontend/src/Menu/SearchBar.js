import React, { Component } from 'react';
import { useState } from 'react';
import '../App';
import axios from 'axios';
import Button from '@mui/material/Button';

class SearchBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: '', gotResponse: false, responseId: '', responseName: '', responseLat: '', responseLang: '', launchDate: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ value: event.target.value });
  }

  handleSearchAnother() {
    window.location.reload(false);
  }

  handleSubscribe(id) {
    console.log ("Subscribe: " + id)
    let request = id;
    axios.post('http://localhost:8080/user/favs/' + request)
      .then(res => {
        console.log(res);
      }
      )
  }

  async handleSubmit(event) {
    let request = this.state.value;
    await axios.post('http://localhost:8080/sat/noradid/' + request)
      .catch(function (error) {
        console.log("ERROR")
      })
      .then(res => {
        //zmienic ifa
        console.log(res.data.length)
        if (res !== undefined) {
          {
            if (res.data.length !== 0)
            {
              this.setState({ gotResponse: true })
              console.log("Name:" + res.data[0].objectName)
              this.setState({responseId: res.data[0].noradcatId, responseName: res.data[0].objectName, responseLat: res.data[0].latitude, responseLang: res.data[0].longitude, launchDate:  res.data[0].launchDate})
    
            }
            else {
              this.setState({value: "No such satelite!"})
            }
          }
        }
        else if (res === undefined) {
          this.setState({value: "No such satelite!"})
        }
      }
      )
    if (this.state.gotResponse === true) {
      console.log("WORKS")
    }
    else {
      console.log("NOT")
    }
  }

    render() {
      if (this.state.gotResponse)
      {
      return (
        <React.Fragment>
          <div className="info-bar" >
            We found your satellite!
            <div>
              Name: {this.state.responseName}
            </div>
            <div>
              Norad ID: {this.state.responseId}
            </div>
            <div>
              Launch Date: {this.state.launchDate}
            </div>
            <Button
                              onClick={() => this.handleSubscribe(this.state.responseId)}
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
          <button type="submit" onClick={this.handleSearchAnother} className="searchAnotherButton">
            Search another
          </button>
        </React.Fragment>
      )
    }
    else {
      return (
        <React.Fragment>
          <div className="searchBarLabel" >
          </div>
          <input className="searchBarInput" value={this.state.value} onChange={this.handleChange} type="text" name="name">
          </input>
          <button type="submit" onClick={this.handleSubmit} className="searchBarButton">
            Search
          </button>
        </React.Fragment>
      )
    }
  }
}

export default SearchBar;