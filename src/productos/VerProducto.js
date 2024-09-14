import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function VerProducto() {
  const [producto, setProducto] = useState({
    id: "",
    nombre: "",
    precio: "",
    imagen: "",
    lettering: "",
    scrapbooking: "",
    pedidos: [],
  });
  const { id } = useParams();

  useEffect(() => {
    cargarProducto();
  }, []);

  const cargarProducto = async () => {
    const result = await axios.get(
      `http://localhost:8082/productos/api/ver/${id}`
    );
    setProducto(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Detalles de Producto</h2>

          <div className="card">
            <div className="card-header">
              detalles de producto id: {producto.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Nombre:</b> {producto.nombre}
                </li>
                <li className="list-group-item">
                  <b>Precio:</b> {producto.precio}
                </li>

                <li className="list-group-item">
                  <b>Scrapbooking:</b> {producto.scrapbooking ? "Sí" : "No"}
                </li>
                <li className="list-group-item">
                  <b>Lettering:</b> {producto.lettering ? "Sí" : "No"}
                </li>

                <li className="list-group-item">
                  <b>Pedidos:</b>

                  <ul>
                    {producto.pedidos.length > 0 ? (
                      producto.pedidos.map((pedido, index) => (
                        <li key={index}>{pedido.pedido}</li>
                      ))
                    ) : (
                      <li>No esta en ningun pedido</li>
                    )}
                  </ul>
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/productos"}>
            Regresar
          </Link>
        </div>
      </div>
    </div>
  );
}
