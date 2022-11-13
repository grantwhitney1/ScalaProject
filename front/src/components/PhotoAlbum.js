import React, { useState, useEffect } from "react";
import Photos from "./Photos";
import { Container, Row, Col, Button } from "react-bootstrap";
import Modalss from "./Modalss";
import "./album.css";

const PhotoAlbum = () => {
  const [pic, setPic] = useState("");
  const [album, addAlbum] = useState([]);

  useEffect(() => {}, [album]);
  return (
    <>
      <Container>
        <h1>Photo Album</h1>
        <Modalss album={album} addAlbum={addAlbum} pic={pic} setPic={setPic} />
        <Row xs={1} md={3}>
          {album.map((source) => {
            return (
              <Col>
                <Photos
                  source={source}
                  album={album}
                  addAlbum={addAlbum}
                  pic={pic}
                  setPic={setPic}
                />
              </Col>
            );
          })}
        </Row>
      </Container>
    </>
  );
};

export default PhotoAlbum;
