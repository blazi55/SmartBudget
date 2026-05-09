import type { FC } from "react";

type Props = {
  current: number;
  goal: number;
};

export const IncomeGoal: FC<Props> = ({ current, goal }) => {
  const percent = goal > 0 ? (current / goal) * 100 : 0;
  const safePercent = Math.min(percent, 100);
  const isCompleted = percent >= 100;

  return (
    <div className="card">

      {/* HEADER */}
      <div className="card-header">
        <p className="card-title">Income Goal</p>
      </div>

      {/* BODY */}
      <div className="card-body progress">

        {/* TOP ROW */}
        <div className="row">
          <span
            style={{
              color: isCompleted ? "var(--green)" : "var(--purple)",
              fontWeight: 600,
            }}
          >
            {Math.round(percent)}%
          </span>

          <span className="muted">
            ${current} / ${goal}
          </span>
        </div>

        {/* PROGRESS */}
        <div className="progress-bg">
          <div
            className="progress-bar"
            style={{
              width: `${safePercent}%`,
              background: isCompleted
                ? "linear-gradient(90deg,#22c55e,#16a34a)"
                : undefined,
            }}
          />
        </div>

        {/* BOTTOM INFO */}
        <div className="muted" style={{ fontSize: 12 }}>
          {isCompleted
            ? "Goal reached 🎉"
            : `Remaining: $${goal - current}`}
        </div>

      </div>
    </div>
  );
};