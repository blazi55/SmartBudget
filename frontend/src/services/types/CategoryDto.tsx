export type CategoryDto = {
  id: number;
  name: string;
  type: "INCOME" | "EXPENSE" | "PET";
  icon?: string;
  color?: string;
};