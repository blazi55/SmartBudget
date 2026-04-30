-- =========================================
-- 🔥 RESET DATABASE (PostgreSQL)
-- =========================================

TRUNCATE TABLE transactions, budgets, categories, users
RESTART IDENTITY CASCADE;


-- =========================================
-- 👤 USERS
-- =========================================

INSERT INTO users (id, email, password_hash, created_at)
VALUES
(1, 'demo@smartbudget.com', 'hashed_password', NOW());


-- =========================================
-- 📂 CATEGORIES
-- =========================================

INSERT INTO categories (id, name, type, icon, color) VALUES
(1, 'Salary', 'INCOME', 'wallet', '#22c55e'),
(2, 'Food', 'EXPENSE', 'utensils', '#f97316'),
(3, 'Transport', 'EXPENSE', 'car', '#3b82f6'),
(4, 'Entertainment', 'EXPENSE', 'film', '#a855f7'),
(5, 'Shopping', 'EXPENSE', 'bag', '#ec4899'),
(6, 'Pets', 'EXPENSE', 'paw', '#eab308'),
(7, 'Health', 'EXPENSE', 'heart', '#ef4444'),
(8, 'Subscriptions', 'EXPENSE', 'tv', '#6366f1');


-- =========================================
-- 💸 TRANSACTIONS
-- =========================================

INSERT INTO transactions
(id, user_id, category_id, amount, type, date, currency, description, created_at)
VALUES

-- 💰 INCOME
(1, 1, 1, 5000.00, 'INCOME', CURRENT_DATE - INTERVAL '10 days', 'USD', 'Salary', NOW()),
(2, 1, 1, 800.00, 'INCOME', CURRENT_DATE - INTERVAL '2 days', 'USD', 'Freelance', NOW()),

-- 🍔 FOOD
(3, 1, 2, -45.20, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'McDonalds', NOW()),
(4, 1, 2, -120.00, 'EXPENSE', CURRENT_DATE - INTERVAL '3 days', 'USD', 'Groceries', NOW()),
(5, 1, 2, -32.50, 'EXPENSE', CURRENT_DATE - INTERVAL '6 days', 'USD', 'Pizza', NOW()),

-- 🚗 TRANSPORT
(6, 1, 3, -60.00, 'EXPENSE', CURRENT_DATE - INTERVAL '4 days', 'USD', 'Fuel', NOW()),
(7, 1, 3, -15.00, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'Uber', NOW()),
(8, 1, 3, -25.00, 'EXPENSE', CURRENT_DATE - INTERVAL '7 days', 'USD', 'Taxi', NOW()),

-- 🎬 ENTERTAINMENT
(9, 1, 4, -20.00, 'EXPENSE', CURRENT_DATE - INTERVAL '5 days', 'USD', 'Netflix', NOW()),
(10, 1, 4, -15.00, 'EXPENSE', CURRENT_DATE - INTERVAL '8 days', 'USD', 'Cinema', NOW()),

-- 🛍 SHOPPING
(11, 1, 5, -250.00, 'EXPENSE', CURRENT_DATE - INTERVAL '2 days', 'USD', 'Clothes', NOW()),
(12, 1, 5, -90.00, 'EXPENSE', CURRENT_DATE - INTERVAL '9 days', 'USD', 'Shoes', NOW()),

-- 🐶 PETS
(13, 1, 6, -90.00, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'Dog food', NOW()),
(14, 1, 6, -150.00, 'EXPENSE', CURRENT_DATE - INTERVAL '4 days', 'USD', 'Vet visit', NOW()),

-- ❤️ HEALTH
(15, 1, 7, -120.00, 'EXPENSE', CURRENT_DATE - INTERVAL '6 days', 'USD', 'Doctor visit', NOW()),

-- 📺 SUBSCRIPTIONS
(16, 1, 8, -15.00, 'EXPENSE', CURRENT_DATE - INTERVAL '1 days', 'USD', 'Netflix subscription', NOW()),
(17, 1, 8, -10.00, 'EXPENSE', CURRENT_DATE - INTERVAL '3 days', 'USD', 'Spotify', NOW());


-- =========================================
-- 📊 BUDGETS
-- (⚠️ sprawdź enum w Javie: MONTH / MONTHLY)
-- =========================================

INSERT INTO budgets
(id, user_id, category_id, limit_amount, period, start_date)
VALUES
(1, 1, 2, 500.00, 'MONTH', CURRENT_DATE),
(2, 1, 3, 300.00, 'MONTH', CURRENT_DATE),
(3, 1, 4, 200.00, 'MONTH', CURRENT_DATE),
(4, 1, 5, 400.00, 'MONTH', CURRENT_DATE),
(5, 1, 6, 250.00, 'MONTH', CURRENT_DATE),
(6, 1, 8, 50.00, 'MONTH', CURRENT_DATE);