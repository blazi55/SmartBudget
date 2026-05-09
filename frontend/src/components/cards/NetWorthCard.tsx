

export const NetWorthCard = ({ amount, change }: any) => (
  <div className="card" style={{
    background: "linear-gradient(90deg,#f97316,#ec4899)"
  }}>
    <p>Total Net Worth</p>
    <h2>${amount}</h2>
    <p>+ ${change} this month</p>
  </div>
);