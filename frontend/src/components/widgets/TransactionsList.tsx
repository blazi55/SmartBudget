import type { FC } from "react";
import type { TransactionDto } from "../../services/types/TransactionDto";

type Props = {
  data: TransactionDto[];
};

export const TransactionsList: FC<Props> = ({ data }) => {

  const formatDate = (date: string) => {
    return new Date(date).toLocaleDateString();
  };

  return (
    <div className="card">

      <div className="card-body list">

        {data.length === 0 && (
          <p className="muted">No transactions</p>
        )}

        {data.map((t, i) => (
          <div className="row" key={`${t.date}-${i}`}>

            {/* LEFT */}
            <div>
              <p style={{ color: t.categoryDto?.color }}>
                {t.categoryDto?.name}
              </p>

              <p className="muted">
                {t.description || "No description"}
              </p>
            </div>

            {/* RIGHT */}
            <div style={{ textAlign: "right" }}>
              <p
                style={{
                  color:
                    t.type === "INCOME"
                      ? "var(--green)"
                      : "var(--text)",
                  fontWeight: 600,
                }}
              >
                {t.type === "INCOME" ? "+" : "-"}$
                {Math.abs(Number(t.amount)).toFixed(2)}
              </p>

              <p className="muted" style={{ fontSize: 12 }}>
                {formatDate(t.date)}
              </p>
            </div>

          </div>
        ))}

      </div>
    </div>
  );
};