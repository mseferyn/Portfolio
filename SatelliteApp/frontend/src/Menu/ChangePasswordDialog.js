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
  const [oldPassword, setOldPassword] = React.useState("");
  const [newPassword, setNewPassword] = React.useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    window.location.reload(false);
    setOpen(false);
  };

  function handleOldPasswordChange(event) {
    setOldPassword(event.target.value);
  }

  function handleNewPasswordChange(event) {
    setNewPassword(event.target.value);
  }

  const handleSubmit = () => {
    let request = "PASSWORD:" + oldPassword + ":" + newPassword;
    axios.post('http://localhost:8080/user/edit/' + request)
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
            fontSize: "16px",
            margin: "4px"
        }}>
        Change password
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Password Change</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Current password"
            type="password"
            fullWidth
            value={oldPassword}
            onChange={handleOldPasswordChange}
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="New password"
            type="password"
            fullWidth
            value={newPassword}
            onChange={handleNewPasswordChange}
            variant="standard"
          />
          <passwordCorrect />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit}>Change password</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}