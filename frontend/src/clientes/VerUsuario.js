import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function VerUsuario() {
  const [user, setUser] = useState({
    username: "",
    password: "",
    email: "",
    authorities: [],
  });
  const { id } = useParams();

  useEffect(() => {
    cargarUsuario();
  }, []);

  const cargarUsuario = async () => {
    const result = await axios.get(
      `http://localhost:8082/clientes/api/ver/${id}`
    );
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Detalles de Usuario</h2>

          <div className="card">
            <div className="card-header">
              detalles de usuario id: {user.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Username:</b> {user.username}
                </li>
                <li className="list-group-item">
                  <b>Email:</b> {user.email}
                </li>
                <li className="list-group-item">
                  <b>Permisos:</b>
                   
                  <ul>
                    {user.authorities.length > 0 ? (
                      user.authorities.map((authority, index) => (
                        <li key={index}>{authority.authority}</li>
                      ))
                    ) : (
                      <li>No tiene permisos</li>
                    )}
                  </ul>
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/clientes"}>
            Regresar
          </Link>
        </div>
      </div>
    </div>
  );
}
