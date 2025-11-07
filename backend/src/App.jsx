import { useEffect, useState } from "react";
import axios from "axios";
import Product from "./Product.jsx";
import Cart from "./Cart.jsx";
import Checkout from "./Checkout.jsx";
import Receipt from "./Receipt.jsx";
import "./index.css";

const API_URL = import.meta.env.VITE_API_URL || "http://localhost:9095";

export default function App() {
  const [cart, setCart] = useState([]);
  const [showCheckout, setShowCheckout] = useState(false);
  const [receipt, setReceipt] = useState(null);

  // Fetch cart items from backend
  const fetchCart = async () => {
    try {
      const res = await axios.get(`${API_URL}/api/cart`);
      setCart(
        res.data.items.map((i) => ({
          id: i.id,
          productId: i.productId,
          name: i.name,
          price: i.price,
          qty: i.quantity || 1,
        })) || []
      );
    } catch (err) {
      console.error("Failed to fetch cart:", err);
    }
  };

  useEffect(() => {
    fetchCart();
  }, []);

  // Add product to cart
  const handleAddToCart = async (product) => {
    try {
      const existing = cart.find((item) => item.productId === product.id);

      if (existing) {
        // Prevent multiple adds, user should increase qty via input
        alert("Item already in cart. Use the quantity input to increase it.");
        return;
      }

      // Optimistically add new item with qty = 1
      const newItem = {
        id: Date.now(), // temporary id
        productId: product.id,
        name: product.name,
        price: product.price,
        qty: 1,
      };
      setCart([...cart, newItem]);

      // Send to backend
      await axios.post(`${API_URL}/api/cart`, {
        productId: product.id,
        qty: 1,
      });

      fetchCart(); // refresh cart from backend
    } catch (err) {
      console.error("Failed to add to cart:", err);
      fetchCart(); // rollback if failed
    }
  };

  // Remove item from cart
  const handleRemove = async (id) => {
    const updatedCart = cart.filter((item) => item.id !== id);
    setCart(updatedCart);

    try {
      await axios.delete(`${API_URL}/api/cart/${id}`);
    } catch (err) {
      console.error("Failed to remove item:", err);
      fetchCart(); // rollback
    }
  };

  // Update quantity
  const handleUpdateQty = async (id, qty) => {
    if (isNaN(qty) || qty < 1) return;

    const updatedCart = cart.map((item) =>
      item.id === id ? { ...item, qty } : item
    );
    setCart(updatedCart);

    try {
      const item = cart.find((c) => c.id === id);
      if (!item) return;

      await axios.post(`${API_URL}/api/cart`, {
        productId: item.productId,
        qty,
      });
    } catch (err) {
      console.error("Failed to update quantity:", err);
      fetchCart(); // rollback
    }
  };

  const handleCheckout = () => setShowCheckout(true);

  return (
    <div className="container">
      <h1>🛒 Vibe Commerce</h1>

      <Product onAddToCart={handleAddToCart} />

      <Cart
        cart={cart}
        onRemove={handleRemove}
        onUpdateQty={handleUpdateQty}
        onCheckout={handleCheckout}
      />

      {showCheckout && (
        <Checkout
          cart={cart}
          onReceipt={(r) => {
            setReceipt(r);
            setShowCheckout(false);
            fetchCart(); // refresh cart after checkout
          }}
        />
      )}

      <Receipt receipt={receipt} onClose={() => setReceipt(null)} />
    </div>
  );
}
