import type { FC } from "react";
import { Routes, Route } from "react-router-dom";
import { Dashboard } from "./pages/Dashboard";

const App: FC = () => {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
    </Routes>
  );
};

export default App;