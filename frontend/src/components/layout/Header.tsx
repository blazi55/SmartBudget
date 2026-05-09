import type { FC } from "react";

export const Header: FC = () => {
  return (
    <div className="header">

      {/* LEFT */}
      <div>
        <h1 className="header-title">Dashboard</h1>
        <p className="header-subtitle">Welcome back 👋</p>
      </div>

      {/* RIGHT */}
      <div className="header-right">

        <div className="search">
          <input placeholder="Search..." />
        </div>

        <div className="avatar">B</div>

      </div>
    </div>
  );
};