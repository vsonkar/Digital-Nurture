class Employee:
    def __init__(self, name, hours_worked, hourly_rate):
        self.name = name
        self.hours_worked = hours_worked
        self.hourly_rate = hourly_rate

    def calculate_pay(self):
        overtime_hours = max(0, self.hours_worked - 40)
        regular_hours = min(self.hours_worked, 40)
        overtime_pay = overtime_hours * self.hourly_rate * 1.5
        regular_pay = regular_hours * self.hourly_rate
        total_pay = regular_pay + overtime_pay
        return total_pay

class Manager(Employee):
    def __init__(self, name, hours_worked, hourly_rate, bonus):
        super().__init__(name, hours_worked, hourly_rate)
        self.bonus = bonus

    def calculate_pay(self):
        base_pay = super().calculate_pay()
        total_pay = base_pay + self.bonus
        return total_pay

employee = Employee("Alice", 45, 20)

# Create Manager object
manager = Manager("Bob", 50, 30, 1000)

print(f"Total pay for {employee.name}: ${employee.calculate_pay():.2f}")
print(f"Total pay for {manager.name}: ${manager.calculate_pay():.2f}")
