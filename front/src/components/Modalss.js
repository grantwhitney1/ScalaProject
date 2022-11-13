import React, { useState } from "react";
import { Button, Modal, Form, Image, Card } from "react-bootstrap";
import FileBase from "react-file-base64";

function Modalss({ pic, setPic, addAlbum, album }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSubmit = (e) => {
    e.preventDefault();
    setShow(false);
    addAlbum((album) => [...album, pic]);
    setPic("");
  };

  return (
    <>
      <Button variant="primary" className="mb-3 mt-3" onClick={handleShow}>
        + Add Picture
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add a new photo!</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Source link</Form.Label>
              {/* <Form.Control value={pic} /> */}
              <div>
                <FileBase
                  type="file"
                  multiple={false}
                  onDone={({ base64 }) => setPic(base64)}
                />
              </div>
              <Form.Group className="mb-3">
                <Form.Label>Message</Form.Label>
                <Form.Control type="text" placeholder=".." />
              </Form.Group>
            </Form.Group>
            <Form.Group>
              <Form.Label>Preview</Form.Label>
              <Card>
                <Card.Img variant="top" src={pic} />
              </Card>
            </Form.Group>
            <Button variant="primary" type="submit" onClick={handleSubmit}>
              Submit
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Modalss;
