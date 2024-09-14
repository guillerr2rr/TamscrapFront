import { Link } from "react-router-dom";
import "./Home.css";

export default function Home() {
  return (
    <div className="home-container">
      <section className="hero-section">
        <div className="hero-content">
          <h1>Bienvenido a nuestra tienda</h1>
          <p>Descubre los mejores productos de Lettering y Scrapbooking</p>
          <Link to="/productos" className="btn btn-custom btn-lg">
            Ver Productos
          </Link>
        </div>
      </section>

      <section className="categorias-destacadas">
        <h2>Categorías Destacadas</h2>
        <div className="row">
          <div className="col-md-4">
            <Link to="/lettering" className="categoria-card">
              <div className="card shadow-sm">
                <img
                  src="https://m.media-amazon.com/images/I/61Wv7ihKCuL._AC_SL1001_.jpg"
                  className="card-img-top"
                  alt="Lettering"
                />
                <div className="card-body">
                  <h5 className="card-title">Lettering</h5>
                  <p className="card-text">
                    Todo lo que necesitas para lettering creativo.
                  </p>
                </div>
              </div>
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="/scrapbooking" className="categoria-card">
              <div className="card shadow-sm">
                <img
                  src="https://m.media-amazon.com/images/I/81oFkzA9pCL._AC_SL1500_.jpg"
                  className="card-img-top"
                  alt="Scrapbooking"
                />
                <div className="card-body">
                  <h5 className="card-title">Scrapbooking</h5>
                  <p className="card-text">
                    Materiales y kits para scrapbooking.
                  </p>
                </div>
              </div>
            </Link>
          </div>
          <div className="col-md-4">
            <Link to="/productos" className="categoria-card">
              <div className="card shadow-sm">
                <img
                  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkEjuMveu-4ukf1LWxnDhtihgqwDJk3Acj2w&s"
                  className="card-img-top"
                  alt="Papelería"
                />
                <div className="card-body">
                  <h5 className="card-title">Papelería</h5>
                  <p className="card-text">
                    Los mejores artículos de papelería.
                  </p>
                </div>
              </div>
            </Link>
          </div>
        </div>
      </section>

      <section className="testimonios-clientes">
        <h2>Lo que dicen nuestros clientes</h2>
        <div className="row">
          <div className="col-md-4">
            <blockquote className="blockquote text-center">
              <p className="mb-0">
                "¡Los mejores productos para lettering! Altamente recomendados."
              </p>
              <footer className="blockquote-footer">Ana Pérez</footer>
            </blockquote>
          </div>
          <div className="col-md-4">
            <blockquote className="blockquote text-center">
              <p className="mb-0">
                "Gran calidad y servicio. Mis pedidos llegaron rápido."
              </p>
              <footer className="blockquote-footer">Carlos García</footer>
            </blockquote>
          </div>
          <div className="col-md-4">
            <blockquote className="blockquote text-center">
              <p className="mb-0">
                "Materiales perfectos para mi proyecto de scrapbooking."
              </p>
              <footer className="blockquote-footer">Laura Fernández</footer>
            </blockquote>
          </div>
        </div>
      </section>

      <section className="seccion-oferta">
        <h2>Ofertas Especiales</h2>
        <p>No te pierdas nuestras promociones exclusivas.</p>
        <Link to="/oferta" className="btn btn-warning btn-lg">
          Ver Ofertas
        </Link>
      </section>
    </div>
  );
}
