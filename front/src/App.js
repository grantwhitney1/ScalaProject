import "./App.css";
import PhotoAlbum from "./components/PhotoAlbum";
import { Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Modalss from "./components/Modalss";

function App() {
  return (
    <>
      <Container>
        <PhotoAlbum />
      </Container>
    </>
  );
}

export default App;
