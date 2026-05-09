import type { FC } from "react";

export const TopBar: FC = () => {
  return (
    <div className="topbar">

      {/* LEFT */}
      <div className="topbar-left">
        <div className="tab active">Smart Budget</div>
      </div>

      {/* CENTER */}
      <div className="topbar-date">
        Sunday, February 5, 2023
      </div>

      {/* RIGHT */}
      <div className="topbar-right">
        <div className="user-info">
          <p className="user-name">Simon K. Jimmy</p>
          <p className="user-role">Mortgage consultant</p>
        </div>

        <div className="avatar" />
      </div>

    </div>
  );
};