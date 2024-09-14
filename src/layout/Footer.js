import React from "react";
import { Link } from "react-router-dom";
import "./Footer.css";

export default function Footer() {
  return (
    <footer className="footer  text-light py-4">
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <h5 className="fw-bold">Tamscrapt</h5>
          </div>
          <div className="col-md-4">
            <h5 className="fw-bold">Enlaces Rápidos</h5>
            <ul className="list-unstyled">
              <li>
                <Link className="text-light" to="/productos">
                  Productos
                </Link>
              </li>
              <li>
                <Link className="text-light" to="/oferta">
                  Ofertas
                </Link>
              </li>
              <li>
                <Link className="text-light" to="/lettering">
                  Lettering
                </Link>
              </li>
              <li>
                <Link className="text-light" to="/scrapbooking">
                  Scrapbooking
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
}
