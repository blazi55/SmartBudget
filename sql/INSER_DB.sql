INSERT INTO users (id, email, password_hash, created_at)
VALUES
(1, 'demo@smartbudget.com', 'hashed_password', NOW());

INSERT INTO categories (id, name, type, icon, color) VALUES
(1, 'Salary', 'INCOME', 'wallet', '#22c55e'),
(2, 'Food', 'EXPENSE', 'utensils', '#f97316'),
(3, 'Transport', 'EXPENSE', 'car', '#3b82f6'),
(4, 'Entertainment', 'EXPENSE', 'film', '#a855f7'),
(5, 'Shopping', 'EXPENSE', 'bag', '#ec4899');

INSERT INTO transactions
(id, user_id, category_id, amount, type, date, currency, description, created_at)
VALUES

-- 💰 INCOME
(1, 1, 1, 5000.00, 'INCOME', CURRENT_DATE - INTERVAL '5 days', 'USD', 'Salary', NOW()),

-- 🍔 FOOD
(2, 1, 2, -45.20, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'McDonalds', NOW()),
(3, 1, 2, -120.00, 'EXPENSE', CURRENT_DATE - INTERVAL '2 days', 'USD', 'Groceries', NOW()),

-- 🚗 TRANSPORT
(4, 1, 3, -60.00, 'EXPENSE', CURRENT_DATE - INTERVAL '3 days', 'USD', 'Fuel', NOW()),
(5, 1, 3, -15.00, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'Uber', NOW()),

-- 🎬 ENTERTAINMENT
(6, 1, 4, -20.00, 'EXPENSE', CURRENT_DATE - INTERVAL '4 days', 'USD', 'Netflix', NOW()),

-- 🛍 SHOPPING
(7, 1, 5, -250.00, 'EXPENSE', CURRENT_DATE - INTERVAL '2 days', 'USD', 'Clothes', NOW()),

-- BONUS INCOME
(8, 1, 1, 800.00, 'INCOME', CURRENT_DATE - INTERVAL '1 days', 'USD', 'Freelance', NOW());

INSERT INTO budgets
(id, user_id, category_id, limit_amount, period, start_date)
VALUES
(1, 1, 2, 500.00, 'MONTHLY', CURRENT_DATE),
(2, 1, 3, 300.00, 'MONTHLY', CURRENT_DATE),
(3, 1, 4, 200.00, 'MONTHLY', CURRENT_DATE);