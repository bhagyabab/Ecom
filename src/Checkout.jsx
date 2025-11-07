import { useState } from "react";

export default function Checkout({ cart, onReceipt }) {
  const [form, setForm] = useState({ name: "", email: "" });

  const handleSubmit = (e) => {
    e.preventDefault();
    const total = cart.reduce((s, i) => s + i.price * i.qty, 0);
    onReceipt({
      customer: form,
      items: cart,
      total,
      timestamp: new Date().toISOString(),
    });
  };

  return (
    <form className="checkout-form" onSubmit={handleSubmit}>
      <h2>Checkout</h2>
      <input
        placeholder="Name"
        value={form.name}
        onChange={(e) => setForm({ ...form, name: e.target.value })}
        required
      />
      <input
        type="email"
        placeholder="Email"
        value={form.email}
        onChange={(e) => setForm({ ...form, email: e.target.value })}
        required
      />
      <button type="submit">Submit</button>
    </form>
  );
}
