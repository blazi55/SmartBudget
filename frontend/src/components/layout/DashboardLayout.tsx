import { Sidebar } from "lucide-react";
import type { FC } from "react";
import type { Props } from "recharts/types/shape/Dot";
import { TopBar } from "./TopBar";

export const DashboardLayout: FC<Props> = ({ children }) => {
  return (
    <div className="app">

      <Sidebar />

      <div className="main">

        <TopBar />

        {children}

      </div>
    </div>
  );
};