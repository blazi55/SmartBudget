import type { FC } from "react";

type Notification = {
  id?: string;
  title: string;
  amount?: number;
  time: string;
  type: "INCOME" | "EXPENSE";
};

type Props = {
  data: Notification[];
};

export const Notifications: FC<Props> = ({ data }) => {

  const formatAmount = (amount: number, type: string) => {
    return `${type === "INCOME" ? "+" : "-"}$${Math.abs(amount).toFixed(2)}`;
  };

  const formatTime = (time: string) => {
    const diff = (Date.now() - new Date(time).getTime()) / 1000;

    if (diff < 3600) return `${Math.floor(diff / 60)}m ago`;
    if (diff < 86400) return `${Math.floor(diff / 3600)}h ago`;
    return `${Math.floor(diff / 86400)}d ago`;
  };

  return (
    <div className="card">

      <div className="card-header">
        <p className="card-title">Notifications</p>
      </div>

      <div className="card-body list notifications-body">

        {data.length === 0 && (
          <p className="muted">No notifications</p>
        )}

        {data.map((n, i) => (
          <div
            key={n.id ?? i}
            className="notification-item"
          >

            {/* TITLE */}
            <p
              style={{
                color:
                  n.type === "INCOME"
                    ? "var(--green)"
                    : "var(--orange)",
                fontWeight: 600,
              }}
            >
              {n.title}
            </p>

            {/* AMOUNT */}
            {n.amount !== undefined && (
              <p
                style={{
                  color:
                    n.type === "INCOME"
                      ? "var(--green)"
                      : "var(--text)",
                  fontWeight: 600,
                }}
              >
                {formatAmount(n.amount, n.type)}
              </p>
            )}

            {/* TIME */}
            <p className="muted" style={{ fontSize: 12 }}>
              {formatTime(n.time)}
            </p>

          </div>
        ))}

      </div>
    </div>
  );
};