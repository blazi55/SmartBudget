package smartbudget.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import smartbudget.dto.createDto.CreateBudgetDto;
import smartbudget.dto.createDto.CreateTransactionDto;
import smartbudget.enums.BudgetPeriod;
import smartbudget.enums.Currency;
import smartbudget.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SmartBudgetIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void fullFlow_shouldWork() throws Exception {

		// =========================
		// 1. CREATE USER
		// =========================
		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "email":"test@test.com",
                              "password":"123456"
                            }
                        """))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value("test@test.com"));

		// =========================
		// 2. CREATE CATEGORY
		// =========================
		mockMvc.perform(post("/api/categories")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
                            {
                              "name":"Food",
                              "type":"EXPENSE",
                              "icon":"utensils",
                              "color":"#f97316"
                            }
                        """))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Food"));

		// =========================
		// 3. CREATE TRANSACTION
		// =========================
		CreateTransactionDto tx = new CreateTransactionDto();
		tx.setUserId(1L);
		tx.setCategoryId(1L);
		tx.setAmount(BigDecimal.valueOf(100));
		tx.setType(TransactionType.EXPENSE);
		tx.setDate(LocalDate.now());
		tx.setCurrency(Currency.USD);
		tx.setDescription("Food test");

		mockMvc.perform(post("/api/transactions")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(tx)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Food test"));

		// =========================
		// 4. CREATE BUDGET
		// =========================
		CreateBudgetDto budget = new CreateBudgetDto();
		budget.setUserId(1L);
		budget.setCategoryId(1L);
		budget.setLimitAmount(BigDecimal.valueOf(500));
		budget.setPeriod(BudgetPeriod.MONTH); // upewnij się, że enum pasuje
		budget.setStartDate(LocalDate.now());

		mockMvc.perform(post("/api/budgets")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(budget)))
				.andExpect(status().isOk());

		// =========================
		// 5. GET USERS
		// =========================
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email").value("test@test.com"));

		// =========================
		// 6. GET CATEGORIES
		// =========================
		mockMvc.perform(get("/api/categories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Food"));

		// =========================
		// 7. GET TRANSACTIONS
		// =========================
		mockMvc.perform(get("/api/transactions?userId=1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].description").value("Food test"));

		// =========================
		// 8. GET BUDGETS
		// =========================
		mockMvc.perform(get("/api/budgets?userId=1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].limitAmount").value(500));
	}
}