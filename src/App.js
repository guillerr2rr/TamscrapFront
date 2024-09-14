import React from "react";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./layout/Navbar";
import Footer from "./layout/Footer";
import Home from "./home/Home";

import ListaClientes from "./clientes/ListaCliente";
import AddUsuario from "./clientes/AddUsuario";
import EditarUsuario from "./clientes/EditarUsuario";
import VerUsuario from "./clientes/VerUsuario";

import ListaProducto from "./productos/ListaProducto";
import ListaScrapbooking from "./productos/ListarScrapbooking";
import ListaLettering from "./productos/ListaLettering";
import ListaOferta from "./productos/ListaOferta";
import VerProducto from "./productos/VerProducto";
import AddProducto from "./productos/AddProducto";
import EditarProducto from "./productos/EditarProducto";
 
function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/clientes" element={<ListaClientes />} />
          <Route path="/addUsuario" element={<AddUsuario />} />
          <Route path="/editarUsuario/:id" element={<EditarUsuario />} />
          <Route path="/verCliente/:id" element={<VerUsuario />} />

          <Route path="/productos" element={<ListaProducto />} />
          <Route path="/scrapbooking" element={<ListaScrapbooking />} />
          <Route path="/lettering" element={<ListaLettering />} />
          <Route path="/oferta" element={<ListaOferta />} />
          <Route path="/ver/:id" element={<VerProducto />} />
          <Route path="/editarProducto/:id" element={<EditarProducto />} />
          <Route path="/addProducto" element={<AddProducto />} />
  
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
