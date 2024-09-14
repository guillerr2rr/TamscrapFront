import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddProducto() {
  let navigate = useNavigate();

  const [producto, setProducto] = useState({
    nombre: "",
    precio: "",
    imagen: "",
    lettering: false,
    scrapbooking: false
  });
  const { nombre, precio, imagen, lettering, scrapbooking } = producto;

  const onInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProducto({
      ...producto,
      [name]: type === 'checkbox' ? checked : value
    });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8082/productos/api/addProducto", producto);
    navigate("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Agregar Producto</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="nombre" className="form-label">
                Nombre
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Introduce el nombre del producto"
                name="nombre"
                value={nombre}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="precio" className="form-label">
                Precio
              </label>
              <input
                type="number"
                className="form-control"
                placeholder="Introduce el precio del producto"
                name="precio"
                value={precio}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="imagen" className="form-label">
                Imagen URL
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Introduce la URL de la imagen"
                name="imagen"
                value={imagen}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <div className="form-check">
                <input
                  type="checkbox"
                  className="form-check-input"
                  id="lettering"
                  name="lettering"
                  checked={lettering}
                  onChange={(e) => onInputChange(e)}
                />
                <label className="form-check-label" htmlFor="lettering">
                  Lettering
                </label>
              </div>
              <div className="form-check">
                <input
                  type="checkbox"
                  className="form-check-input"
                  id="scrapbooking"
                  name="scrapbooking"
                  checked={scrapbooking}
                  onChange={(e) => onInputChange(e)}
                />
                <label className="form-check-label" htmlFor="scrapbooking">
                  Scrapbooking
                </label>
              </div>
            </div>

            <button type="submit" className="btn btn-outline-primary">
              Enviar
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancelar
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
