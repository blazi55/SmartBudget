import type { FC } from "react";

type Props = {
  data: {
    label: string;
    value: number;
  }[];
};

export const PetExpenses: FC<Props> = ({ data }) => {

  const formatCurrency = (value: number) =>
    `$${value.toFixed(2)}`;

  return (
    <div className="card">

      {/* HEADER */}
      <div className="card-header">
        <p className="card-title">Expenses for Pets</p>
      </div>

      {/* BODY */}
      <div className="card-body list">

        {data.length === 0 && (
          <p className="muted">No pet expenses</p>
        )}

        {data.map((item, i) => (
          <div
            key={`${item.label}-${i}`}
            className="pet-row"
          >
            <div className="row">

              {/* LEFT */}
              <span>
                {item.label || "Other"}
              </span>

              {/* RIGHT */}
              <span className="muted">
                {formatCurrency(Math.abs(item.value))}
              </span>

            </div>
          </div>
        ))}

      </div>
    </div>
  );
};