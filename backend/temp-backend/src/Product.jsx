import { useEffect, useState } from "react";
import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL || "http://localhost:9095";

export default function Product({ onAddToCart }) {
  const [products, setProducts] = useState([]);

  const getProductImage = (p) => {
    if (p.img) return p.img;
    switch (p.name) {
      case "Wireless Headphones":
        return "https://upload.wikimedia.org/wikipedia/commons/6/6b/Wireless_Headphones.jpg";
      case "Smart Watch":
        return "https://upload.wikimedia.org/wikipedia/commons/3/3b/Smartwatch.jpg";
      case "Bluetooth Speaker":
        return "https://upload.wikimedia.org/wikipedia/commons/7/7a/Bluetooth_speaker.jpg";
      case "USB-C Cable":
        return "https://upload.wikimedia.org/wikipedia/commons/4/42/Usb-c-cable.jpg";
      default:
        return "https://upload.wikimedia.org/wikipedia/commons/2/21/Portable_Charger.jpg";
    }
  };

  useEffect(() => {
    axios
      .get(`${API_URL}/api/products`)
      .then((res) => setProducts(res.data))
      .catch((err) => console.error("Failed to fetch products:", err));
  }, []);

  return (
    <div className="products-grid">
      {products.map((p) => (
        <div key={p.id} className="product-card">
          <img src={getProductImage(p)} alt={p.name} />
          <h3>{p.name}</h3>
          <p>${Number(p.price).toFixed(2)}</p>
          <button onClick={() => onAddToCart(p)}>Add to Cart</button>
        </div>
      ))}
    </div>
  );
}
