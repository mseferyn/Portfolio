import React, { Component } from 'react';
import { useRef } from 'react';
import { useState } from 'react';
import '../App';
import { useDetectOutsideClick } from "./useDetectOutsideClick";
import RegistrationDialog from "./RegistrationDialog";
import ChangePasswordDialog from "./ChangePasswordDialog"
import axios from 'axios'
import Button from '@mui/material/Button';


export default function ManageAccount() {
  const dropdownRef = useRef(null);
  const [isActive, setIsActive] = useDetectOutsideClick(dropdownRef, false);
  const [login, setLogin] = useState("")
  const [password, setPassword] = useState("")
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const onClick = () => setIsActive(!isActive);
  const [open, setOpen] = React.useState(false);
  const [correctLogin, setCorrectLogin] = useState(true);

  const handleClose = () => {
    window.location.reload(false);
    setOpen(false);
  };

  function handleLoginChange(event) {
    setLogin(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  async function handleLogin() {
    let request = "LOGIN:" + login + "&PASSWORD:" + password;
    let response = ''
    await axios.post('http://localhost:8080/user/login/' + request)
      .then(res => {
        console.log(JSON.stringify(res.data));
        response = JSON.stringify(res.data)
      }
      )
    // Ponizej warunek odpowiedzialny za logowanie i za info o bledzie, do uzaleznienia od zwrotnego info z serwera

    if (response === 'true') {
      console.log("ENTERED TRUE")
      setIsLoggedIn(true)
    }
    else if (response === 'false') {
      console.log("ENTERED FALSE")
      setCorrectLogin(false);
    }
    setPassword("")
  };


  if (!isLoggedIn) {
    return (
      <div className="ManageAccountButton">
        <div className="menu-container">
          <button onClick={onClick} className="menu-trigger">
            <span>Manage Account</span>
          </button>
          <nav
            ref={dropdownRef}
            className={`menu ${isActive ? "active" : "inactive"}`}
          >
            <ul>
              <li>
                Login
                <input type="text" name="login" value={login} onChange={handleLoginChange} />
                Password
                <input type="password" name="password" value={password} onChange={handlePasswordChange} />
                <div>
                  {correctLogin === false ? <div>Login or password was incorrect</div> : null}
                </div>
                <Button
                  onClick={handleLogin}
                  style={{
                    color: 'white',
                    borderRadius: 15,
                    backgroundColor: "#21b6ae",
                    padding: "12px 24px",
                    fontSize: "16px",
                    margin: "4px"
                  }}> Log in! </Button>
                <RegistrationDialog />
              </li>
            </ul>
          </nav>
        </div>
      </div>
    );
  }
  else {
    return (
      <div className="ManageAccountButton">
        <div className="menu-container">
          <button onClick={onClick} className="menu-trigger">
            <span>Welcome user!</span>
          </button>
          <nav
            ref={dropdownRef}
            className={`menu ${isActive ? "active" : "inactive"}`}
          >
            <ul>
              <li>
                Current user:
                <div>{login}</div>
                <ChangePasswordDialog />
              </li>
            </ul>
          </nav>
        </div>
      </div>
    );
  }
}