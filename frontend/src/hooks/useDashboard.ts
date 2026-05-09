import { useEffect, useState } from "react";
import {
  getTransactions,
  getCategories,
  getBudgets,
} from "../services/smartbudget";
import type { BudgetDto } from "../services/types/BudgetDto";
import type { CategoryDto } from "../services/types/CategoryDto";
import type { TransactionDto } from "../services/types/TransactionDto";


export const useDashboard = (userId: number = 1) => {
  const [transactions, setTransactions] = useState<TransactionDto[]>([]);
  const [categories, setCategories] = useState<CategoryDto[]>([]);
  const [budgets, setBudgets] = useState<BudgetDto[]>([]);
  const [filter, setFilter] = useState<string>("ALL");

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      getTransactions({ userId }),
      getCategories(),
      getBudgets(userId),
    ])
      .then(([tx, cat, bud]) => {
        setTransactions(tx);
        setCategories(cat);
        setBudgets(bud);
      })
      .finally(() => setLoading(false));
  }, [userId]);

  /* ===== FILTER ===== */

  const filteredTransactions =
    filter === "ALL"
      ? transactions
      : transactions.filter((t) => t.categoryDto?.name === filter);

  /* ===== BALANCE ===== */

  const balance = filteredTransactions.reduce((sum, t) => {
    return t.type === "INCOME"
      ? sum + t.amount
      : sum - t.amount;
  }, 0);

  /* ===== SPENDING ===== */

  const spending = filteredTransactions.map((t) => ({
    value: Math.abs(t.amount),
  }));

  /* ===== NOTIFICATIONS ===== */

  const notifications = transactions.slice(0, 5).map(t => ({
    id: `${t.date}-${t.amount}`,
    title: t.type === "INCOME" ? "Payment received" : "Expense",
    amount: Number(t.amount),
    time: t.date,
    type: t.type,
  }));

  /* ===== BREAKDOWN ===== */

  const breakdownMap = transactions
    .filter((t) => t.type === "EXPENSE")
    .reduce((acc, t) => {
      const key = t.categoryDto?.name || "Other";

      if (!acc[key]) acc[key] = 0;
      acc[key] += Math.abs(Number(t.amount));

      return acc;
    }, {} as Record<string, number>);

  const spendingBreakdown = Object.entries(breakdownMap).map(
    ([label, value]) => ({
      label,
      value,
    })
  );

  /* ===== PET ===== */

  const petExpenses = transactions
    .filter((t) => t.categoryDto?.name === "Pets")
    .map((t) => ({
      label: t.description || "Pet expense",
      value: Math.abs(Number(t.amount)),
    }));

    const income = transactions
    .filter(t => t.type === "INCOME")
    .reduce((sum, t) => sum + Number(t.amount), 0);

    const expenses = transactions
    .filter(t => t.type === "EXPENSE")
    .reduce((sum, t) => sum + Math.abs(Number(t.amount)), 0);

    /* ===== ASSETS ===== */

    const assetsMap = transactions.reduce((acc, t) => {
    const key = t.categoryDto?.name || "Other";

    if (!acc[key]) acc[key] = 0;
    acc[key] += Math.abs(Number(t.amount));

    return acc;
  }, {} as Record<string, number>);

  const assets = Object.entries(assetsMap).map(([name, value]) => ({
    name,
    value,
  }));

  return {
    balance,
    transactions: filteredTransactions,
    categories,
    budgets,
    spending,
    assets,
    notifications,
    spendingBreakdown,
    petExpenses,
    setFilter,
    loading,
  };
};