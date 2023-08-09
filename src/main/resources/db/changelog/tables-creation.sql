DROP TABLE IF EXISTS "employee-category";
CREATE TABLE "employee-category" (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

DROP TABLE IF EXISTS "employees";
CREATE TABLE "employees" (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    date_of_birth DATE,
    joined_date DATE,
    department_id INT,
    employee_category_id INT,
--    FOREIGN KEY (department_id) REFERENCES "departments"(id),
--    FOREIGN KEY (employeeCategory_id) REFERENCES "employee-category"(id)
);

DROP TABLE IF EXISTS "departments";
CREATE TABLE "departments" (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES employees(id)
);

DROP TABLE IF EXISTS "salary-rate";
CREATE TABLE "salary-rate" (
    id INT PRIMARY KEY IDENTITY(1,1),
    employee_category_id INT,
    employee_level INT,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (employee_category_id) REFERENCES "employee-category"(id)
);
