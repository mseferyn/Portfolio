import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import axios from 'axios'

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [email, setEmail] = React.useState("");
  const [name, setName] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [ns, setNs] = React.useState("");
  const [ew, setEw] = React.useState("");


  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    window.location.reload(false);
    setOpen(false);
  };

  function handleSetEmail(event) {
    setEmail(event.target.value);
  }

  function handleSetName(event) {
    setName(event.target.value);
  }

  function handleSetPassword(event) {
    setPassword(event.target.value);
  }

  function handleSetNs(event) {
    setNs(event.target.value);
  }

  function handleSetEw(event) {
    setEw(event.target.value);
  }

  const handleSubmit = () => {
    let request = "EMAIL:" + email + "&NAME:" + name + "&PASSWORD:" + password + "&NS:" + ns + "&EW:" + ew;
    axios.post('http://localhost:8080/user/add/' + request)
      .then(res => {
        console.log(res);
      }
      )
    setOpen(false);
    window.location.reload(false);
  }

  return (
    <div>
      <Button onClick={handleClickOpen}
        style={{
          color: 'white',
          borderRadius: 15,
          backgroundColor: "#21b6ae",
          padding: "12px 24px",
          fontSize: "11px",
          margin: "4px"
        }}>
        Register!
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Create new account</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please enter your account data
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Email Address"
            type="email"
            fullWidth
            value={email}
            onChange={handleSetEmail}
            variant="standard"
            error={!(email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) }
            helperText={!(email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) ? 'Wrong email!' : ' '}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Account name"
            type="text"
            fullWidth
            value={name}
            onChange={handleSetName}
            variant="standard"
            error={(name === "")}
            helperText={name === "" ? 'Empty field!' : ' '}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Password"
            type="password"
            fullWidth
            value={password}
            onChange={handleSetPassword}
            variant="standard"
            error={(password === "")}
            helperText={email === "" ? 'Empty field!' : ' '}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="N-S coordinates"
            type="text"
            fullWidth
            value={ns}
            onChange={handleSetNs}
            variant="standard"
            error={!(ns.match(/^(-?[1-8]?\d(?:\.\d{1,18})?|90(?:\.0{1,18})?)$/))}
            helperText={!(ns.match(/^(-?[1-8]?\d(?:\.\d{1,18})?|90(?:\.0{1,18})?)$/)) ? 'Coordinates cannot be empty and need to contain numbers only!' : ' '}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="E-W coordinates"
            type="text"
            fullWidth
            value={ew}
            onChange={handleSetEw}
            variant="standard"
            error={!(ew.match(/^(-?(?:1[0-7]|[1-9])?\d(?:\.\d{1,18})?|180(?:\.0{1,18})?)$/))}
            helperText={!(ew.match(/^(-?(?:1[0-7]|[1-9])?\d(?:\.\d{1,18})?|180(?:\.0{1,18})?)$/)) ? 'Coordinates cannot be empty and need to contain numbers only!' : ' '}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit}
          disabled={(!(ew.match(/^(-?(?:1[0-7]|[1-9])?\d(?:\.\d{1,18})?|180(?:\.0{1,18})?)$/))) || (!(ns.match(/^(-?[1-8]?\d(?:\.\d{1,18})?|90(?:\.0{1,18})?)$/))) || (!(email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) || (password === '') || (name === '')) 
          }
          >Register!</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}