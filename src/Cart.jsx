export default function Cart({ cart, onRemove, onUpdateQty, onCheckout }) {
  const total = cart.reduce((sum, { price, qty }) => sum + price * qty, 0);

  return (
    <div className="cart">
      <h2>Your Cart</h2>

      {cart.length === 0 ? (
        <p>Cart is empty.</p>
      ) : (
        <>
          {cart.map((item) => (
            <div key={item.id} className="cart-item">
              <span>{item.name}</span>

              <input
                type="number"
                min="1"
                value={item.qty}
                onChange={(e) => {
                  const newQty = parseInt(e.target.value);
                  if (!isNaN(newQty) && newQty > 0) {
                    onUpdateQty(item.id, newQty);
                  }
                }}
              />

              <span>${(item.price * item.qty).toFixed(2)}</span>
              <button onClick={() => onRemove(item.id)}>Remove</button>
            </div>
          ))}

          <h3>Total: ${total.toFixed(2)}</h3>
          <button onClick={onCheckout} disabled={cart.length === 0}>
            Checkout
          </button>
        </>
      )}
    </div>
  );
}
