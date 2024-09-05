import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarProducto() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [producto, setProducto] = useState({
    id: "",
    nombre: "",
    precio: 0,
    imagen: "",
    lettering: false,
    scrapbooking: false,
    pedidos: []
  });

  useEffect(() => {
    const cargarProducto = async () => {
      try {
        const result = await axios.get(
          `http://localhost:8082/productos/api/ver/${id}`
        );
        setProducto(result.data);
      } catch (error) {
        console.error("Error al cargar el producto:", error);
      }
    };

    cargarProducto();
  }, [id]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setProducto((prevProducto) => ({ ...prevProducto, [name]: value }));
  };

  const onCheckboxChange = (e) => {
    const { name, checked } = e.target;
    setProducto((prevProducto) => ({ ...prevProducto, [name]: checked }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(
        `http://localhost:8082/productos/api/editar/${id}`,
        producto
      );
      navigate("/productos");
    } catch (error) {
      console.error("Error al actualizar el producto:", error);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Editar Producto</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label htmlFor="nombre" className="form-label">
                Nombre
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Introduce el nombre del producto"
                name="nombre"
                value={producto.nombre}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="precio" className="form-label">
                Precio
              </label>
              <input
                type="number"
                step="0.01"
                className="form-control"
                placeholder="Introduce el precio"
                name="precio"
                value={producto.precio}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="imagen" className="form-label">
                Imagen
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="URL de la imagen"
                name="imagen"
                value={producto.imagen}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="lettering" className="form-label">
                Lettering
              </label>
              <input
                type="checkbox"
                className="form-check-input"
                name="lettering"
                checked={producto.lettering}
                onChange={onCheckboxChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="scrapbooking" className="form-label">
                Scrapbooking
              </label>
              <input
                type="checkbox"
                className="form-check-input"
                name="scrapbooking"
                checked={producto.scrapbooking}
                onChange={onCheckboxChange}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="pedidos" className="form-label">
                Pedidos
              </label>
              <div>
                {producto.pedidos.map((pedido, index) => (
                  <div key={index}>
                    {pedido.descripcion} - {pedido.cantidad} unidades
                  </div>
                ))}
              </div>
            </div>

            <button type="submit" className="btn btn-outline-primary">
              Enviar
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/productos">
              Cancelar
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
