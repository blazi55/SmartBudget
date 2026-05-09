export type BudgetDto = {
  limitAmount: number;
  period: "DAILY" | "WEEKLY" | "MONTHLY" | "YEARLY";
  startDate: string;
};