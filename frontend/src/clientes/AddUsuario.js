import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddUsuario() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: "",
    email: "",
  });
  const { username, password, email } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };
  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8082/clientes/api/addCliente", user);
    navigate("/");
  };
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Registro de Usuario</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Username" className="form-label">
                username
              </label>
              <input
                type={"Text"}
                className="form-control"
                placeholder="Intruduce tu username"
                name="username"
                value={username}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                contraseña
              </label>
              <input
                type={"Text"}
                className="form-control"
                placeholder="Intruduce tu contraseña"
                name="password"
                value={password}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                email
              </label>
              <input
                type={"Text"}
                className="form-control"
                placeholder="Intruduce tu email"
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
              ></input>
            </div>

            <button type="submit" className="btn btn-outline-primary">
              enviar
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              cancelar
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
