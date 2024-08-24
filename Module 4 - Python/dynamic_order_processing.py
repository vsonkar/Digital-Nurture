from abc import ABC, abstractmethod

class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, order_amount):
        pass

class RegularDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount 
     
class PremiumDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.9  

class VIPDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.8  

class Order:
    def __init__(self, customer_type, order_amount):
        self.customer_type = customer_type
        self.order_amount = order_amount
        self.discount_strategy = self.get_discount_strategy()

    def get_discount_strategy(self):
        if self.customer_type == "Regular":
            return RegularDiscount()
        elif self.customer_type == "Premium":
            return PremiumDiscount()
        elif self.customer_type == "VIP":
            return VIPDiscount()
        else:
            raise ValueError("Invalid customer type")

    def final_price(self):
        return self.discount_strategy.apply_discount(self.order_amount)

order1 = Order("Regular", 100)
order2 = Order("Premium", 100)
order3 = Order("VIP", 100)

print(f"Final price for Regular customer: ${order1.final_price():.2f}")
print(f"Final price for Premium customer: ${order2.final_price():.2f}")
print(f"Final price for VIP customer: ${order3.final_price():.2f}")
