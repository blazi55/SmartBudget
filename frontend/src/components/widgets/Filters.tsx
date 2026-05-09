import type { FC } from "react";

type Props = {
  onFilterChange: (value: string) => void;
};

export const Filters: FC<Props> = ({ onFilterChange }) => {
  return (
    <div className="filter-wrapper">
      <select
        onChange={(e) => onFilterChange(e.target.value)}
        className="filter-select"
      >
        <option value="">All Categories</option>
        <option value="Food">Food</option>
        <option value="Entertainment">Entertainment</option>
        <option value="Income">Income</option>
      </select>
    </div>
  );
};