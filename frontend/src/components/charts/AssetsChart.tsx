import type { FC } from "react";
import { PieChart, Pie, Cell, ResponsiveContainer } from "recharts";

type DataItem = {
  name: string;
  value: number;
};

type Props = {
  data: DataItem[];
};

const COLORS = ["#6366f1", "#22c55e", "#f97316", "#f43f5e"];

export const AssetsChart: FC<Props> = ({ data }) => {
  const total = data.reduce((acc, d) => acc + d.value, 0);

  return (
    <div className="card">

      {/* HEADER */}
      <div className="card-header">
        <p className="card-title">Assets</p>
      </div>

      {/* BODY */}
      <div className="card-body assets-body">

        {/* CHART */}
        <div className="assets-chart">
          <ResponsiveContainer>
            <PieChart>
              <Pie
                data={data}
                innerRadius={55}
                outerRadius={75}
                paddingAngle={2}
                dataKey="value"
              >
                {data.map((_, i) => (
                  <Cell key={i} fill={COLORS[i % COLORS.length]} />
                ))}
              </Pie>
            </PieChart>
          </ResponsiveContainer>
        </div>

        {/* LEGEND */}
        <div className="assets-legend">
          {data.map((item, i) => {
            const percent = Math.round((item.value / total) * 100);

            return (
              <div className="row" key={item.name}>
                <div className="assets-label">
                  <span
                    className="dot"
                    style={{ background: COLORS[i % COLORS.length] }}
                  />
                  <span>{item.name}</span>
                </div>

                <span className="muted">{percent}%</span>
              </div>
            );
          })}
        </div>

      </div>
    </div>
  );
};