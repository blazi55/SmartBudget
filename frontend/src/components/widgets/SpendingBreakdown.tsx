import type { FC } from "react";
import type { TransactionDto } from "../../services/types/TransactionDto";

type Props = {
  data: TransactionDto[];
};

export const SpendingBreakdown: FC<Props> = ({ data }) => {

  const grouped = data
    .filter(t => t.type === "EXPENSE")
    .reduce((acc: Record<string, number>, t) => {
      const name = t.categoryDto?.name || "Other";

      if (!acc[name]) acc[name] = 0;
      acc[name] += Math.abs(Number(t.amount));

      return acc;
    }, {});

  const result = Object.entries(grouped).map(([label, value]) => ({
    label,
    value,
  }));

  const total = result.reduce((sum, d) => sum + d.value, 0);

  return (
    <div className="card">

      {/* HEADER */}
      <div className="card-header">
        <p className="card-title">Spending Breakdown</p>
      </div>

      {/* BODY */}
      <div className="card-body breakdown-body">

        {result.length === 0 && (
          <p className="muted">No expenses</p>
        )}

        {result.map((item) => {
          const percent =
            total > 0
              ? Math.round((item.value / total) * 100)
              : 0;

          return (
            <div key={item.label} className="breakdown-item">

              {/* TOP */}
              <div className="row">
                <span>{item.label}</span>
                <span className="muted">{percent}%</span>
              </div>

              {/* BAR */}
              <div className="progress-bg">
                <div
                  className="progress-bar"
                  style={{ width: `${percent}%` }}
                />
              </div>

              {/* VALUE */}
              <div className="breakdown-value">
                ${item.value.toFixed(2)}
              </div>

            </div>
          );
        })}

      </div>
    </div>
  );
};