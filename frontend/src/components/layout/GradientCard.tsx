import type { FC } from "react";

export const GradientCard: FC = () => {
  return (
    <div className="gradient-card">

      <p className="gradient-label">Total Net Worth</p>

      <p className="gradient-value">
        $278,378
      </p>

      <p className="gradient-sub">
        +2.5% this month
      </p>

    </div>
  );
};