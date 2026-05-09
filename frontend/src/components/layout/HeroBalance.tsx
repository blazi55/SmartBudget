import type { FC } from "react";

export const HeroBalance: FC = () => {
  return (
    <div className="hero-card">

      <div className="hero-header">
        <p className="hero-subtitle">
          Personal Finance Tracker
        </p>
        <div className="hero-badge">LIVE</div>
      </div>

      <p className="hero-title">
        Available Balance
      </p>

      <p className="hero-value">
        $14,822
      </p>

    </div>
  );
};