import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function ListaProducto() {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    try {
      const result = await axios.get(
        "http://localhost:8082/productos/api/productos"
      );
      setProductos(result.data);
    } catch (error) {
      console.error("Error al cargar productos:", error);
    }
  };

  const borrarProducto = async (id) => {
    await axios.delete(`http://localhost:8082/productos/api/borrar/${id}`);
    cargarProductos();
  };

  return (
    <div className="container">
      <div className="py-4">
        <Link className="btn btn-primary mx-2 my-3" to="/addProducto">
          Añadir Producto
        </Link>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col"> </th>
              <th scope="col">Nombre</th>
              <th scope="col">Imagen</th>
              <th scope="col">Precio</th>
              <th scope="col">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {productos.map((producto, index) => (
              <tr key={producto.id}>
                <th scope="row">
                  <Link className="btn btn-primary" to={`/ver/${producto.id}`}>
                    {index + 1}
                  </Link>
                </th>
                <td>{producto.nombre}</td>
                <td>
                  <img
                    src={producto.imagen}
                    alt={producto.nombre}
                    style={{ width: "100px", height: "100px" }}
                  />
                </td>
                <td>{producto.precio.toFixed(2)} €</td>
                <td>
                  
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editarProducto/${producto.id}`}
                  >
                    Editar
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => borrarProducto(producto.id)}
                  >
                    Borrar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
