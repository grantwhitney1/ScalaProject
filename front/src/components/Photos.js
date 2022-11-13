import React from "react";
import { Card, Container } from "react-bootstrap";

const Photos = ({ source }) => {
  return (
    <>
      <Card>
        <Card.Img variant="top" src={source} />
      </Card>
      <br />
    </>
  );
};

export default Photos;
