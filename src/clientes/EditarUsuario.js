import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarUsuario() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [usuario, setUsuario] = useState({
    username: "",
    password: "",
    email: "",
    authorities: [],
  });

  useEffect(() => {
    const cargarUsuario = async () => {
      try {
        const result = await axios.get(
          `http://localhost:8082/clientes/api/ver/${id}`
        );
        setUsuario(result.data);
      } catch (error) {
        console.error("Error al cargar el usuario:", error);
      }
    };

    cargarUsuario();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setUsuario((prevUsuario) => ({ ...prevUsuario, [name]: value }));
  };

  const onAuthoritiesChange = (e) => {
    const selectedAuthorities = Array.from(
      e.target.selectedOptions,
      (option) => option.value
    );
    setUsuario((prevUsuario) => ({ ...prevUsuario, authorities: selectedAuthorities }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
 
      await axios.put(`http://localhost:8082/clientes/api/editar/${id}`, usuario);
      navigate("/clientes");
    
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Editar Usuario</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">
                Usuarioname
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Introduce tu username"
                name="username"
                value={usuario.username}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Contraseña
              </label>
              <input
                type="password"
                className="form-control"
                placeholder="Introduce tu contraseña"
                name="password"
                value={usuario.password}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                placeholder="Introduce tu email"
                name="email"
                value={usuario.email}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="authorities" className="form-label">
                Authorities
              </label>
              <select
                multiple
                className="form-control"
                name="authorities"
                value={usuario.authorities}
                onChange={onAuthoritiesChange}
              >
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
                <option value="ANONYMOUS">ANONYMOUS</option>
              </select>
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
