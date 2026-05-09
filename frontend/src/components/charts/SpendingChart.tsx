import type { FC } from "react";
import {
  LineChart,
  Line,
  ResponsiveContainer,
  Tooltip,
  CartesianGrid,
  YAxis,
} from "recharts";

type Props = {
  data: { value: number }[];
};

export const SpendingChart: FC<Props> = ({ data }) => {
  return (
    <ResponsiveContainer width="100%" height={240}>
      <LineChart data={data}>

        {/* GRADIENT */}
        <defs>
          <linearGradient id="lineGradient" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stopColor="#a855f7" stopOpacity={0.9} />
            <stop offset="100%" stopColor="#a855f7" stopOpacity={0.1} />
          </linearGradient>
        </defs>

        {/* GRID */}
        <CartesianGrid
          stroke="rgba(255,255,255,0.04)"
          vertical={false}
        />

        {/* AXIS */}
        <YAxis
          stroke="rgba(255,255,255,0.2)"
          fontSize={11}
          tickLine={false}
          axisLine={false}
        />

        {/* TOOLTIP */}
        <Tooltip
          contentStyle={{
            background: "#0f172a",
            border: "1px solid rgba(255,255,255,0.1)",
            borderRadius: "10px",
            fontSize: "12px",
          }}
          labelStyle={{ color: "#94a3b8" }}
          cursor={{ stroke: "rgba(255,255,255,0.1)" }}
        />

        {/* LINE */}
        <Line
          type="monotone"
          dataKey="value"
          stroke="url(#lineGradient)"
          strokeWidth={3}
          dot={false}
          activeDot={{ r: 4 }}
        />

      </LineChart>
    </ResponsiveContainer>
  );
};