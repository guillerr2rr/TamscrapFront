import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./ListaOferta.css";

export default function ListaOferta() {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    const result = await axios.get(
      "http://localhost:8082/productos/api/ofertas"
    );
    setProductos(result.data);
  };

  const calcularPrecioOriginal = (precioConDescuento, descuento) => {
    return (precioConDescuento / (1 - descuento / 100)).toFixed(2);
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col"> </th>
              <th scope="col">Nombre</th>
              <th scope="col">Imagen</th>
              <th scope="col">Precio</th>
              <th scope="col">Descuento</th>
            </tr>
          </thead>
          <tbody>
            {productos.map((producto, index) => (
              <tr key={producto.id}>
                <th scope="row">
                  <Link className="btn-custom" to={`/ver/${producto.id}`}>
                    {index + 1}
                  </Link>
                </th>
                <td>{producto.nombre}</td>
                <td>
                  <img
                    src={producto.imagen}
                    alt={producto.nombre}
                    className="product-image"
                  />
                </td>
                <td>
                  {producto.oferta && producto.descuento > 0 ? (
                    <div className="price-container">
                      <span className="original-price">
                        {calcularPrecioOriginal(producto.precio, producto.descuento)} €
                      </span>
                      <strong className="discounted-price">
                        {producto.precio.toFixed(2)} €
                      </strong>
                    </div>
                  ) : (
                    <strong>{producto.precio.toFixed(2)} €</strong>
                  )}
                </td>
                <td>{producto.oferta && producto.descuento > 0 ? `${producto.descuento}%` : "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
