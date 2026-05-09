import { api } from "./api";
import type { BudgetDto } from "./types/BudgetDto";
import type { CategoryDto } from "./types/CategoryDto";
import type { TransactionDto } from "./types/TransactionDto";

/* ===== USERS ===== */

export const getUsers = () => api.get("/users");
export const getUser = (id: number) => api.get(`/users/${id}`);

/* ===== CATEGORIES ===== */

export const getTransactions = (params?: {
  userId?: number;
  categoryId?: number;
}) => {
  const query = new URLSearchParams();

  if (params?.userId) query.append("userId", String(params.userId));
  if (params?.categoryId)
    query.append("categoryId", String(params.categoryId));

  return api.get(`/transactions?${query.toString()}`) as Promise<TransactionDto[]>;
};

export const getCategories = () =>
  api.get("/categories") as Promise<CategoryDto[]>;

export const getBudgets = (userId: number) =>
  api.get(`/budgets?userId=${userId}`) as Promise<BudgetDto[]>;