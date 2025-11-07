export default function Receipt({ receipt, onClose }) {
  if (!receipt) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <h3>Order Complete 🎉</h3>
        <p>Total: ${receipt.total.toFixed(2)}</p>
        <p>Date: {new Date(receipt.timestamp).toLocaleString()}</p>
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
}
