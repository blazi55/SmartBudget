import type { FC, ReactNode } from "react";

type Props = {
  title?: string;
  children: ReactNode;
  className?: string;
  rightSlot?: ReactNode;
};

export const Card: FC<Props> = ({ title, children, className = "", rightSlot }) => {
  return (
    <div
      className={`
        bg-[var(--card)]
        rounded-2xl
        p-5
        border border-[var(--border)]
        ${className}
      `}
    >
      {(title || rightSlot) && (
        <div className="flex justify-between items-center mb-4">
          <p className="text-sm text-[var(--text-muted)]">{title}</p>
          {rightSlot}
        </div>
      )}

      {children}
    </div>
  );
};