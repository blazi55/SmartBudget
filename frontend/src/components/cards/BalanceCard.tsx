export const BalanceCard = ({ amount }: any) => (
  <div className="card">

    {/* HEADER */}
    <div className="card-header">
      <p className="card-title">Available Balance</p>
    </div>

    {/* BODY */}
    <div className="card-body">
      <p className="balance-value">${amount}</p>
    </div>

  </div>
);