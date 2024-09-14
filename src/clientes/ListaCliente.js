import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Home() {
  const [users, setUsers] = useState([]);
  
  useEffect(() => {
    cargarUsuarios();
  }, []);

  const cargarUsuarios = async () => {
    try {
      const result = await axios.get(
        "http://localhost:8082/clientes/api/clientes"
      );
      setUsers(result.data);
    } catch (error) {
      console.error("Error al cargar usuarios:", error);
    }
  };
  const borrarUsuario = async (id) => {
    await axios.delete(`http://localhost:8082/clientes/api/borrar/${id}`);
    cargarUsuarios();
  };
  return (
    <div className="container">
      <div className="py-4">
        <Link className="btn btn-primary mx-2 my-3" to="/addUsuario">
          Añadir Usuario
        </Link>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col"> </th>
              <th scope="col">username</th>
              <th scope="col">email</th>
              <th scope="col">acciones</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <th scope="row">
                  <Link className="btn-custom" to={`/verCliente/${user.id}`}>




                  
                    {index + 1}
                  </Link>
                </th>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editarUsuario/${user.id}`}
                  >
                    Editar
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => borrarUsuario(user.id)}
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
