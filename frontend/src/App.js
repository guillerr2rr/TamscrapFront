import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import ListaClientes from "./clientes/ListaCliente";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import EditarUsuario from "./clientes/EditarUsuario";
import AddUsuario from "./clientes/AddUsuario";
import VerUsuario from "./clientes/VerUsuario";

import ListaProducto from "./productos/ListaProducto";
import VerProducto from "./productos/VerProducto";
import AddProducto from "./productos/AddProducto";
import EditarProducto from "./productos/EditarProducto";
function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/clientes" element={<ListaClientes />} />
          <Route exact path="/addUsuario" element={<AddUsuario />} />
          <Route exact path="/editarUsuario/:id" element={<EditarUsuario />} />
          <Route exact path="/verCliente/:id" element={<VerUsuario />} />
          <Route exact path="/productos" element={<ListaProducto />} />
          <Route exact path="/ver/:id" element={<VerProducto />} />
          <Route exact path="/editarProducto/:id" element={<EditarProducto />} />
          <Route exact path="/addProducto" element={<AddProducto />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
