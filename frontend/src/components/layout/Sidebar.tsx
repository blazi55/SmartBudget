import type { FC } from "react";

export const Sidebar: FC = () => {
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun"];
  const active = "Mar";

  return (
    <div className="sidebar">

      <div className="logo">SB</div>

      <div className="menu">
        {months.map((m) => (
          <div
            key={m}
            className={`menu-item ${m === active ? "active" : ""}`}
          >
            {m}
          </div>
        ))}
      </div>

    </div>
  );
};