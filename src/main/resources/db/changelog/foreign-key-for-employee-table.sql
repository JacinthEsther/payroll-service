-- Add FOREIGN KEY constraint for department_id column
ALTER TABLE "employees"
ADD CONSTRAINT FK_employees_department
FOREIGN KEY (department_id)
REFERENCES "departments" (id);

-- Add FOREIGN KEY constraint for employeeCategory_id column
ALTER TABLE "employees"
ADD CONSTRAINT FK_employees_employee_category
FOREIGN KEY (employee_category_id)
REFERENCES "employee-category" (id);
